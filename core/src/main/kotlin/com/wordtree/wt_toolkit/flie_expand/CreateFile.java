package com.wordtree.wt_toolkit.flie_expand;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class CreateFile {
    public static String fileWen;
    public static String filejia;
    public static String file;
    static Scanner sc = new Scanner(System.in);

    //    创建一个文件夹,以及文件对象
    public static void getfile() throws IOException {
        setDiZhi();
        File f2 = new File(filejia);
        f2.mkdir();
        File f1 = new File(file);
        f1.createNewFile();
        System.out.println("创建成功！");
    }

    //    输入文件的地址
    public static void setDiZhi() {
        System.out.println("请输入文件名以及对应路径比如:D:\\奥特曼\\奥特曼.txt");
        System.out.println("输入你的文件夹路径:");
        filejia = sc.next();
        System.out.println("输入你的文件名称");
        fileWen = sc.next();
        file = filejia + fileWen;
        File f3 = new File(filejia);
        File f2 = new File(file);
        if (!f3.isDirectory()) {
            System.out.println("该目录不存在!");
            System.out.println("请创建文件！");
        } else if (!f2.isFile()) {
            System.out.println("该文件不存在!");
        }
        System.out.println("已经将该文件打开！");
    }

    //    判断该对象是否存在
    public static void banduanFile(String fl) throws IOException {
        File fl2 = new File(fl);
        Date data = new Date();
//        判断指定工作区是否存在
        if (fl2.exists()) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd HH:mm:ss");
            System.out.println(sdf.format(data));
        } else {
            System.out.println("该工作取件不存在!");
            System.out.println("请重新输入:");
            setDiZhi();
        }
    }

    //    读取该文件中的内容
    public static void getWenJian(String fl) throws IOException {
        File f3 = new File(fl);
        BufferedReader br = new BufferedReader(new FileReader(f3));
        String str = null;
        while ((str = br.readLine()) != null) {//读取这一行的数据
//                输出这一行的字符
            System.out.println(str);}
        br.close();
    }

    //    写入该文件中的内容
    public static void setWenJian(String fl) throws IOException {
//        1.0版本使用字节流对数组实施操作，2.0进化为更高级的字符缓冲流
        while (true) {
            banduanFile(fl);
            OutputStream out = new FileOutputStream(fl, true);
            BufferedWriter zijieOut=new BufferedWriter(new OutputStreamWriter(out));
//           1.0版本代码 byte[] bytes = new byte[1024];
            String s = sc.next();
//           1.0版本代码 bytes = s.getBytes("UTF-8");
            zijieOut.write(s);
            zijieOut.newLine();
            if ("false".equals(s)) {
                System.out.println("已经退出写作!");
                return;
            }
            zijieOut.close();
            fuzhiWenJianJias(filejia);
        }

    }

    //    删除指定文件下面的文件夹或者是文件
    public static void shanchuWenJian() {
        System.out.println("请输入您要删除的文件");
        String fl = sc.next();
        File file = new File(fl);
        file.delete();
        if (file.exists()) {
            System.out.println("删除成功!");
        }
    }

    //   1.0 对文件进行备份
    public static void beiFenOneVersion(String fl) throws IOException {
        int i = 1;
        InputStream bf = new FileInputStream(fl);
        File file = new File(fileWen + "备份" + i);
        OutputStream bf2 = new FileOutputStream(fileWen + "备份" + i);
        file.createNewFile();
        int a = 0;
        while ((a = bf.read()) != -1) {
            bf2.write(a);
        }
        i++;
        bf.close();
        bf2.close();
    }
//    2.1版本对备份代码进行重构
    public static void  beiFen(String beifen) throws IOException {
        File beginFile=new File(beifen);
        String beginName = beginFile.getName();
        File endfile = new File(beginFile+"备份");
        System.out.println(endfile);
        byte[] bytes=new byte[1024];
        int duquBiao=0;
        //先是判断是否存在指定路径文件夹,如果指定路径不存在文件夹,则创建一个文件夹
        if(beginFile.mkdir()==false){
//            创建一份备份文件夹
            endfile.mkdir();
//            获取文件夹中的文件路径
            File[] files = beginFile.listFiles();
            for (File file1:files){
//                获取对应文件的名字
                String name = file1.getName();
//                创建一个相同名字的备份文件夹中的文件
                File endwen = new File(endfile +"\\"+name);
                endwen.createNewFile();
//                利用字节流进行读取
                BufferedInputStream bfs=new BufferedInputStream(new FileInputStream(file1));
                BufferedOutputStream bf2 = new BufferedOutputStream(new FileOutputStream(endwen));
                while ((duquBiao=bfs.read(bytes))!=-1){
                    bf2.write(bytes,0,duquBiao);
                }
//                最后关闭程序并且更新
                bfs.close();
                bf2.close();
                System.out.println("已经将该文件夹备份!");
            }
        }else {
            System.out.println("已经自动帮您创建一个文件夹!");
        }
    }
//    2.2版本对备份代码进行重构
    public static void  fuzhiWenJianJias(String file) throws IOException {
//        创建一个路径
    File filekai=new File(file);
//        判断路径是否存在,如果不存在则创建一个文件夹,存在对文件夹进行复制
    if (!filekai.exists()){
        filekai.mkdir();
        System.out.println("已经帮你创建好了文件夹,不用谢!");
    }else {
//            获取开始文件总目录的名字
        String filekaiName = filekai.getName();
//            创造一个备份文件总目录
        File fileBeiFen=new File(file+"备份");
        fileBeiFen.mkdir();
//            获取开始文件总目录里面的所有东西
        File[] ListfilesKaishi = filekai.listFiles();
//            遍历开始文件总目录里面的所有东西
        for (File fileBian:ListfilesKaishi){
//                打印里面文件的路径
            System.out.println(fileBian);
//            判断是否为文件夹
            if (fileBian.isDirectory()){
//                    如果是,则创建在备份总目录下面的文件夹
                String file3Name = fileBian.getName();
                File fileBeiFen2=new File(fileBeiFen,file3Name);
                fileBeiFen2.mkdir();
                fuzhiTons(fileBian,fileBeiFen2);
            }else {
//                    如果不是则对复制总目录进行一个复制
                fuzhiTon(fileBian,fileBeiFen);
            }
        }
    }

}
    public static void  fuzhiTon(File file, File fileBeifen) throws IOException {
        byte[] bytes=new byte[1024];
        int kaishi=0;
        BufferedInputStream bfs=new BufferedInputStream(new FileInputStream(file));
        String fileName = file.getName();
        File file1=new File(fileBeifen,fileName);
        BufferedOutputStream bf2 = new BufferedOutputStream(new FileOutputStream(file1));
        if ((kaishi=bfs.read(bytes))!=-1) {
            bf2.write(bytes,0,kaishi);
        }
        bfs.close();
        bf2.close();
    }

    public static void  fuzhiTons(File file,File fileBeifen) throws IOException {
        File[] listFiles = file.listFiles();
        byte[] bytes = new byte[1024];
        int kaishi=0;
        for (File listFile : listFiles) {
            BufferedInputStream bfs=new BufferedInputStream(new FileInputStream(listFile));
            String listFileName = listFile.getName();
            File backupsFlieSrc = new File(fileBeifen,listFileName);
            BufferedOutputStream bf2 = new BufferedOutputStream(new FileOutputStream(backupsFlieSrc));
            if ((kaishi=bfs.read(bytes))!=-1) {
                bf2.write(bytes,0,kaishi);
            }
            bfs.close();
            bf2.close();
        }
    }

}
