package studentmanage;

public class Student {
    private String id;
    private String name;
    private int age;
    private String familyAddress;

    public void student(){
    }

    public void student(String id,String name,int age,String familyAddress){
        this.id = id;
        this.name = name;
        this.age = age;
        this.familyAddress = familyAddress;
    }

    public String getId(){
        return id;
    }

    public void setId(String id){
        this.id = id;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public int getAge(){
        return age;
    }

    public void setAge(int age){
        this.age = age;
    }   

    public String getfamilyAddress(){
        return familyAddress;
    }

    public void setfamilyAddress(String familyAddress){
        this.familyAddress = familyAddress;
    }

}
