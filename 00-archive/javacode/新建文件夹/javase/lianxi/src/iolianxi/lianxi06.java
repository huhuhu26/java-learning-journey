package iolianxi;

import java.io.*;
import java.util.Scanner;

public class lianxi06 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("lianxi//userinfo.txt"));

        String line = br.readLine();


        String[] userinfo = line.split("&");
        String[] arr1 = userinfo[0].split("=");
        String[] arr2 = userinfo[1].split("=");
        String[] arr3 = userinfo[2].split("=");

        System.out.println("请输入姓名：");
        Scanner sc = new Scanner(System.in);
        String username = sc.next();
        System.out.println("请输入密码:");
        String password = sc.next();
        int count = Integer.parseInt(arr3[1]);
        if(count == 3){
            System.out.println("账户已锁定");
        }

        if(username.equals(arr1[1])&&password.equals(arr2[1])){
            System.out.println("登陆成功");
        }else {
            System.out.println("登录失败");
            count = count + 1;
            BufferedWriter bw = new BufferedWriter(new FileWriter("lianxi//userinfo.txt"));
            bw.write(arr1[0]+"="+arr1[1]+"&"+arr2[0]+"="+arr2[1]+"&"+arr3[0]+"="+String.valueOf(count));
            bw.close();
        }

        br.close();
    }
}
