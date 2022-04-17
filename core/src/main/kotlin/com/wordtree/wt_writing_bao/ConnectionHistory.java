package com.wordtree.wt_writing_bao;
import com.wordtree.wt_physical.User;

import java.sql.*;
import java.util.HashMap;

public class ConnectionHistory {

    //    实现对数据库的连接
    public static Connection lienJie(String url, String user, String password) throws SQLException {
        Connection connection = DriverManager.getConnection(url, user, password);
        return connection;
    }
    public static Connection lienJie() throws SQLException {
        String urlx="jdbc:sqlserver://localhost:1433;DatabaseName=奥特曼";
        String usernamex="yangteng";
        String userpasswordx="yt2002";
//        Connection connection = DriverManager.getConnection(urlx, usernamex,userpasswordx);
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/奥特曼写作系统?serverTimezone=UTC", "root", "root");
//        "jdbc:mysql://localhost:3306/奥特曼写作系统?serverTimezone=UTC", "root", "root"
        System.out.println("连接成功！");
        return connection;
    }
//    关闭数据库，避免发生意外
    public static void sClose(Connection connection, PreparedStatement pr, ResultSet resultSet) throws SQLException {
        connection.close();
        pr.close();
        resultSet.close();
    }
    public static void sClose(Connection connection, PreparedStatement pr) throws SQLException {
        connection.close();
        pr.close();
    }
    public static void setSql(String sql) throws SQLException {
        Connection connection = lienJie();
        PreparedStatement pr = connection.prepareStatement(sql);
        pr.executeUpdate();
        sClose(connection,pr);
    }
    //    实现对数据库的增删改查
    public static void setDataTon(String sql, Object... ars) throws SQLException {
        Connection connection = lienJie();
        PreparedStatement pr = connection.prepareStatement(sql);
        for (int i = 0; i < ars.length; i++) {
            pr.setObject(i + 1, ars[i]);
        }
        setSql("ALTER TABLE `基本信息表` AUTO_INCREMENT =1");
        pr.executeUpdate();
        sClose(connection,pr);
    }
    public static void setDataTon(String sql, HashMap<Integer, User> map, Object... ars) throws SQLException {
        /*2.01:使用var变量进行赋值操作,在java中var是一切变量的变量
        * 可以用来修饰一切变量
        * */
//        建立对数据库的连接
        Connection connection = lienJie();
//       对sql实现封装
        PreparedStatement pr = connection.prepareStatement(sql);
//        封装之后遍历,利用遍历输入通识符号的值
        for (int i = 0; i < ars.length; i++) {
            pr.setObject(i + 1, ars[i]);
        }
//        启动对数据的控制语句pr.executeQuery();并且创建一个对象用来表示这个;创建一个对象用来储存
        ResultSet resultSet = pr.executeQuery();
        //        用resultSet获取原始数据库的一些数据,比如说数据的行数,数据库每一列的名字,用metaData封装这个对象
        ResultSetMetaData metaData = resultSet.getMetaData();
        int len=0;
        while (resultSet.next()){//判断这一行数据是否为空
            User user=new User(resultSet.getString(1),resultSet.getString(2),resultSet.getString(3),resultSet.getInt(4),resultSet.getInt(5));
            map.put(resultSet.getInt(5),user);
            len++;
//            System.out.println("第"+len+"行:");
//            如果不为空这遍历这一行数据的列数ColumnCount这个是列数的意思
//            for (int i = 0; i < metaData.getColumnCount(); i++) {
//                Object object = resultSet.getObject(i+1);
//                获取每一行对应每一个值的名字
//                String columnName = metaData.getColumnName(i + 1);
//                System.out.print(columnName+":"+object+"\t");
//            }
//            System.out.println();
        }
        sClose(connection,pr,resultSet);
    }
}
