package com.wordtree.wt_module.writing.ui;

import com.wordtree.wt_writing_bao.LianJie;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;

public class RegisterUI extends BaseDialogUI {
    public RegisterUI(){
        this.setTitle("这里是注册界面!");
        //用户名
        JLabel qqJlabel = new JLabel("用户名：");
        JTextField userTxt = new JTextField("", 20);

        //密码
        JLabel qqJlabelMiMa = new JLabel("密码：");
        JTextField userPossTxt = new JTextField("", 20);

        //再次确定密码
        JLabel qqJlabelMiMa2 = new JLabel("再次确认密码：");
        JTextField userPossTxt2 = new JTextField("", 20);
        //确定是否登入的文本框
        JTextField jieGuo = new JTextField("登入状态", 20);

        //登入按钮
        JButton jButtonQQ = new JButton("确定注册！");
        JButton jbuttonQ = new JButton("取消");
        jButtonQQ.setSize(40, 20);

        //进行布局
        this.setLayout(new FlowLayout(FlowLayout.LEFT));

        //添加组件
        this.add(qqJlabel);
        this.add(userTxt);
        this.add(qqJlabelMiMa);
        this.add(userPossTxt);
        this.add(qqJlabelMiMa2);
        this.add(userPossTxt2);
        this.add(jieGuo);
        this.add(jButtonQQ);

        //进行事件监听
        jButtonQQ.addActionListener(e -> {
            String userN = userTxt.getText();
            String password = userPossTxt.getText();
            String password2 = userPossTxt2.getText();
            try {
                if (password.equals(password2)) {
                    LianJie.setDataTon("INSERT `基本信息表`(`user`,`password`) VALUES(?,?)", userN, password);
                    JOptionPane.showMessageDialog(this, "注册成功！");
                    this.dispose();
                } else {
                    JOptionPane.showMessageDialog(this, "密码错误");
                }

            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        });
        this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
    }

}
