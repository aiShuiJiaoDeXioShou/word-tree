package com.wordtree.wt_module.tBossGameEngine.飞机大战僵尸.ui;

import java.awt.image.BufferedImage;
import java.io.IOException;

//被玩家操控的战斗机
//属性对应特点,方法对应行为
public class Hero {
    //英雄机的图片
    BufferedImage img;
    int x;//横坐标
    int y;//纵坐标
    int w;//飞机的宽度
    int h;//飞机的高度
    //构造方法给对象定型
    public Hero() throws IOException {
//        在构造方法中写东西能直接放到另外方法中
        //确定英雄机开始时显示的图片
        img = App.getImg("/img/20190310203728690.png");
    //使用横纵坐标,操控飞机的位置
        x=200;
        y=500;
        //去顶操控飞机大小的宽度和高度
        w=img.getWidth();
        h=img.getHeight();
    }
    public void moveToMouse(int mx,int my){
        x=mx-w/2;
        y=my-h/2;
        System.out.println(mx);
    }
}
