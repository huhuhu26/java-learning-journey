package MyTest;

import java.io.*;

public class test01 {
    public static void main(String[] args) throws IOException {

        FileInputStream fis = new FileInputStream("myio\\a.txt");
        FileOutputStream fos = new FileOutputStream("myio\\redu.txt");
        int b;
        while((b    = fis.read()) != -1){
            fos.write(b^2);
        }
        fos.close();
        fis.close();

    }
}
