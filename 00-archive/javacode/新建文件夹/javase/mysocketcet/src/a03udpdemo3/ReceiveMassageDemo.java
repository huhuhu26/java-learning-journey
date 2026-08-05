package a03udpdemo3;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class ReceiveMassageDemo {
    public static void main(String[] args) throws IOException {
        DatagramSocket ds = new DatagramSocket(10086);
        byte[] bytes = new byte[1024];
        DatagramPacket dp =  new DatagramPacket(bytes,bytes.length);
        while (true) {
            ds.receive(dp);
            byte[] data = dp.getData();
            int len = dp.getLength();
            String ip = dp.getAddress().getHostAddress();
            String name = dp.getAddress().getHostName();
            System.out.println("ip为"+ip+"主机为"+name+"的人发送了数据:"+new String(data,0,len));
        }
    }
}
