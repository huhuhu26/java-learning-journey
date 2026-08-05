import java.util.Scanner;
public class huiwen {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int x = sc.nextInt() ;
        sc.close();
        int temp = x;
        int num = 0;
        while(x>0){
            num = num *10 + x % 10;
            x = x / 10;
        }
        if(num == temp){
            System.out.println("true");
        }else{
            System.out.println("false");
        }
    }
}
