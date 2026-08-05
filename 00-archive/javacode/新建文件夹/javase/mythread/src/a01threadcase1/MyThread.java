package a01threadcase1;

public class MyThread extends Thread{

    @Override
    public void run() {
        int i = 50;
        while(i>0) {
            System.out.println(this.getName() + "Hello Word!");
            i--;
        }
    }
}
