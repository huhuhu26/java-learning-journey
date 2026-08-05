package ti;

import java.util.Scanner;

public class p1002guohezu {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int bx,by,mx,my;
        bx = sc.nextInt();
        by = sc.nextInt();
        mx = sc.nextInt();
        my = sc.nextInt();
        sc.close();
        bx += 2; by += 2; mx += 2; my += 2;
        int[][] a = new int[40][40];
        int[][] b = new int[40][40];
        int[] fx = {0,-2,-2,-1,-1,1,1,2,2};
        int[] fy = {0,1,-1,2,-2,2,-2,1,-1};
        int i;
        for(i = 0;i <= 8;i++)b[mx+fx[i]][my+fy[i]] = 1;
        a[2][1] = 1;
        int j;
        for(i = 2;i < bx+1;i++){
            for(j = 2;j < by+1;j++){
                if(b[i][j] == 1)continue;
                a[i][j] = a[i-1][j] + a[i][j-1];
            }
        }
        System.out.println(a[bx][by]);
    }
}
