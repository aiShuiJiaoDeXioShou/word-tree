package com.wordtree.wt_toolkit.flie_expand;

import com.google.gson.Gson;
import com.wordtree.MainKt;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Properties;

public class R {
    public static String getPropertie(String name) {
        Properties properties = getProperties("app.properties");
        return properties.getProperty(name);
    }

    public static boolean addProperty(String key,String value) {
        return updateProperty(key,value);
    }

    public static boolean updateProperty(String key,String value) {
        Properties properties = getProperties("app.properties");
        Object o = properties.setProperty(key, value);
        FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream = new FileOutputStream(R.class.getClassLoader().getResource("app.properties").getFile());
            properties.store(fileOutputStream,"注释");
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                fileOutputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return o != null;
    }

    public static boolean removeProperty(String key){
        Properties properties = getProperties("app.properties");
        Object o = properties.remove(key);
        return o != null;
    }

    private static Properties getProperties(String name){
        BufferedReader in = null;
        InputStream resourceAsStream = R.class.getClassLoader().getResourceAsStream(name);
        Properties p = null;
        try {
            assert resourceAsStream != null;
            in = new BufferedReader(new InputStreamReader(resourceAsStream, StandardCharsets.UTF_8));
            p = new Properties();
            p.load(in);
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
        return p;
    }

    public static String ImageUrl(String name)  {
        String aStatic = null;
        try {
            aStatic = R.class.getClassLoader().getResource("static/img/"+ getPropertie(name)).toString();
            System.out.println("name"+ getPropertie(name));
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
            aStatic = R.class.getClassLoader().getResourceAsStream("static/img/"+ getPropertie(name));
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

    public static void main(String[] args) {

    }

}
