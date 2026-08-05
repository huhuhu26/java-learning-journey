package iolianxi;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class lianxi02 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("lianxi//name.txt"));
        ArrayList<String> list = new ArrayList<>();
        String line;
        while((line = br.readLine()) != null){
            list.add(line.split("-")[0]);
        }
        Collections.shuffle(list);
        System.out.println(list.get(0));
    }
}
