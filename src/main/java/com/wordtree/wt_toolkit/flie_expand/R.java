package com.wordtree.wt_toolkit.flie_expand;

import com.google.gson.Gson;
import com.wordtree.Main;

import java.io.*;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.HashMap;
import java.util.Objects;
import java.util.Properties;

public class R {
    public static String textName(String name) {
        BufferedReader in = null;
        String path = R.class.getClassLoader().getResource("app.properties").getPath();
        String s = null;
        try {
            in = new BufferedReader(new InputStreamReader(new FileInputStream(path), StandardCharsets.UTF_8));
            Properties p = new Properties();
            p.load(in);
            s = p.getProperty(name);
        } catch (IOException e) {
            System.err.println("读取文件app.properties操作失败！！！");
            e.printStackTrace();
        } finally {
            try {
                in.close();
            } catch (IOException e) {
                System.out.println("读取文件，关闭操作失败！！！");
                e.printStackTrace();
            }
        }
        return s;
    }
    public static String ImageUrl(String name){
        String path = null;
        try {
            path = Objects.requireNonNull(R.class.getClassLoader().getResource("static")).toURI().toString();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return path+"img/"+textName(name);
    }
    public static Object propertiesItem(String key) {
        Gson gson = new Gson();
        InputStreamReader reader = null;
        Object param = null;
        try {
            reader = new InputStreamReader(new FileInputStream(Main.class.getClassLoader().getResource("setting.json").getFile()));
            HashMap<String, Object> hashMap = gson.fromJson(reader, HashMap.class);
            param = hashMap.get(key);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return param;
    }
}
