package a05threadmethod2;

import a01threadcase1.MyThread;

public class ThreadDemo {
    public static void main(String[] args) {

        MyRunnable mr = new MyRunnable();
        Thread t1 = new Thread(mr,"飞机");
        Thread t2 = new Thread(mr,"坦克");

        t1.setPriority(1);
        t2.setPriority(10);

        t1.start();
        t2.start();

    }
}
