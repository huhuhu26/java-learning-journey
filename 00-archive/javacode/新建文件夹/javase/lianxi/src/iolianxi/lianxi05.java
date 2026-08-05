package iolianxi;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class lianxi05 {
    public static void main(String[] args) throws IOException {
        ArrayList<Student> s = new ArrayList<>();
        BufferedReader br = new BufferedReader(new FileReader("lianxi//names.txt"));
        String line;

        while((line = br.readLine()) != null){
            String[] strs = line.split("-");
            s.add(new Student(strs[0],strs[1],Integer.parseInt(strs[2]),Double.parseDouble(strs[3])));
        }

        double weight = 0;
        for(Student stu : s){
            weight = weight + stu.getWeight();
        }

        double[] arr = new double[s.size()];
        int index = 0;
        for(Student stu : s){
            arr[index] = stu.getWeight()/weight;
            index++;
        }

        for(int i = 1;i < arr.length;i++){
            arr[i] = arr[i] + arr[i-1];
        }

        double number = Math.random();

        //二分查找

        int index2 = -Arrays.binarySearch(arr,number)-1;
        Student s1 = s.get(index2);
        System.out.println(s1);
        double w = s.get(index2).getWeight()/2;
        s.get(index2).setWeight(w);

        BufferedWriter bw = new BufferedWriter(new FileWriter("lianxi//names.txt"));
        for(Student stu : s){
            bw.write(stu.toString());
            bw.newLine();
        }
        bw.close();



    }
}
