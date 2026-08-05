package a05interfacedemo5;

public class BasketballCoath extends Coath{
    public BasketballCoath() {
    }

    public BasketballCoath(String name, int age) {
        super(name, age);
    }

    @Override
    public void teach() {
        System.out.println("教学打篮球");
    }
}
