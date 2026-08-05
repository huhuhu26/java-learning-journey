package a03udpdemo3;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class SendMassageDemo {
    public static void main(String[] args) throws IOException {
        DatagramSocket ds = new DatagramSocket();
        Scanner sc = new Scanner(System.in);
        while (true) {
            InetAddress address = InetAddress.getByName("127.0.0.1");
            String str = sc.next();
            byte[] bytes = str.getBytes();
            if(str.equals("886")){
                break;
            }
            int port = 10086;
            DatagramPacket dp = new DatagramPacket(bytes,bytes.length,address,port);
            ds.send(dp);
            ds.close();
        }
    }
}
