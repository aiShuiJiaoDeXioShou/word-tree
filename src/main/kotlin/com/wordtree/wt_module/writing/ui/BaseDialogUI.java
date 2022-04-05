package com.wordtree.wt_module.writing.ui;

import com.wordtree.wt_module.writing.SignInWindowkz;

import javax.swing.*;

public abstract class BaseDialogUI extends JFrame {
    public BaseDialogUI(){
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);//设置点击x窗口默认隐藏
        this.setSize(SignInWindowkz.WIDE/2,SignInWindowkz.HIGH/2);
        this.setLocationRelativeTo(null);//设置窗口居中显示
        this.setVisible(true);
    }

}
