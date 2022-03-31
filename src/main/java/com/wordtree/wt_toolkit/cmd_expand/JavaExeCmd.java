package com.wordtree.wt_toolkit.cmd_expand;
import com.wordtree.wt_toolkit.flie_expand.FileWrite;

import java.io.*;

public class JavaExeCmd {

    public static void run(String cmd){
        Runtime rt = Runtime.getRuntime();
        BufferedReader br = null;
        BufferedInputStream in = null;
        BufferedReader inBr = null;
        try {
            Process p = rt.exec(cmd);
            String lineStr = "";
            String line = null;
            StringBuilder sb = new StringBuilder();
            br = new BufferedReader(new InputStreamReader(p.getErrorStream()));
            in = new BufferedInputStream(p.getInputStream());
            inBr = new BufferedReader(new InputStreamReader(in));
            while ((lineStr = inBr.readLine()) != null)
                //获得命令执行后在控制台的输出信息
                System.out.println(lineStr);// 打印输出信息
            //检查命令是否执行失败。
            if (p.waitFor() != 0) {
                if (p.exitValue() == 1)//p.exitValue()==0表示正常结束，1：非正常结束
                    System.err.println("命令执行失败!");
            }
            while ((line = br.readLine()) != null) {
                sb.append(line).append("\n");
                System.out.println(sb);
            }
            System.out.println(sb);//打印执行后的结果
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                assert inBr != null;
                inBr.close();
                in.close();
                br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public static void runFile(String cmd,String url) {
        FileWrite fileWrite = new FileWrite(cmd, url);
        fileWrite.run();
    }
}
