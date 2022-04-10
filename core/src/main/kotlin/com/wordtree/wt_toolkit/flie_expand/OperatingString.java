package com.wordtree.wt_toolkit.flie_expand;

import com.wordtree.wt_physical.User;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.wordtree.wt_toolkit.flie_expand.CreateFile.banduanFile;

public class OperatingString extends User {
    //    对文件中的字符串进行统计
    public static void countString(String fl) throws IOException {
//        使用map集合进行对文件字符串的统计
        Map<String, Integer> map = new HashMap();
//        对文件进行读取
        banduanFile(fl);
        InputStream input = new FileInputStream(fl);
//        利用map进行对字符串的统计
        int a = 0;
        while ((a = input.read()) != -1) {
            map.put(String.valueOf((char) a), map.containsKey(String.valueOf(a)) ? map.get(String.valueOf(a)) + 1 : 1);
        }
        map.forEach((k, v) -> {
            System.out.println("字符：" + k + "\t个数：" + v);
        });
        input.close();
    }


    //完成对字符串的增删改查
//    public static void setString (String oldStr,String newStr) throws IOException {
////        getWenJian(fl);
////        Scanner sc=new Scanner(System.in);
////        System.out.println("请输入要修改的内容:");
////        String a=sc.next();
////
////        System.out.println("请输入修改后的内容:");
//                RandomAccessFile raf = new RandomAccessFile(filePath, "rw");
//        String line;
//        while (null!=(line=raf.readLine())) {
//            if(line.contains(oldStr)){
//                String[] split = line.split(oldStr);
//                raf.seek(split[0].length());
//                raf.writeBytes(newStr);
//                raf.writeBytes(split[1]);
//            }
//        }
//        raf.close();
//        }
    public static void operatingString(String fl) throws IOException {
        RandomAccessFile f1 = new RandomAccessFile(fl, "rws");
        f1.readLine();
    }


    //统计数字数
    public static int countNumber(String str) {
        int count = 0;
        Pattern p = Pattern.compile("\\d");
        Matcher m = p.matcher(str);
        while (m.find()) {
            count++;
        }
        return count;
    }


    //    统计字母
    public static int countLetter(String str) {
        int count = 0;
        Pattern p = Pattern.compile("[a-zA-Z]");
        Matcher m = p.matcher(str);
        while (m.find()) {
            count++;
        }
        return count;
    }


    //    统计汉字数
    public static int countChinese(String str) {
        int count = 0;
        Pattern p = Pattern.compile("[\\u4e00-\\u9fa5]");
        Matcher m = p.matcher(str);
        while (m.find()) {
            count++;
        }
        return count;
    }


    //    统计空格数
    public static int countSpace(String str) {
        int count = 0;
        Pattern p = Pattern.compile("\\s");
        Matcher m = p.matcher(str);
        while (m.find()) {
            count++;
        }
        return count;
    }


    //    综合方法
    public static void tonJi(String fl) {
        int num = 0; //数字数
        int letter = 0; //字母数
        int line = 0; //行数
        int space = 0; //空格数
        int word = 0; //汉字数
        try {
            File f3 = new File(fl);
            BufferedReader br = new BufferedReader(new FileReader(f3));
            String str = null;
            while ((str = br.readLine()) != null) {//读取这一行的数据
//                输出这一行的字符
                System.out.println(str);
                line++;//行++
                num += countNumber(str);//统计这一行的数值
                letter += countLetter(str);//统计这一行的字母数
                word += countChinese(str);//统计这一行的汉字
                space += countSpace(str);//统计这一行的空格数
            }
        } catch (Exception e) {
            System.out.println("文件为空!");
        }
        System.out.println("数字数：" + num);
        System.out.println("字母数" + letter);
        System.out.println("汉字数" + word);
        System.out.println("空格数" + space);
        System.out.println("行数" + line);
    }


    //    获取一共有多少个字符
    public void getStringCiShu(String fl) {
        File f4 = new File(fl);
//        返回这个文件的长度
        setZishu(Math.toIntExact(f4.length()));
    }
}

