package Test;

public class Girlfriend {
    private int age;
    private String name;

    public Girlfriend(){
    }

    public Girlfriend(int age,String name){
        this.age = age;
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        if(age < 18||age > 40){
            throw new RuntimeException();
        }
        this.age = age;
    }

    public String getName() {

        return name;
    }

    public void setName(String name) {
        int len = name.length();
        if(len<3||len>10){
            throw new RuntimeException();
        }
        this.name = name;
    }
}
