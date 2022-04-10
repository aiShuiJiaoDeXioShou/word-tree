package com.wordtree.wt_module.writing.ui;

import com.wordtree.wt_physical.User;
import com.wordtree.wt_toolkit.baoExpand.MethodExpands;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;
import java.util.HashMap;

public class LoginUI extends BaseDialogUI {
    public LoginUI(){
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
        setLayout(new FlowLayout(FlowLayout.LEFT));
        //添加组件
        add(qqJlabel);
        add(userTxt);
        add(qqJlabelMiMa);
        add(userPossTxt);
        add(qqJlabelMiMa2);
        add(userPossTxt2);
        add(jieGuo);
        add(jButtonQQ);

        //进行事件监听
        jButtonQQ.addActionListener(e -> {
            String userN = userTxt.getText();
            String password = userPossTxt.getText();
            String password2 = userPossTxt2.getText();
            try {
                if (verification(userN, password, password2, jieGuo)) {
                    jieGuo.setText("登入成功！");
                    JOptionPane.showMessageDialog(this, "登入成功！");
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(this, "登入失败");
                    jieGuo.setText("登入失败！");
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        });
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
    }

    //验证密码的方法
    public boolean verification(String userN, String password, String password2, JTextField jieGuo) throws SQLException {
        HashMap<Integer, User> denRu = MethodExpands.getDenRu();
        for (int i = 0; i < denRu.size(); i++) {
            User user = denRu.get(i + 1);
            String us = user.getUser();
            String pass = user.getPassword();
            if (us.equals(userN) && pass.equals(password) && pass.equals(password2)) {
                jieGuo.setText("登入成功！");
                return true;
            }
        }
        return false;
    }
}
