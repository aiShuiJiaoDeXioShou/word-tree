package com.wordtree.wt_writing_bao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionTools {
    private static Connection connection = null;

    public static Connection getConnection(){
        if (connection == null){
            try {
                connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/books2","root","root");
                System.out.println(connection);
            } catch (SQLException throwables) {
                System.err.println("数据库链接失败");
                throwables.printStackTrace();
            }
        }
        return connection;
    }

    public static void close(){
        try {
            connection.close();
        } catch (SQLException throwables) {
            System.err.println("关闭数据库连接失败");
            throwables.printStackTrace();
        }
    }
}
