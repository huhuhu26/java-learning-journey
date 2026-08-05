package a02udpdemo;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class SendMessageDemo {
    public static void main(String[] args) throws IOException {
        DatagramSocket ds = new DatagramSocket();
        String str = "你好啊";
        byte[] buf = str.getBytes();
        InetAddress address = InetAddress.getByName("127.0.0.1");
        int port = 10086;
        DatagramPacket dp = new DatagramPacket(buf,buf.length,address,port);
        ds.send(dp);
        ds.close();

    }
}
