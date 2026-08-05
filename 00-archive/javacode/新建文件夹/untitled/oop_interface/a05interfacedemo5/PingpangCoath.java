package a05interfacedemo5;

public class PingpangCoath extends Coath implements English{
    public PingpangCoath() {
    }

    public PingpangCoath(String name, int age) {
        super(name, age);
    }

    @Override
    public void teach() {
        System.out.println("教学打乒乓球");
    }

    @Override
    public void speak() {
        System.out.println("乒乓球教练在说英语");
    }
}
