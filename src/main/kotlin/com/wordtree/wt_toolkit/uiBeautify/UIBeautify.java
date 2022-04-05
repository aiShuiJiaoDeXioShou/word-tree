package com.wordtree.wt_toolkit.uiBeautify;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class UIBeautify {
    public static String 设置_输出文本色() {
        int 文本属性值 = 0;
        int 前景色值;
        int 背景色值;
        Scanner sc = new Scanner(System.in);
        System.out.println("您要输出的文字是:");
        System.out.println(获取_调色表());
        String 文字 = sc.next();
        System.out.println("请选择分别文本属性,前景色,跟背景色!");
        int 文本属性 = sc.nextInt();
        int 前景色 = sc.nextInt();
        int 背景色 = sc.nextInt();
        switch (文本属性) {
            case 1:
                文本属性值 = 0;
                break;
            case 2:
                文本属性值 = 1;
                break;
            case 3:
                文本属性值 = 4;
                break;
            case 4:
                文本属性值 = 5;
                break;
            case 5:
                文本属性值 = 7;
                break;
            case 6:
                文本属性值 = 8;
                break;
/*            case 7:
                文本属性值= ;
                break;
            case 8:
                文本属性值= ;
                break;
            case 9:
                文本属性值= ;
                break;*/
        }
        switch (前景色) {
            case 1:
                前景色 = 30;
                break;
            case 2:
                前景色 = 31;
                break;
            case 3:
                前景色 = 32;
                break;
            case 4:
                前景色 = 33;
                break;
            case 5:
                前景色 = 34;
                break;
            case 6:
                前景色 = 35;
                break;
            case 7:
                前景色 = 36;
                break;
            case 8:
                前景色 = 37;
                break;
        }
        switch (背景色) {
            case 1:
                背景色 = 40;
                break;
            case 2:
                背景色 = 41;
                break;
            case 3:
                背景色 = 42;
                break;
            case 4:
                背景色 = 43;
                break;
            case 5:
                背景色 = 44;
                break;
            case 6:
                背景色 = 45;
                break;
            case 7:
                背景色 = 46;
                break;
            case 8:
                背景色 = 47;
                break;
        }
        return 文字 = "\u001b[" + 文本属性 + ";" + 前景色 + ";" + 背景色 + "m" + 文字;
    }
    public static String setWenGreen(String 文本){

        return 文本 = "\u001b[0;32m"+ 文本;
    }
    public static String setWenReds(String 文本){

        return 文本 = "\u001b[0;31m"+ 文本;
    }
    public static String setWenYellow(String 文本){

        return 文本 = "\u001b[0;33m"+ 文本;
    }
    public static String setWenBlue(String 文本){

        return 文本 = "\u001b[0;34m"+ 文本;
    }
    public static String setWenQin(String 文本){

        return 文本 = "\u001b[0;36m"+ 文本;
    }

    public static String 设置_改变文本色(int 文本属性值, int 前景色值, int 背景色值, String 文字) {
        if (背景色值 != 0) {
            return 文字 = "\u001b[" + 文本属性值 + ";" + 前景色值 + ";" + 背景色值 + "m" + 文字;
        }
        return 文字 = "\u001b[" + 文本属性值 + ";" + 前景色值 + "m" + 文字;
    }

    public static String 获取_调色表() {
        return "文本属性\n" +
                "Value 居性\n" +
                "0 重置所有属性，恢复默认设置\n" +
                "1 粗体显示\n" +
                "4 下划线\n" +
                "5 文字闪烁\n" +
                "7 反向显示\n" +
                "8 隐藏 \n" +
                "前景色\n" +
                "value 颜色\n" +
                "30 黑\n" +
                "31 红\n" +
                "32 绿\n" +
                "33 黄\n" +
                "34 蓝\n" +
                "35 品红\n" +
                "36 青\n" +
                "37 白 \n" +
                "背景色\n" +
                "value 颜色\n" +
                "40 黑\n" +
                "41 红\n" +
                "42 绿\n" +
                "43 黄\n" +
                "44 蓝\n" +
                "45 品红\n" +
                "46 青\n" +
                "47 白 \n";
    }
    //    对用户界面进行美化的工具类
//    获取当地时间,并将此格式化
    public String 获取_时间() {
        Date 时间 = new Date();//空值则为本地时间
        SimpleDateFormat 时间格式 = new SimpleDateFormat("yyyy年MM月dd HH:mm:ss");
//        format在英文中对应格式的意思,而simple是简单的意思
        String 现在的时间 = 时间格式.format(时间);
        return 现在的时间;
    }

    //计算两次时间的间隔
    public String 获取_时间间隔(Date dtartdate, Date enddate) {
       /*
        实现对两个时间的运算
        public static void main(String[] args) {
            Date date1 = new Date(200);
            Date date2 = new Date(300);
            long a=date2.getTime()-date1.getTime();
            System.out.println(a);
        }*/
        SimpleDateFormat 时间格式 = new SimpleDateFormat("hh时mm分ss秒");
        long 时间差值 = dtartdate.getTime() - enddate.getTime();
        String 时间差值String版本 = 时间格式.format(时间差值);
        return 时间差值String版本;
    }

}
