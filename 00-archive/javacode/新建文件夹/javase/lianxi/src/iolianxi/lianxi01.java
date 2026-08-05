package iolianxi;

import com.sun.source.util.SourcePositions;

import javax.print.DocFlavor;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class lianxi01 {
    public static void main(String[] args) throws IOException {
        //https://hanyu.baidu.com/shici/detail?pid=0b2f26d4c0ddb3ee693fdb1137ee1b0d&from=kg0
        //男生名字:
        //http://www.haoming8.cn/baobao/10881.html

        //获取女生名字:
        //http://www.haoming8.cn/baobao/7641.html
        String FamilyNameNet = "https://hanyu.baidu.com/shici/detail?pid=0b2f26d4c0ddb3ee693fdb1137ee1b0d&from=kg0";
        String BoyNameNet = "http://www.haoming8.cn/baobao/10881.html";
        String GirlNameNet = "http://www.haoming8.cn/baobao/7641.html";

        String FamilyNameStr = webCrawler(FamilyNameNet);
        String BoyNameStr = webCrawler(BoyNameNet);
        String GirlNameStr = webCrawler(GirlNameNet);

        ArrayList<String> FamilyNameTempList = getDate(FamilyNameStr,"(\\W{4})(，|。)",1);
        ArrayList<String> BoyNameTempList = getDate(BoyNameStr,"([\\u4E00-\\u9FA5]{2})(、|。)",1);
        ArrayList<String> GirlNameTempList = getDate(GirlNameStr,"(.. ){4}..",0);

        ArrayList<String> FamilyNameList = new ArrayList<>();
        for(String str : FamilyNameTempList){
            for(int i = 0;i < str.length();i++){
                FamilyNameList.add(str.charAt(i) + "");
            }
        }

        ArrayList<String> BoyNameList = new ArrayList<>();
        //处理方案：去除重复元素
        for(String Str : BoyNameTempList){
            if(!BoyNameList.contains(Str)){
                BoyNameList.add(Str);
            }
        }

        //女孩的名字
        ArrayList<String> GirlNameList = new ArrayList<>();
        for(String str : GirlNameTempList){
            String[] strs = str.split(" ");
            for(int i = 0; i < strs.length;i++){
                GirlNameList.add(strs[i]);
            }
        }

        ArrayList<String> list = getInfos(FamilyNameList,BoyNameList,GirlNameList,70,50);
        Collections.shuffle(list);

        BufferedWriter bw = new BufferedWriter(new FileWriter("lianxi//name.txt"));
        for(String str : list){
            bw.write(str);
            bw.newLine();
        }
        bw.close();



    }

    public static ArrayList<String> getInfos(ArrayList<String> FamilyNameList,ArrayList<String> BoyNameList,ArrayList<String> GirlNameList,int boyCount,int girlCount){
        HashSet<String> boyhs = new HashSet<>();
        while(true){
            if(boyhs.size()==boyCount){
                break;
            }
            Collections.shuffle(FamilyNameList);
            Collections.shuffle(BoyNameList);
            boyhs.add(FamilyNameList.get(0) + BoyNameList.get(0));
        }

        HashSet<String> girlhs = new HashSet<>();
        while(true){
            if(girlhs.size()==girlCount){
                break;
            }
            Collections.shuffle(FamilyNameList);
            Collections.shuffle(GirlNameList);
            girlhs.add(FamilyNameList.get(0) + GirlNameList.get(0));
        }

        ArrayList<String> list = new ArrayList<>();
        Random r = new Random();
        for(String str : boyhs){
            int age = r.nextInt(10) + 18;
            list.add(str + "-男-" + age);
        }

        for(String str : girlhs){
            int age = r.nextInt(8) + 18;
            list.add(str + "-女-" + age);
        }
        return list;
    }


    public static ArrayList<String> getDate(String str, String regex,int index){
        ArrayList<String> list = new ArrayList<>();
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(str);
        while(matcher.find()){
            String group = matcher.group(index);
            list.add(group);
        }
        return list;
    }

    public static String webCrawler(String net) throws IOException {
        StringBuilder sb = new StringBuilder();
        URL url = new URL(net);
        URLConnection conn = url.openConnection();
        InputStreamReader isr = new InputStreamReader(conn.getInputStream());
        int ch;
        while((ch = isr.read()) != -1){
            sb.append(((char)ch));
        }
        isr.close();
        return sb.toString();
    }


}
