package com.wordtree.wt_module.tBossGameEngine.飞机大战僵尸.ui;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

/*
处理图片的工具类
工具类中的写法:攻击类中一般都会吧需要重复使用的代码抽离出来,定义成工具方法
工具类中的方法一般需要加上static
工具类能提高自己代码的复用性
* */
public class App {
    //读取指定位置的图片
    public static BufferedImage getImg(String path) throws IOException {
        //加载图片
        InputStream is;
        BufferedImage img = ImageIO.read(App.class.getResource(path));//导入变量,能使方法的使用性变高
       //加载到图片之后返回值为img
        return img;
    }

}
