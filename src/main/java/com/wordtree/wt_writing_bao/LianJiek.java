package com.wordtree.wt_writing_bao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public interface LianJiek {
    Connection lianJie(String sql,String user,String password);
    void sClose(Connection connection, PreparedStatement pr, ResultSet resultSet);
    void setDataTon(String sql, Object... ars);
}
