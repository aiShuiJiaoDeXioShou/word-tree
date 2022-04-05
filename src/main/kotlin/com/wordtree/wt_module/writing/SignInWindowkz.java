package com.wordtree.wt_module.writing;

import com.wordtree.wt_toolkit.flie_expand.FileToolYt;
import com.wordtree.wt_writing_bao.LianJie;
import com.wordtree.wt_module.writing.ui.AdministratorsUI;
import com.wordtree.wt_module.writing.ui.LoginUI;
import com.wordtree.wt_module.writing.ui.RegisterUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class SignInWindowkz {
    public static LianJie lianJie = new LianJie();
    public static final JFrame MAIN_FRAME = new JFrame("欢迎来到WordTree！");
    public static Integer WIDE = 800;
    public static Integer HIGH = 600;
    private final JPanel panel =new JPanel();

    //条形菜单
    public JMenuBar BarMenukz(JMenu... menu) {
        JMenuBar menubar = new JMenuBar();
        for (int i = 0; i < menu.length; i++) {
            menubar.add(menu[i]);
        }
        return menubar;
    }

    //子菜单
    public void submenuyt(JMenu menu, JMenuItem... menuItems) {
        for (int i = 0; i < menuItems.length; i++) {
            menu.add(menuItems[i]);
        }
    }

    //弹出式菜单
    public void popUpMenuyt(Component con, MenuItem... menuItem) {
        //创建右击菜单Popup是弹出的意思,Popupmenu是弹出菜单的意思
        PopupMenu pop = new PopupMenu();
        //创建基本的菜单组件
        for (int i = 0; i < menuItem.length; i++) {
            pop.add(menuItem[i]);
        }
        //将菜单组件添加到指定组件当中
        con.add(pop);
        //对鼠标点击行为进行注册监听
        con.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                boolean flag = e.isPopupTrigger();
                if (flag) {
                    pop.show(con, e.getX(), e.getY());
                }
            }
        });

    }

    //主登入界面
    public void ZhuDenRu() {
        //菜单
        JMenuBar jMenuBar = new JMenuBar();
        JMenu jMenu = new JMenu("文件");
        JMenu jMenu2 = new JMenu("编辑");
        submenuyt(jMenu, new JMenuItem("保存"), new JMenuItem("选择位置"), new JMenuItem("删除"));
        submenuyt(jMenu2, new JMenuItem("保存"), new JMenuItem("选择位置"), new JMenuItem("删除"));
        jMenuBar.add(jMenu2);
        jMenuBar.add(jMenu);
        MAIN_FRAME.setJMenuBar(jMenuBar);

        //选择用户登陆注册或者管理员登入注册
        JButton jButtonUser = new JButton("用户登陆！");
        jButtonUser.setBackground(new Color(191,191,191));
        JButton jbuttonGuan = new JButton("管理员登入");
        JButton jButtonUser2 = new JButton("用户注册！");
        jButtonUser.setSize(40, 20);
        jbuttonGuan.setSize(40, 20);
        jButtonUser2.setSize(40, 20);
        //组装登入按钮，或者注册按钮
        Box ubox = Box.createVerticalBox();
        //用户登陆
        ubox.add(jButtonUser);
        ubox.add(jButtonUser2);
        //管理员登入
        ubox.add(jbuttonGuan);

        //加载图片
        ImageIcon icon=new ImageIcon(FileToolYt.getResource("img/img_1.png"));
        //Image im=new Image(icon);
        //将图片放入label中
        JLabel label=new JLabel(icon);

        //设置label的大小
        label.setBounds(0,0,WIDE,HIGH);
        //获取frame的顶层容器,并设置为透明
        JPanel j=(JPanel)MAIN_FRAME.getContentPane();
        j.setOpaque(false);
        panel.add(ubox);
        panel.setOpaque(false);
        //获取窗口的第二层，将label放入
        MAIN_FRAME.getLayeredPane().add(label);
        MAIN_FRAME.add(panel);
        MAIN_FRAME.setSize(icon.getIconWidth(),icon.getIconHeight());
        MAIN_FRAME.setVisible(true);

        //为按钮绑定事件
        //用户登陆按钮
        jButtonUser.addActionListener(e -> {
            //用户登入
            Runnable runnable = LoginUI::new;
            runnable.run();
        });
        //用户注册按钮
        jButtonUser2.addActionListener(e -> {
            //用户登入
            Runnable runnable = RegisterUI::new;
            runnable.run();
        });
        jbuttonGuan.addActionListener(e -> {
            //用户登入
            Runnable runnable = AdministratorsUI::new;
            runnable.run();
        });
       init();
    }

    private void init(){
        MAIN_FRAME.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);//设置窗口的默认关闭方式
        MAIN_FRAME.setSize(WIDE, HIGH);//设置窗口的大小
        MAIN_FRAME.setResizable(false);//设置窗口不能变动
        MAIN_FRAME.setLocationRelativeTo(null);//设置窗口居中显示
        MAIN_FRAME.setIconImage(new ImageIcon(FileToolYt.getResource("img/icon.png")).getImage());//设置窗口图标
        MAIN_FRAME.setVisible(true);//设置窗口显示
    }
}
