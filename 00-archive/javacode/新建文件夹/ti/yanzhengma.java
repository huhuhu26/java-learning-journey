package ti;

import java.util.Random;

public class yanzhengma {
    public static void main(String[] args) {
        Random random = new Random();
        int i;
        for(i = 0;i<4;i++){
            int b = random.nextInt(2);
            if(b == 0){
                int a1 = random.nextInt(26)+65;
                System.out.print((char)a1);
            }else{
                int a2 = random.nextInt(26)+97;
                System.out.print((char)a2);
            }
        }   
        int b1 = random.nextInt(10);
        System.out.println(b1);

    } 
}
