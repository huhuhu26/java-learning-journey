import java.util.Scanner;
import java.util.ArrayList;
import java.util.Random;

public class manage {
    public static void main(String[] args){
        ArrayList<Student> list = new ArrayList<>();
        System.out.println("欢迎来到学生管理系统");
        while(true){
            System.out.println("请选择操作1登录 2注册 3忘记密码 4退出");
            Scanner sc = new Scanner(System.in);
            int a = sc.nextInt();
            switch(a){
                case 1:{
                    log(list);
                    break;
                }
                case 2:{
                    register(list);
                    break;
                }
                case 3:{
                    retrieve(list);
                    break;
                }
                case 4:{
                    System.out.println("感谢使用，再见");
                    System.exit(0);
                }
                default: System.out.println("没有这个选项！");
            }
        }
    }   

    public static void register(ArrayList<Student> list){
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入用户名");
        String username;
        Student s = new Student();
        while(true){
            username = sc.next();
            boolean a = isUsername(username,list);
            if(a){
            s.setUsername(username);
            break; 
            }else{
                System.out.println("用户名不符合要求,请重新输入");
            }
        }
        String passcode2,passcode1;
        while(true){
            System.out.println("请输入密码");
            passcode1 = sc.next();
            System.out.println("请再次输入密码");
            passcode2 = sc.next();
            if(passcode2.equals(passcode1))break;
            else System.out.println("两次密码不同，请重新输入");
        }
        System.out.println("请输入身份证号码");
        String identity;
        while(true){
            identity = sc.next();
            boolean b = isIdentity(identity);
            if(!b)System.out.println("输入身份证号码错误，请重新输入");
            else break;
        }
        System.out.println("请输入电话号码");
        String phone;
        while(true){
            phone = sc.next();
            boolean b = isPhone(phone);
            if(!b)System.out.println("电话号码错误，请重新输入");
            else break;
        }
        s.setPasscode(passcode2);
        s.setUsername(username);
        s.setIdentity(identity);
        s.setPhone(phone);
        list.add(s);
    }

    public static boolean isUsername(String username,ArrayList<Student> list){
        int n = 0,A = 0;
        for(int i = 0;i < username.length();i++){
            char a = username.charAt(i);
            if(a < '0'||(a > '9'&&a < 'A')||(a > 'Z'&&a < 'a')||a > 'z'){
                return false;
            }else{
                if(a > '0'&&a < '9')
                    n++;
                else A++;
                }
            }
            if(list.size() != 0){
                for(int i = 0;i < list.size();i++) {
                    if(list.get(i).getUsername().equals(username)){
                        return false;
                    }
                }
            }
        if(A == 0||A + n < 3||A + n > 15 )return false;
        return true;
    }

    public static void log(ArrayList<Student> list){
        if(list.size() == 0){
            System.out.println("无用户，请先注册");
            return;
        }
        Scanner sc = new Scanner(System.in);
        String username;
        String captcha;
        String captcha1;
        String passcode;
        int count = 0;
        while(count < 3){
            System.out.println("请输入用户名");
            username = sc.next();
            int a = -1;
            for(int i = 0;i < list.size();i++){
                if(list.get(i).getUsername().equals(username)){
                    a = i;
                    break; 
                }
                if(a == -1){
                    System.out.println("用户名未注册，请先注册");
                    return;
                }
            }
            System.out.println("请输入密码");
            passcode = sc.next();
            if(!list.get(a).getPasscode().equals(passcode)){
                System.out.println("你输入的密码错误");
                count++;
                continue;  
            }
            captcha1 = createCaptcha();
            System.out.println(captcha1);
            System.out.println("请输入验证码");
            captcha = sc.next();
            if(!captcha1.equals(captcha)){
                System.out.println("你输入的验证码错误");
                count++;
                continue;
            }
            System.out.println("登陆成功");
            break;
        }
        if(count >= 3){
            System.out.println("登陆失败");
        }
    }

    public static boolean isIdentity(String identity){
        if(identity.length() != 18)return false;
        char a = identity.charAt(0);
        if(a == '0')return false;
        for(int i = 1;i < identity.length() - 1;i++){
            a = identity.charAt(i);
            if(a < '0'||a > '9'){
                return false;
            }
        }
        a = identity.charAt(17);
        if(a < '0'||(a > '9'&&(a != 'x'||a != 'X'))){
            return  false;
        }
        return true;
    }

    public static boolean isPhone(String phone){
        int len = phone.length();
        char p = phone.charAt(0);
        if(len != 11||p == 0)return false;
        return true;
    }

    public static String createCaptcha(){
        String captcha = "";
        Random random = new Random();
        int c = random.nextInt(5);
        int i;
        for(i = 0;i<5;i++){
            int b = random.nextInt(2);
            if(i != c){
                if(b == 0){
                    int a1 = random.nextInt(26)+65;
                    char c1 = (char)a1;
                    captcha = captcha + c1;
                }else if(b == 1){
                    int a2 = random.nextInt(26)+97;
                    char c2 = (char)a2;
                    captcha = captcha + c2;
                }
            }else{
                int a3 = random.nextInt(10)+48;
                char c3 = (char)a3;
                captcha = captcha + c3;
            }
        }   
        return captcha;
    }

    public static void retrieve(ArrayList<Student> list){
        System.out.println("请输入用户名");
        Scanner sc = new Scanner(System.in);
        String username = sc.next();
        int a = -1;
        for(int i = 0;i < list.size();i++){
            if(list.get(i).getUsername().equals(username)){
                a = i;
                break;
            }
        }
        if(a == -1){
            System.out.println("用户名错误");
            return;
        }
        String identity;
        String phone;
        System.out.println("请输入身份证号码");
        identity = sc.next();
        System.out.println("请输入电话号码");
        phone = sc.next();
        if(list.get(a).getPhone().equals(phone)&&list.get(a).getIdentity().equals(identity)){
            System.out.println("请输入修改后的密码");
            String passcode = sc.next();
            list.get(a).setPasscode(passcode);
            return;
        }else{
            System.out.println("输入的身份证号码或手机号号码有误");
            return;
        }
    }

}
