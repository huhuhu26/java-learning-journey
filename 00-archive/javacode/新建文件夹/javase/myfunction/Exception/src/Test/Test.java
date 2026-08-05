package Test;
import java.util.Scanner;

public class Test {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Girlfriend gf = new Girlfriend();
        int age = 0;
        while (true) {
            try {
                String name = sc.nextLine();
                gf.setName(name);
                String ageStr = sc.nextLine();
                age = Integer.parseInt(ageStr);
                break;
            } catch (NumberFormatException e) {
                System.out.println("年龄的格式有误，请输入数字");
            } catch (RuntimeException e) {
                throw new RuntimeException(e);
            }
        }
        gf.setAge(age);
    }
}