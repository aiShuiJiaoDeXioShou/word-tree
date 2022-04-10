package com.wordtree.wt_toolkit.netEngine;

import com.wordtree.wt_physical.User;

import java.io.*;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class NetEngine extends User implements Runnable{
    static Test test=new Test();
    //获取本机ip地址,并将它储存在数据库中
    public static String getWTipAdd(User netUser) throws UnknownHostException {
        netUser.setUserId(InetAddress.getLocalHost().getHostAddress());
        return InetAddress.getLocalHost().getHostAddress();
    }
    public static String getWTipAdd() throws UnknownHostException {
        return InetAddress.getLocalHost().getHostAddress();
    }
    public static String getWTHost(User netUser) throws UnknownHostException {
        netUser.setUserHost(InetAddress.getLocalHost().getHostName());
        return InetAddress.getLocalHost().getHostName();
    }
    public static String getWTHost() throws UnknownHostException {
        return InetAddress.getLocalHost().getHostName();
    }
    public static InetAddress getwtHostAndIp(User netUser) throws UnknownHostException {

        return InetAddress.getLocalHost();
    }

    //实现对文件的上传服务器
    public static void fileServer(String hostname,int port,String filename) throws IOException {
        Socket fuwusocket=new Socket(hostname,port);
        InputStream in=new FileInputStream(filename);
        BufferedReader reader=new BufferedReader(new InputStreamReader(in));
        BufferedWriter writer=new BufferedWriter(new OutputStreamWriter(fuwusocket.getOutputStream()));
        String len=null;
        while ((len=reader.readLine())!=null){
            writer.write(len);
            writer.newLine();
            writer.flush();
        }
        writer.write("无内鬼可以结束");
        writer.newLine();
        writer.flush();
        //给出反馈
        BufferedReader reader1=new BufferedReader(new InputStreamReader(fuwusocket.getInputStream()));
        System.out.println("服务器的反馈："+reader1.readLine());
        fuwusocket.close();
        reader.close();
        writer.close();
    }

    //实现对文件的下载客户端
    public static void fileClient(int port,String filename) throws IOException {
        ServerSocket client=new ServerSocket(10068);
        Socket accept = client.accept();
        System.out.println("连接成功！");
        InputStream in=accept.getInputStream();
        BufferedReader reader=new BufferedReader(new InputStreamReader(in));
        BufferedWriter writer=new BufferedWriter(new OutputStreamWriter(new FileOutputStream(filename,true)));
        String len=null;
        while ((len=reader.readLine())!=null){
            if ("无内鬼可以结束".equals(len)){
                break;
            }
            writer.write(len);
            writer.newLine();
            writer.flush();
        }
        //给出反馈
        BufferedWriter writer1=new BufferedWriter(new OutputStreamWriter(accept.getOutputStream()));
        writer1.write("文件上传成功！");
        writer1.newLine();
        writer1.flush();
        //释放资源
        reader.close();
        writer.close();
        client.close();
        accept.close();
    }

    //实现网络通讯
    public static void WTQQ() throws IOException {
        String len=null;
        DatagramSocket qqData=new DatagramSocket();
        Scanner input=new Scanner(System.in);
        while (!"qq".equals(len)){
             len = input.next();
            byte[] s=len.getBytes(StandardCharsets.UTF_8);
            DatagramPacket qqPacket=new DatagramPacket(s, s.length,InetAddress.getByName(getWTipAdd()),12300);
            qqData.send(qqPacket);
        }
        qqData.close();
        test.setBanduan(false);
    }
    public static void WTQQJ() throws IOException {
        DatagramSocket qqData = qqData=new DatagramSocket(12300);
        while (test.isBanduan()){
            byte[] bytes=new byte[1024];
            DatagramPacket qqPacket=new DatagramPacket(bytes,bytes.length);
            qqData.receive(qqPacket);
            System.out.println(new String(qqPacket.getData(),0,qqPacket.getLength()));
        }
    }


    public static void main(String[] args) throws IOException {
        NetEngine qq=new NetEngine();
        Thread thread=new Thread(qq,"qq");
        thread.start();
    }

    @Override
    public void run() {
        try {
            WTQQ();
        } catch (IOException e) {
            System.out.println("连接失败！");
        }
    }

    static class qqj implements Runnable {
        public static void main(String[] args) {
            qqj qqjx=new qqj();
            Thread thread=new Thread(qqjx,"qqj");
            thread.start();
        }

        @Override
        public void run() {
            try {
                WTQQJ();
            } catch (IOException e) {
                System.out.println("臭宝");
            }
        }
    }
}
