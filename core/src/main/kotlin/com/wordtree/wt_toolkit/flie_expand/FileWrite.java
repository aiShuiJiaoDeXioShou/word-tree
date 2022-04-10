package com.wordtree.wt_toolkit.flie_expand;

import com.wordtree.wt_toolkit.uiBeautify.UIBeautify;

import java.io.*;

public class FileWrite implements Runnable {
    String cmd;
    String url;

    public FileWrite(String cmd, String url) {
        this.cmd = cmd;
        this.url = url;
    }

    @Override
    public void run() {
        Runtime rt = Runtime.getRuntime();
        FileOutputStream stream = null;
        InputStream outputStream = null;
        Process exec = null;
        try {
            //运行脚本命令
            exec = rt.exec(cmd);
            //截断脚本的执行结果
            outputStream = exec.getInputStream();
            byte[] bytes = new byte[1024];
            int read = outputStream.read(bytes);
            //将该结果写入到指定文件目录
            if (read != -1) {
                stream = new FileOutputStream(url);
                stream.write(bytes, 0, read);
            }
        }
        catch (IOException e) {
            System.out.println(UIBeautify.setWenQin("执行脚本失败，IO流异常"));
            e.printStackTrace();
        }
        //如果脚本执行失败，则输出失败的脚本异常
        catch (IndexOutOfBoundsException e) {
            coutErro(exec);
        } finally {
            try {
                assert stream != null;
                stream.close();
                outputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    //输出脚本失败结果
    private void coutErro(Process exec){
        System.out.println(UIBeautify.setWenQin("执行脚本失败"));
        assert exec != null;
        InputStream errorStream = exec.getErrorStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(errorStream));
        try {
            for(String s = reader.readLine();s != null;s = reader.readLine()){
                System.out.println(UIBeautify.setWenYellow(s));
            }
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }finally {
            try {
                errorStream.close();
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
