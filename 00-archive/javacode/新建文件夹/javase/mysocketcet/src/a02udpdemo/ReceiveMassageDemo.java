package a02udpdemo;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class ReceiveMassageDemo {
    public static void main(String[] args) throws IOException {
        DatagramSocket ds = new DatagramSocket(10086);
        byte[] buf = new byte[1024];
        DatagramPacket dp = new DatagramPacket(buf,buf.length);
        ds.receive(dp);
        byte[] data = dp.getData();
        int len = dp.getLength();
        InetAddress address = dp.getAddress();
        int port = dp.getPort();
        System.out.println("接受到数据：" + new String(buf,0,len));
    }
}
