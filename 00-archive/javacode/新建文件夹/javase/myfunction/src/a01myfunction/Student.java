package a01myfunction;

public class Student {
    private String name;
    private int age;

    public Student(){}

    public Student(String str){
        this.name = str.split(",")[0];
        this.age = Integer.parseInt(str.split(",")[1]);

    }

    public Student(String name,int age){
        this.name = name;
        this.age = age;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age){
        this.age = age;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName(){
        return name;
    }
}
