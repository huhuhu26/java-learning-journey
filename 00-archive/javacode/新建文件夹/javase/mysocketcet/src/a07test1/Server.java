package a07test1;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) throws IOException {
        ServerSocket ss = new ServerSocket(10000);
        //监听服务端
        Socket socket = ss.accept();
        InputStream is = socket.getInputStream();
        InputStreamReader isr = new InputStreamReader(is);
        int b;
        while((b = isr.read()) != -1){
            System.out.println((char)b);
        }
        socket.close();
        ss.close();
    }
}
