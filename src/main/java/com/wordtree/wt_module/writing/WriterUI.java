package com.wordtree.wt_module.writing;

import com.wordtree.wt_physical.User;
import com.wordtree.wt_toolkit.flie_expand.CreateFile;
import com.wordtree.wt_toolkit.flie_expand.OperatingString;
import com.wordtree.wt_toolkit.flie_expand.UserShiXian;
import com.wordtree.wt_toolkit.baoExpand.MethodExpands;
import com.wordtree.wt_toolkit.uiBeautify.UIBeautify;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.Scanner;

/*项目开发,写一个关于写作的项目
 * 0.项目名称叫做奥特曼写作,主要写一些有关于赞美奥特曼星球的作文
 * 1.他的一些功能:在里面写作,对数据进行备份,储存到txt文件当中
 *2.注册账号,管理员实现对账号的增删改查,注册账号登入之后能看到自己写的一些信息
 * 3.对自己的文章进行修改,对自己文章的字符进行统计计算
 * 4.项目分层,有工具包,实现包,UI用户交流包
 * 5.工具包的实现思路:工具包里基本的开发思路为实现一个文件夹的创建,实现对数据的储存,实现对数据的增删改查
 *
 * 6.版本迭代:
 *
 * 1.1：
 * 0.1.修复了一些小bug
 * 0.2.自五月二十七日开始每一周对一个小版本更新
 *
 * 奥特曼故事本2.0:
 * 0.1.新增了数据库的连接，新增对控制台信息的美化包UIBeautify
 *
 * 2.1：
 * 0.1.新增全局变量var优化代码的使用问题，新增加管理员查询修改删除，修改底层代码逻辑，使得对文件的备份能用了,增加多种文件夹复制模式
 * 增加游戏引擎工具包（com.wordtree.wTmodule.writing.tBossGameEngine），更改项目名称改为WordTree（世界树的意思）
 *
 * 2.2：
 * 0.1.新增文件上传与下载，重构项目将项目分为不同模块，新增聊天室业务模块，添加接口类，将项目独立出ytidea
 * 0.2.版本增加对图形化UI支持的工具类
 *
 * 2.3:
 * 0.1.以Swing组件的方式,重写了登入UI,写作界面UI,实现了对数据库的连接
 * */
public class WriterUI implements Runnable{
    static CreateFile uldomm = new CreateFile();
    static OperatingString op = new OperatingString();
    static Scanner sc=new Scanner(System.in);
    static User us = new User();
    static UserShiXian useq = new UserShiXian();
    static UIBeautify ubf=new UIBeautify();
    static Date date1=new Date();
    static Date enddate=new Date();

    public void loginInterface() throws IOException, SQLException {
//        前期准备区
        HashMap<String, String> YonHumap = new HashMap<String, String>();
        HashMap<String, String> GLmap = new HashMap<String, String>();
        boolean banduan2021 = true;
//      代码区
        System.out.println("-------------欢迎来到奥特曼写作世界:----------------");
        while (banduan2021) {
            System.out.println("    1.登入账号，2.注册账号，3.管理员登入,4.退出");
            int xz = sc.nextInt();
            switch (xz) {
                case 1:
                    if (useq.setPass(YonHumap)) {
//                        System.out.println("亲爱的" +us.getBiming() );
//                        System.out.println("您在过去的时间里面一共打了" + us.getZishu());
//                        OperatingString.tonJi(CreateFile.file);
                        userInterface();
                        writingInterface();
                    } else {
                        System.out.println("登入失败，用户名或者密码错误！");
                    }
                    break;
                case 2:
                    System.out.println("注册您的账号:");
                    useq.setRegistered(YonHumap,us);
                    break;
                case 3:
                    useq.GuanLiY(GLmap);
                    if (useq.setPass(GLmap)) {
                        queryUI();
                    } else {
                        System.out.println("登入失败，用户名或者密码错误！");
                    }
                    break;
                case 4:
                    continue;
            }
        }
    }

    public void writingInterface() throws IOException {//规划视窗
        boolean banduan = true;
        Scanner sc = new Scanner(System.in);
//        写作的操作界面
        System.out.println("奥特曼写作操作界面:");
        System.out.println("指令:创建项目文件,打开项目文件,写作,查看文字,统计文字数据,退出输入false,统计字符串种类");
        while (banduan) {
            System.out.println("指令模式:");
            String name = sc.next();
            switch (name) {
                case "创建项目文件":
                    CreateFile.getfile();
                    break;
                case "打开项目文件":
                    CreateFile.setDiZhi();
                    break;
                case "写作":
                    date1.getTime();
                    System.out.println("写作模式");
                    CreateFile.setWenJian(CreateFile.file);
//                      CreateFile.beiFen(CreateFile.file);
                    enddate.getTime();
                    break;
                case "查看文字":
                    CreateFile.getWenJian(CreateFile.file);
                    break;
                case "删除文件":
                    CreateFile.shanchuWenJian();
                    break;
                case "统计文字数据":
                    OperatingString.countString(CreateFile.file);
                    break;
                case "统计字符串种类":
                    OperatingString.tonJi(CreateFile.file);
            }
            if ("false".equals(name)) {
                banduan = false;
            }
        }
    }
    /*写一个用户登入之后时时可以看到，并且在刷新的界面，界面的显示内容：
    * 写作的时间
    * 写作的次数
    * 写作的字数
    * 笔名
    * */
    public void userInterface(){
        System.out.println("现在是北京时间:"+ubf.获取_时间());
        System.out.println("-------- 你好,"+us.getBiming()+"! -----------");
        System.out.println("         你一共码了:\t"+op.getZishu());
        System.out.println("         您一共码了:"+ubf.获取_时间间隔(date1,enddate));
        System.out.println(" ");
        System.out.println("---------------------------------------------");
    }

    public void queryUI() throws SQLException {
        MethodExpands methodExpands=new MethodExpands();
        System.out.println("请选择你要使用的方法:");
        System.out.println("1.添加用户信息\n2.删除用户信息\n3.修改用户信息\n4.查看所有用户信息\n5.查询单个用户信息");
        int select查询UI=sc.nextInt();
        switch (select查询UI){
            case 1:
                methodExpands.setBao();
                break;
            case 2:
                methodExpands.setBaoShan();
                break;
            case 3:
                methodExpands.setXiu();
                break;
            case 4:
                methodExpands.getAll();
                break;
            case 5:
                methodExpands.getSingle();
                break;
        }
        }

    @Override
    public void run() {
        try {
            loginInterface();
        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }
    }
}
