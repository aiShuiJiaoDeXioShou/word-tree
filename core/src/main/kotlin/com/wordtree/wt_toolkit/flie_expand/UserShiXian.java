package com.wordtree.wt_toolkit.flie_expand;

import com.wordtree.wt_physical.User;

import java.util.HashMap;
import java.util.Scanner;

public class UserShiXian extends User {
    Scanner sc = new Scanner(System.in);

    //    创建一个用户
    public void setRegistered(HashMap<String, String> map,User us) {
        System.out.println("请输入您的用户名:");
        String use = sc.next();
        us.setUser(use);
        System.out.println("请输入您的密码:");
        String mima = sc.next();
        try {
            System.out.println("请再次输入您的密码!");
            String mima2 = sc.next();
            if (mima.equals(mima2)) {
                us.setPassword(mima);
                System.out.println("创建用户名成功");
                System.out.println("请输入你的笔名!");
                String bimin1 = sc.next();
                us.setBiming(bimin1);
            } else {
                throw new Exception();
            }
        } catch (Exception e) {
            System.out.println("第二次输入的密码不正确!");
        }
        /*只有一个用户名*/
        map.put(use, mima);
    }

    //    验证用户名是否正确
    public boolean setPass(HashMap<String, String> map) {
        System.out.println("请输入您的用户名:");
        String yhname = sc.next();
        System.out.println("请输入您的密码:");
        String yonhumima = sc.next();
        return map.containsKey(yhname) || (map.get(yhname) == yonhumima) ? true : false;
    }

    //    管理员账号
    public void GuanLiY(HashMap<String, String> map) {
        map.put("管理员", "666666");
    }

    //    查看所有用户的信息
    public void getYonhu(HashMap<String, String> map) {
            map.forEach((k, v) -> {
                System.out.println("用户：" + k + "\t密码：" + v);
            });
    }
}
