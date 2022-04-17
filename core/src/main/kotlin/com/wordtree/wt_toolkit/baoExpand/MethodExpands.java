package com.wordtree.wt_toolkit.baoExpand;

import com.wordtree.wt_physical.User;
import com.wordtree.wt_writing_bao.ConnectionHistory;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Scanner;

public class MethodExpands extends User {
    ConnectionHistory mEConnectionHistory =new ConnectionHistory();
    //    实现用户操作对数据的添加，删除修改
    public static Scanner input = new Scanner(System.in);
    public static void setBao() throws SQLException {
        ConnectionHistory connectionHistory = new ConnectionHistory();
        System.out.println("请输入添加笔名:");
        String biming = MethodExpands.input.next();
        System.out.println("请输入添加用户名");
        String user = MethodExpands.input.next();
        System.out.println("添加字数信息:");
        String zishu = MethodExpands.input.next();
        connectionHistory.setDataTon("INSERT `基本信息表`(biming,`user`,`password`,zishu) VALUES(?,?,?)", biming, user, zishu);
    }
    public static void setBaoShan() throws SQLException {
        System.out.println("请输入您要删除的用户名:");
        String user = MethodExpands.input.next();
        ConnectionHistory.setDataTon("delete from `基本信息表` where `user`=？", user);
    }
    public static void setXiu() throws SQLException {
        System.out.println("请输入您要修改之后的笔名:");
        String houuser = MethodExpands.input.next();
        System.out.println("请输入您要修改的笔名:");
        String user = MethodExpands.input.next();
        ConnectionHistory.setDataTon("UPDATE `基本信息表` SET biming=? WHERE `user`=?", houuser, user);
    }
    //    查看所有
    public static HashMap getAll() throws SQLException {
        HashMap map = new HashMap();
        ConnectionHistory.setDataTon("SELECT * FROM `基本信息表`", map);
        return map;
    }
    public static void getSingle() throws SQLException {
        HashMap map = new HashMap();
        System.out.println("请输入您的用户名:");
        String user = MethodExpands.input.next();
        ConnectionHistory.setDataTon("SELECT * FROM `基本信息表` WHERE `user`=?", map, user);
        System.out.println(map);
    }
    //返回一个数组对象
    public static HashMap<Integer,User> getDenRu() throws SQLException{
        HashMap<Integer,User> map = new HashMap<>();
        ConnectionHistory.setDataTon("SELECT * FROM `基本信息表`",map);
//        System.out.println(map);
        return map;
    }

    public static void main(String[] args) throws SQLException {
        getSingle();
    }
}
