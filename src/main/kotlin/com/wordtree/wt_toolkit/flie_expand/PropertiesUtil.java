package com.wordtree.wt_toolkit.flie_expand;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesUtil {

    /**
     * 获取Properties对象
     * @return
     */
    public static Properties getProperties(){
        Properties properties = new Properties();
        InputStream inputStream = null;
        try {
            //data.properties在resources目录下
            inputStream = PropertiesUtil.class.getClassLoader().getResourceAsStream("app.properties");
            properties.load(inputStream);
        } catch (FileNotFoundException e) {
            System.out.println("data.properties文件未找到!");
        } catch (IOException e) {
            System.out.println("出现IOException");
        } finally {
            try {
                if (null != inputStream){
                    inputStream.close();
                }
            } catch (IOException e) {
                System.out.println("data.properties文件流关闭出现异常");
            }
        }
        return properties;
    }

    /**
     * 根据key查询value值
     * @param key key
     * @return
     */
    public static String getValue(String key){
        Properties properties = getProperties();
        String value = properties.getProperty(key);
        return value;
    }

    /**
     * 新增/修改数据
     * @param key
     * @param value
     */
    public static void setValue(String key, String value){
        Properties properties = getProperties();
        properties.setProperty(key, value);
        //此处获取的路径是target下classes
        FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream = new FileOutputStream(PropertiesUtil.class.getClassLoader().getResource("app.properties").getFile());
            properties.store(fileOutputStream, "注释");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != fileOutputStream){
                    fileOutputStream.close();
                }
            } catch (IOException e) {
                System.out.println("data.properties文件流关闭出现异常");
            }
        }
    }

    public static void main(String[] args) {
        String password = PropertiesUtil.getValue("aoteman");
        System.out.println(password);
        PropertiesUtil.setValue("password","123456");
        String password1 = PropertiesUtil.getValue("password");
        System.out.println(password1);
    }

}
