package com.hyc.demo1;
import Java.until.scanner;
public class HelloWord {
    public static void main(String[] args) {
        scanner sc = new scanner(System.in);
        int x = sc.nextInt() ;
        int num=0;
        while(x>0){
            int w = x % 10;
            x = x / 10;
            num = num * 10 +w;
        }
        if( x == num)
            System.out.println("ture");
        else System.out.println("flase");
    }
}
