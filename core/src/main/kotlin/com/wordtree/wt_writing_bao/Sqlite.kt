package com.wordtree.wt_writing_bao

import java.sql.Connection
import java.sql.DriverManager

//连接sqlite数据库
fun connectSqlite(): Connection {
    val driver = "org.sqlite.JDBC"
    val url = "jdbc:sqlite:wordtree1.db"
    Class.forName(driver)
    return DriverManager.getConnection(url)
}
