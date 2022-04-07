package com.wordtree.wt_toolkit.flie_expand;

import com.google.gson.Gson;
import com.wordtree.MainKt;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Properties;

public class R {
    public static String textName(String name) {
        BufferedReader in = null;
        InputStream resourceAsStream = R.class.getClassLoader().getResourceAsStream("app.properties");
        String s = null;
        try {
            assert resourceAsStream != null;
            in = new BufferedReader(new InputStreamReader(resourceAsStream, StandardCharsets.UTF_8));
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
    public static String ImageUrl(String name)  {
        String aStatic = null;
        try {
            aStatic = R.class.getClassLoader().getResource("static/img/"+textName(name)).toString();
            System.out.println("name"+textName(name));
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            /*try {
                aStatic.close();
            } catch (IOException e) {
                e.printStackTrace();
            }*/
        }
        return aStatic;
    }

    public static InputStream ImageUrl2(String name)  {
        InputStream aStatic = null;
        try {
            aStatic = R.class.getClassLoader().getResourceAsStream("static/img/"+textName(name));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return aStatic;
    }

    public static Object propertiesItem(String key) {
        Gson gson = new Gson();
        InputStreamReader reader = null;
        Object param = null;
        try {
            InputStream resourceAsStream = MainKt.class.getClassLoader().getResourceAsStream("setting.json");
            assert resourceAsStream != null;
            reader = new InputStreamReader(resourceAsStream, StandardCharsets.UTF_8);
            HashMap<String, Object> hashMap = gson.fromJson(reader, HashMap.class);
            param = hashMap.get(key);
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
