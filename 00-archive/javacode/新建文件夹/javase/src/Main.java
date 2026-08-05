import java.util.Arrays;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        //在此输入您的代码...
        int n = scan.nextInt();

        double[] x = new double[n];
        double[] y = new double[n];
        double[] r = new double[n];

        for(int i = 0;i < n;i++){
            x[i] = scan.nextDouble();
            y[i] = scan.nextDouble();
            r[i] = scan.nextDouble();
        }

        double[][] prim = new double[n][n];

        for(int i = 0;i < n;i++){
            for(int j = i+1;j < n;j++){
                double dx = x[i] - x[j];
                double dy = y[i] - y[j];
                double dr = r[i] + r[j];
                double l = Math.max(Math.sqrt(dx*dx+dy*dy) - dr,0);
                prim[i][j] = l;
                prim[j][i] = l;
            }
        }

        double[] minData = new double[n];
        Arrays.fill(minData,Double.MAX_VALUE);
        boolean[] used = new boolean[n];
        int min = 0;
        minData[0] = 0.0;

        double totalWeight = 0.0;

        for(int i = 0;i < n;i++){
            int u = -1;
            for(int j = 0;j < n;j++){
                if(!used[i]&&((u == -1)||minData[i]<minData[u])){
                    u=i;
                }
            }

            used[u] = true;
            totalWeight += minData[u];

            used[u] = true;
            for(int k = 0;k < n;k++){
                if (!used[k] && prim[u][k] < minData[k]) {
                    minData[k] = prim[u][k];
                }
            }
        }
        System.out.println(totalWeight);
        scan.close();
        return;
    }
}