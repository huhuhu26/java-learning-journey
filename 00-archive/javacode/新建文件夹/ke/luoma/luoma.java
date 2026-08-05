package luoma;

import java.util.Scanner;

public class luoma {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        String number;
        while(true){
            number = sc.nextLine();
            boolean flag = isNumber(number);
            if(flag){
                if(number.length()<=9){
                    break;
                }else{
                    System.out.println("你输入的数字过长,请重新输入");
                }
            }
            System.out.println("你输入的字符不合规,请重新输入");
        }

        String[] nmStrings = {" ","Ⅰ ","Ⅱ ","Ⅲ ","Ⅳ ","Ⅴ ","Ⅵ ","Ⅶ ","Ⅷ ","Ⅸ "};
        StringBuilder sb = new StringBuilder();

        for(int i = 0;i < number.length();i++){
            int a = number.charAt(i) - '0';
            sb.append(a);
        }
        System.out.println(sb);
    }
    public static boolean isNumber(String number){
        for(int i = 0;i < number.length();i++){
            char c = number.charAt(i);
            if(c < '0' || c > '9'){
                return false;
            }
        }
        return true;
    }
}
