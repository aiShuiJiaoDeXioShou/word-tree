package com.wordtree.wt_module.writing.ui;

import com.wordtree.wt_toolkit.baoExpand.MethodExpands;
import com.wordtree.wt_writing_bao.LianJie;
import com.wordtree.wt_physical.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.HashMap;

import static com.wordtree.wt_module.writing.SignInWindowkz.lianJie;

public class FeaturesUI {
    public static final JFrame jFrameY = new JFrame();
    public void GuanLiYuan() throws SQLException {
        //设置页面大小以及基本情况
        final int W = 1000;
        final int H = 600;
        jFrameY.setBounds(200, 100, W, H);
        jFrameY.setVisible(true);
        jFrameY.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        //设置菜单
        JMenuBar jMenuBar = new JMenuBar();
        JMenu jMenu = new JMenu("切换账号");
        JMenu jMenu1 = new JMenu("退出当前程序");
        jMenuBar.add(jMenu);
        jMenuBar.add(jMenu1);
        jMenu1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jFrameY.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
            }
        });

        //设置主界面
        JSplitPane sp = new JSplitPane();
        sp.setContinuousLayout(true);
        sp.setDividerLocation(200);
        sp.setDividerSize(7);
        //设置主界面上面的按键
        Panel panel = new Panel();
        Button button = new Button("添加");
        ZSGUI(button);
        Button button2 = new Button("删除");
        ZSGUI(button2,"删除");
        Button button3 = new Button("修改");
        button.setSize(50,20);
        button2.setSize(50,20);
        button3.setSize(50,20);
        panel.add(button);
        panel.add(button2);
        panel.add(button3);
        panel.setLayout(new FlowLayout(FlowLayout.LEADING));
        sp.setLeftComponent(panel);

  /*      //组装表格
//        Object[] objects = {"笔名", "用户名", "密码", "字数", "id"};
//        Object[][] objects1 =new Object[100][5];
//        HashMap<Integer, User> denRu = MethodExpands.getDenRu();
//        for (int i = 0; i < denRu.size(); i++) {
//            User user = denRu.get(i + 1);
//            objects1 = new Object[i + 1][5];
//            objects1[i][0]=user.getBiming();
//            objects1[i][1]=user.getUser();
//            objects1[i][2]=user.getPassword();
//            objects1[i][3]=user.getZishu();
//            objects1[i][4]=user.getId();
//            {user.getBiming(),user.getUser(),user.getPassword(),user.getZishu(),user.getUserId()}
//        }*/

        //组装界面
        jFrameY.setJMenuBar(jMenuBar);
//        jFrameY.add(new JScrollPane(jTable));
        Button buttonshua=new Button("刷新");
        sp.setRightComponent(new JScrollPane(BiaoGe()));
        panel.add(buttonshua);
        buttonshua.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    sp.setRightComponent(new JScrollPane(BiaoGe()));
                    jFrameY.add(sp);
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        });
        jFrameY.add(sp);

    }
    public static JTable BiaoGe() throws SQLException {
        //组装表格
        Object[] objects = {"笔名", "用户名", "密码", "字数", "id"};
        Object[][] objects1 =new Object[100][5];
        HashMap<Integer, User> denRu = MethodExpands.getDenRu();
        for (int i = 0; i < denRu.size(); i++) {
            User user = denRu.get(i + 1);
//            objects1 = new Object[i + 1][5];
            objects1[i][0]=user.getBiming();
            objects1[i][1]=user.getUser();
            objects1[i][2]=user.getPassword();
            objects1[i][3]=user.getZishu();
            objects1[i][4]=user.getId();
//            {user.getBiming(),user.getUser(),user.getPassword(),user.getZishu(),user.getUserId()}
        }
        JTable jTable = new JTable(objects1, objects);
        return jTable;
    }

    public void YonHu() {

    }

    public void ZSGUI(Button button) {
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame jFrame=new JFrame();
                JLabel jLabel=new JLabel("请输入你要添加的id:");
                JTextField jTextField=new JTextField("",20);
                JLabel jLabel2=new JLabel("笔名:");
                JTextField jTextField2=new JTextField("",20);
                JLabel jLabel3=new JLabel("用户名:");
                JTextField jTextField3=new JTextField("",20);
                JLabel jLabel4=new JLabel("密码:");
                JTextField jTextField4=new JTextField("",20);
                //添加到菜单上
                jFrame.add(jLabel);
                jFrame.add(jTextField);
                jFrame.add(jLabel2);
                jFrame.add(jTextField2);
                jFrame.add(jLabel3);
                jFrame.add(jTextField3);
                jFrame.add(jLabel4);
                jFrame.add(jTextField4);
                //确定添加
                Button button1=new Button("确定添加");
                jFrame.add(button1);
                button1.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        try {
                            lianJie.setDataTon("INSERT `基本信息表`(biming,`user`,`password`) VALUES(?,?,?)", jTextField2.getText(), jTextField3.getText(), jTextField4.getText());
                            JOptionPane.showMessageDialog(jFrame,"添加成功");
                        } catch (SQLException throwables) {
                            throwables.printStackTrace();
                        }
                    }
                });
                //进行合理的布局
                jFrame.pack();
                jFrame.setLayout(new FlowLayout(FlowLayout.LEFT));
                jFrame.setBounds(300,200,250,300);
                jFrame.setVisible(true);
                jFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
            }
        });
    }
    public void ZSGUI(Button button,String a) {
        JFrame jFrame=new JFrame();
        JLabel jLabel=new JLabel("请输入你要删除的用户名:");
        JTextField jTextField=new JTextField("",20);
        //添加到菜单上
        jFrame.add(jLabel);
        jFrame.add(jTextField);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jFrame.pack();
                jFrame.setVisible(true);
                //确定添加
                Button button1=new Button("确定删除");
                jFrame.add(button1);
                button1.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        try {
                            LianJie.setDataTon("delete from `基本信息表` where `user`=?",jTextField.getText());
                            JOptionPane.showMessageDialog(jFrame,"删除成功！");
                        } catch (SQLException throwables) {
                            throwables.printStackTrace();
                        }
                    }
                });
                //进行合理的布局
                jFrame.pack();
                jFrame.setLayout(new FlowLayout(FlowLayout.LEFT));
                jFrame.setBounds(300,200,250,300);
                jFrame.setVisible(true);
                jFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
            }
        });
    }
}
