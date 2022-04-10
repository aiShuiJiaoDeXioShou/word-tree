package com.wordtree.wt_writing_bao;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public abstract class BaseMapper {

    public <T> List<T> getData(Class<T> clazz, String sql, Object... args) {
        Connection connection = ConnectionTools.getConnection();
        ArrayList<T> list = new ArrayList<>();
        try {

            PreparedStatement sqlObj  = connection.prepareStatement(sql);
            if (args.length > 0){
                for (int i = 1; i <= args.length; i++) {
                    sqlObj.setObject(i,args[i]);
                }
            }
            ResultSet resultSet = sqlObj.executeQuery();
            //构造实体类
            Constructor<T> constructor = clazz.getConstructor();
            Field[] fields = clazz.getDeclaredFields();
            while (resultSet.next()) {
                T t = constructor.newInstance();
                //为实体类添加数据
                for (Field field : fields) {
                    field.setAccessible(true);
                    Object dataValue = resultSet.getObject(field.getName());
                    field.set(t,dataValue);
                }
                list.add(t);
            }

        } catch (SQLException throwables) {
            System.err.println("获取数据失败");
            try {
                connection.close();
            } catch (SQLException throwables2) {
                throwables2.printStackTrace();
            }
            throwables.printStackTrace();
        } catch (NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
            try {
                connection.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            e.printStackTrace();
        }

        try {
            connection.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return list;
    }

    public <T> int exeSql(Class<T> clazz,String sql, Object... args){
        Connection connection = ConnectionTools.getConnection();
        int resInt = -1;
        try {

            PreparedStatement sqlObj  = connection.prepareStatement(sql);
            ResultSet resultSet = sqlObj.executeQuery();
            //构造实体类
            Constructor<T> constructor = clazz.getConstructor();
            T t = constructor.newInstance();
            Field[] fields = clazz.getDeclaredFields();
            if (args.length > 0){
                for (int i = 1; i <= args.length; i++) {
                    sqlObj.setObject(i,args[i]);
                }
            }
            for (Field field : fields) {
                field.setAccessible(true);
                Object dataValue = resultSet.getObject(field.getName());
                field.set(t,dataValue);
            }
            resInt = sqlObj.executeUpdate();
        } catch (SQLException throwables) {
            System.err.println("获取数据失败");
            try {
                connection.close();
            } catch (SQLException throwables2) {
                throwables2.printStackTrace();
            }
            throwables.printStackTrace();
        } catch (NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
            try {
                connection.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            e.printStackTrace();
        }

        try {
            connection.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return resInt;
    }
}
