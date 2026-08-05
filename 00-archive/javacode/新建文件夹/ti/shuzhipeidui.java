package ti;
import java.util.Scanner;
public class shuzhipeidui {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        int[] a = new int[n];
        for(int i = 0 ; i < n ; i++){
            a[i] = scan.nextInt();
        }
        int count = 0;
        boolean[] b = new boolean[n];
        for(int i = 0 ; i < (n - 1) ; i++){
            if(b[i])continue;
            for(int j = i + 1 ; j < n ; j++){
                if(b[j])continue;
                if(a[i] + 1 == a[j]){
                    count++;
                    b[i] = true;
                    b[j] = true;
                    break;
                }
            }
        }
        System.out.println(count);
        scan.close();
    }
}
