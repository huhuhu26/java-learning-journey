package a01threadpool1;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MythreadPollDemo {
    public static void main(String[] args) {
        ExecutorService pool = Executors.newCachedThreadPool();

        pool.submit(new MyRuannable());

        pool.shutdown();
    }
}
