# 项目长期记忆 · hm-dianping（黑马点评 Redis 实战）

## 项目性质
- 跟随黑马 Redis 课程的学习项目：Spring Boot + MyBatis-Plus + MySQL + Redis。
- 前端是 nginx 部署的静态页（D:\BaiduNetdiskDownload\nginx-1.18.0\hmdp），端口 8080，`/api` 反代到后端 8081。
- 前端 `js/common.js` 响应拦截器：`success=false` 时把 `errorMsg` reject；401 时跳 login.html。用 `.catch(this.$message.error)` 弹提示（只显示约 3 秒）。

## 用户学习习惯
- 学习是「阶段性」的，可能出现两周以上的空档。讲知识点时不要用「上次说过」这类指代，要从头铺垫背景、给出完整上下文。
- 偏好「结合自己的代码」讲原理：先定位到具体文件/行号，再解释为什么这么写、不这么写会怎样。
- 对并发/时序类问题（如 double-check、锁）需要画出时间线才容易理解。

## 约定与踩过的坑
- 登录 token 必须由 `/user/login` 返回给前端（`Result.ok(token)`），否则前端存不到 sessionStorage，后续请求无 authorization 头 → 401 跳登录页。
- 店铺缓存的三个方案统一封装在 `com.hmdp.utils.CacheClient`（泛型方法 + `Function<ID,R> dbFallback`）：
  - `queryWithPassThrough`（缓存穿透 · 空值缓存）
  - `queryWithMutex`（缓存击穿 · 互斥锁，含 double-check）
  - `queryWithLogicalExpire`（缓存击穿 · 逻辑过期，需先跑测试类预热）
  - 三个方案在 `ShopServiceImpl.queryById()` 里切换，**每次只能保留一行生效**。
- 逻辑过期方案：key 不设物理 TTL，只存 `RedisData{data, expireTime}`；缓存需先用 `saveShop2Redis` 预热（测试类 `HmDianPingApplicationTests.testSaveShop`）。切换方案测试前要删掉 `cache:shop:*` 旧 key，否则格式不匹配会 NPE。
- 锁的实现：`setIfAbsent(key,"1",10s)`，`finally` 中仅当抢到锁才 unlock（避免误删他人锁）。
- 沙箱环境限制：本机 `mvn`/`bash` 在沙箱内不可用（PATH 异常），编译验证需在 IDEA 中完成。
