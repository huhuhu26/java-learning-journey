package studentmanage;

import java.util.Scanner;
import java.util.ArrayList;

public class manage {
    public static void main(String[] args) {
        ArrayList<Student> studentList = new ArrayList<>();
        while(true){
            System.out.println("----------学生管理系统---------");
            System.out.println("1:添加学生");
            System.out.println("2:删除学生");
            System.out.println("3:修改学生");
            System.out.println("4:查询学生");
            System.out.println("5:退出");
            Scanner sc = new Scanner(System.in);
            int a = sc.nextInt();
            if(a == 1){
                addStudent(studentList);
            }else if(a == 2){
                deleteStudent(studentList);
            }else if(a == 3){
                modifyStudent(studentList);
            }else if(a == 4){
                if(studentList.size() == 0){
                    System.out.println("当前无学生信息，请添加后再查询");
                }else{
                    System.out.println("id\t\tname\tage\tfamilyAddress");
                    for(int i = 0;i < studentList.size();i++){
                    System.out.println(studentList.get(i).getId() + "\t" + studentList.get(i).getName()
                                       + "\t" + studentList.get(i).getAge() + "\t" + studentList.get(i).getfamilyAddress());
                    }
                }
            }else if(a == 5){
                break;
            }
        }
    }

    public static void addStudent(ArrayList<Student> studentList){
        Scanner sc = new Scanner(System.in);
        Student s = new Student();
        System.out.println("请输入学生id:");
        while(true){
            String id = sc.next();
            boolean b = isId(studentList, id);
            if(b){
            s.setId(id);
            break;
            }else{
                System.out.println("输入错误请重新输入");
            }
        }
        System.out.println("请输入学生name:");
        String name = sc.next();
        s.setName(name);
        System.out.println("请输入学生age:");
        int age = sc.nextInt();
        s.setAge(age);
        System.out.println("请输入学生familyAddress:");
        String familyAddress = sc.next();
        s.setfamilyAddress(familyAddress);
        studentList.add(s);
        System.out.println("添加成功");
        return;
    }

    public static boolean isId(ArrayList<Student> studentList,String id){
        if(studentList.size() == 0){
            return true;
        }
        for(int i = 0;i < studentList.size();i++){
            boolean b = id.equals(studentList.get(i).getId());
            if(b){
                return false;
            }
        }
        return true;
    }

    public static void deleteStudent(ArrayList<Student> studentList){
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入学生id:");
        String id = sc.next();
        int a = inquireStudent(studentList,id);
        if(a == -1){
            System.out.println("学生不存在");
            return;
        }else{
            studentList.remove(a);
        }
        return;
    }

    public static int inquireStudent(ArrayList<Student> studentList,String id){
        for(int i = 0;i <= studentList.size();i++){
            boolean b = id.equals(studentList.get(i).getId());
            if(b){
                return i;
            }
        }
        return -1;
    }

    public static void modifyStudent(ArrayList<Student> studentList){
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入学生id:");
        String id = sc.next();
        int i = inquireStudent(studentList,id);
        if(i == -1){
            System.out.println("学生不存在");
            return;
        }else{
            System.out.println("请输入修改name");
            String name = sc.next();
            studentList.get(i).setName(name);
            System.out.println("请输入修改age");
            int age = sc.nextInt();
            studentList.get(i).setAge(age);
            System.out.println("请输入修改familyAddress");
            String familyAddress = sc.next();
            studentList.get(i).setfamilyAddress(familyAddress);
            return;
        }
    }


}
