package a05interfacedemo5;

public abstract class Coath extends Person{
    public Coath() {
    }

    public Coath(String name, int age) {
        super(name, age);
    }

    public abstract void teach();
}
