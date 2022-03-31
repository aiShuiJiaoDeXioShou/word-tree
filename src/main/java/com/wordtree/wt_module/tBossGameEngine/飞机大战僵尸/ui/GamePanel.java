package com.wordtree.wt_module.tBossGameEngine.飞机大战僵尸.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;

/*
java中的游戏面板:JPannel
自定义游戏面板
1\写一个类继承JPanel
2\写一个构造方法,初始化面板的属性
画图步骤
1\在类中定义图片并且起名
2\在构造方法中调用工具类初始化图片
3\在画图方法,paint中,画图片
* */
public class GamePanel extends JPanel {//游戏面板,简直就是笑死我了
    //定义背景图片
    BufferedImage bg;//背景图片
    //创建英雄机
    Hero hero=new Hero();
    public GamePanel(GameFrame frame) throws IOException {
        //定义背景的颜色
        setBackground(Color.lightGray);
        //初始化配筋图片
        bg=App.getImg("/img/beijin.png");
//    使用鼠标监听器,创建一个鼠标适配器
        MouseAdapter adapter=new MouseAdapter(){
          /*确定需要监听鼠标的事件
          *
          * */
            @Override
            public void mouseMoved(MouseEvent e) {
                int mx = e.getX();
                int my = e.getY();
                hero.moveToMouse(mx,my);
                //刷新界面重新调用方法
                repaint();
            }
        };
        addMouseListener(adapter);
        addMouseMotionListener(adapter);
        KeyAdapter kd =new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
//                将适配器加入到键盘当中
//                监听键盘的案件
//                每一个键盘的按键都对应有一个数组
                int keyCode= e.getKeyCode();
                if (keyCode==KeyEvent.VK_UP){//英雄向上移动

                }else if(keyCode==KeyEvent.VK_DOWN){//英雄向下移动

                } else if (keyCode==KeyEvent.VK_LEFT){//英雄向左移动

                }else if(keyCode==KeyEvent.VK_RIGHT){//英雄向右移动

                }
            }
        };
        addKeyListener(kd);
    }
//    使用键盘监听器
//    1.创建键盘适配器
//专门用的画图方法,Graphics g 画笔
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        //画图片,先画图片的x坐标,再画图片的y坐标
        g.drawImage(bg,0,0,null);
        g.drawImage(hero.img,hero.x,hero.y,hero.h,hero.w,null);
    }
}
