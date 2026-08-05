package a05interfacedemo5;

public class PingpangAthlete extends Athlete implements English{
    public PingpangAthlete() {
    }

    public PingpangAthlete(String name, int age) {
        super(name, age);
    }

    @Override
    public void learn() {
        System.out.println("学习打乒乓球");
    }

    @Override
    public void speak() {
        System.out.println("乒乓球运动员在说英语");
    }
}
