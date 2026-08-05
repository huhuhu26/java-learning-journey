package ti;

import java.util.Scanner;

 class pingpang {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int i = 0;
        String str1 = "";
              char a;
        while(true){
            a = sc.next().charAt(0);
            if(a == 'E')break;
            str1 = str1 + a;
        }
        sc.close();
        i = 0;
        int win = 0;
        int lose = 0;
        char[] charArr = str1.toCharArray(); 
        while(charArr[i] != 'E'){
            if(i % 11 == 0&&i != 0){
                if((win - lose) >= 2||(lose - win) <=-2){
                    System.out.println(win + ":" + lose);
                    win = 0;
                    lose = 0;
                }
            }
            if(charArr[i] == 'W'){
                win++;
            }else{
                lose++;
            }
            i++;
        }
        System.out.println(win + ":" + lose);
        i = 0;
        win = 0;
        lose = 0;
        System.out.println();
        System.out.println();
        while(charArr[i] != 'E'){
            if(i % 21 == 0&&i != 0){
                if((win - lose) >= 2||(lose - win) <=-2){
                    System.out.println(win + ":" + lose);
                    win = 0;
                    lose = 0;
                }
            }
            if(charArr[i] == 'W'){
                win++;
            }else{
                lose++;
            }
            i++;
        }
        System.out.println(win + ":" + lose);
    }
}




