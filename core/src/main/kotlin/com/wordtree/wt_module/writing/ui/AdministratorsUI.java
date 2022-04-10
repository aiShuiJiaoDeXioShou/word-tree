package com.wordtree.wt_module.writing.ui;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;

public class AdministratorsUI extends BaseDialogUI {
    public AdministratorsUI(){
        this.setTitle("这里是管理员界面");
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
        JButton jButtonQQ = new JButton("确定登入！");
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
                if (DenRuYt(userN, password, password2)) {
                    jieGuo.setText("登入成功！");
                    JOptionPane.showMessageDialog(this, "登入成功！");
                    this.dispose();
                    new FeaturesUI().GuanLiYuan();
                } else {
                    JOptionPane.showMessageDialog(this, "登入失败");
                    jieGuo.setText("登入失败！");
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        });
        this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
    }
    public boolean DenRuYt(String userN, String password, String password2) throws SQLException {
        String us = "管理员";
        String pass = "666666";
        return us.equals(userN) && pass.equals(password) && pass.equals(password2);
//        jieGuo.setText("登入失败！");
    }
}
