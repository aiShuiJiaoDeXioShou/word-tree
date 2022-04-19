package com.wordtree.service.mapper

import java.sql.Connection
import java.sql.DriverManager

//连接sqlite数据库
val connection:Connection by lazy {
    DriverManager.getConnection("jdbc:sqlite:wordtree1.db")
}
