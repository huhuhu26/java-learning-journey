package a07test1;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) throws IOException {

        Socket socket = new Socket("127.0.0.1",10000);

        Scanner sc = new Scanner(System.in);
        OutputStream os = socket.getOutputStream();

        while(true){
            System.out.println("请输入发送内容");
            String str = sc.nextLine();
            if(str.equals("886")){
                break;
            }
            os.write(str.getBytes());
        }
        socket.close();
    }
}
