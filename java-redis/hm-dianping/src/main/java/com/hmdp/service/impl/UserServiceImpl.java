package com.hmdp.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import cn.hutool.core.util.RandomUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hmdp.dto.LoginFormDTO;
import com.hmdp.dto.Result;
import com.hmdp.dto.UserDTO;
import com.hmdp.entity.User;
import com.hmdp.mapper.UserMapper;
import com.hmdp.service.IUserService;
import com.hmdp.utils.RedisConstants;
import com.hmdp.utils.RegexPatterns;
import com.hmdp.utils.RegexUtils;
import com.hmdp.utils.SystemConstants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import static com.hmdp.utils.RedisConstants.*;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author 虎哥
 * @since 2021-12-22
 */
@Service
@Slf4j
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public Result sendCode(String phone, HttpSession session) {
        //1校验手机号是否合法
        System.out.println(phone);
        if (RegexUtils.isPhoneInvalid(phone)) {
            //2如果不合法，返回错误
            return Result.fail("手机号格式不正确");
        }

        //3如果合法，生成验证码
        String code = RandomUtil.randomNumbers(6);
        //4将验证码存入redis
        stringRedisTemplate.opsForValue().set(LOGIN_CODE_KEY + phone, code, LOGIN_CODE_TTL, TimeUnit.MINUTES);
        log.info("发送验证码：{}给{}", code, phone);
        return Result.ok();
    }

    @Override
    public Result login(LoginFormDTO loginForm, HttpSession session) {
        //1校验手机号是否合法

        if (RegexUtils.isPhoneInvalid(loginForm.getPhone())) {
            //2如果不合法，返回错误
            return Result.fail("手机号格式不正确");
        }
        //3校验验证码是否合法
        String code = stringRedisTemplate.opsForValue().get(LOGIN_CODE_KEY + loginForm.getPhone());
        if (!loginForm.getCode().equals(code)) {
            //4如果不合法，返回错误
            return Result.fail("验证码格式不正确");
        }
        //根据手机号查询用户
        User user = query().eq("phone", loginForm.getPhone()).one();

        if (user == null) {
            //如果用户不存在，则创建用户
            user = new User();
            user.setPhone(loginForm.getPhone());
            user.setNickName(SystemConstants.USER_NICK_NAME_PREFIX + loginForm.getPhone());
            save(user);
        }
        //TODO 将用户保存到redis
        //随机生成token，作为登录令牌
        String token = UUID.randomUUID().toString();
        //将用户转为hash存储
        UserDTO userDTO = BeanUtil.copyProperties(user, UserDTO.class);

        Map<String,Object> userMap = BeanUtil.beanToMap(userDTO,new HashMap<>(),
                CopyOptions.create().
                        setIgnoreNullValue(true).
                        setFieldValueEditor((fieldName,fieldValue) -> fieldValue.toString()) )
                ;

        //存储
        String tokenKey = LOGIN_USER_KEY + token;
        stringRedisTemplate.opsForHash().putAll(tokenKey,userMap);
        //设置有效期
        stringRedisTemplate.expire(tokenKey,LOGIN_USER_TTL,TimeUnit.MINUTES);
        log.info("用户登录成功，用户ID：{}", user.getId());
        //7.返回token给前端
        return Result.ok(token);
    }
}
