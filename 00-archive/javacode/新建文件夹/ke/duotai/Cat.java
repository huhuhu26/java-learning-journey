public class Cat extends Animal{


    public Cat(){

    }
    

    public Cat(int age,String color){
        super(age,color);

    }


    public void eat(String something){
        System.out.println(getAge() + "的" + getColor() + "的猫眯着眼睛侧着头吃" + something);
    }

    public void catchMouse(){
        System.out.println("猫抓老鼠");
    }
}
