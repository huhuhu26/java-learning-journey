package a05interfacedemo5;

public abstract class Athlete extends Person{
    public Athlete() {
    }

    public Athlete(String name, int age) {
        super(name, age);
    }

    public abstract void learn();
}
