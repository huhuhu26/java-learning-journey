package jinezhuanhuan;

import java.util.Scanner; 

public class jinezhuanhuan {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int money;
        while(true){
            money = sc.nextInt();
            if(money > 0 && money < 9999999){
                break;
            }else{
                System.out.println("输入金额无效");
            }
        }

        String moneyStr = "";
        while(money > 0){
            int ge = money % 10;
            String c = getCapitalNumber(ge);
            moneyStr = c + moneyStr;
            money = money / 10;
        }

        int count = 7 - moneyStr.length();
        for(int i = 0;i < count;i++){
            moneyStr = "零" + moneyStr;
        }

        String result = "";
        String[] arr = {"佰","拾","万","仟","佰","拾","元"};
        for(int i = 0;i < arr.length;i++){
            char c = moneyStr.charAt(i);
            result = result + c + arr[i];
        }

    }


    public static String getCapitalNumber(int number){
        String[] arr = {"零","壹","贰","叁","肆","伍","陆","柒","捌","玖"};
        return arr[number];
    }
}
