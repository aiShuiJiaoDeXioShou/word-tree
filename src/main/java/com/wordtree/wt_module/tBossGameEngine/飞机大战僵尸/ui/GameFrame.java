package com.wordtree.wt_module.tBossGameEngine.飞机大战僵尸.ui;

import javax.swing.*;
import java.io.IOException;

/*
游戏中的窗体
java中的窗体类:JFrame游戏的窗体是重复的模式
自定义窗体的步骤:
1、写一个类去继承JFrame
2、写一个构造方法，初始化窗体的属性
属性对应特点，方法对应行为
* */
public class GameFrame extends JFrame {//游戏中的窗体
//继承JFrame表示GameFrame变成了一个窗体
    //构造方法的作用 给对象做定型
    public GameFrame() {//用构造方法对子类进行定型,设置屏幕的宽和高,设置对象的名字
        //设置标题
        setTitle("谏山创大战孝子");//直接继承JFrame
        //设置大小,设置宽高,简直就是笑死我了
        setSize(480,852);
        //设置窗体位置位置居中
        setLocationRelativeTo(null);
        //设置窗体界面大小,简直就是又要笑死我了,不允许玩家改变界面大小
//        re在英文里面常常表示重复或者重新的意思,siz代表大小,able表示一种可能性
        setResizable(false);
//        设置默认的关闭选项,设置关闭窗体的时候退出程序
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) throws IOException {
//1定义类型,本腾也来复习一下  2内存地址简直就是笑死我了 3加new的才是对象简直就是笑死我了
        GameFrame frame=new GameFrame();
        //创建面板对象
        GamePanel panel=new GamePanel(frame);
//        显示窗体简直就是笑死我了
        frame.setVisible(true);
//        将面板加入到窗体当中
        frame.add(panel);

    }
}
