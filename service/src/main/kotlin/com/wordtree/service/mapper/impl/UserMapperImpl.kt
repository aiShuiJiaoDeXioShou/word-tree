package com.wordtree.service.mapper.impl

import com.wordtree.service.entity.wt_user_base
import com.wordtree.service.mapper.UserMapper
import com.wordtree.service.tool.getSpringBean
import org.springframework.jdbc.core.BeanPropertyRowMapper
import org.springframework.jdbc.core.JdbcTemplate


class UserMapperImpl: UserMapper {
    private val jdbcTemplate: JdbcTemplate = getSpringBean("session")
    override fun getUser(id: Int): wt_user_base {
        val wtUserBase = jdbcTemplate.queryForObject("select * FROM wt_user_base WHERE user_id = $id", BeanPropertyRowMapper(wt_user_base::class.java))
        return wtUserBase
    }

    override fun addUser(user: wt_user_base): Boolean {
        jdbcTemplate.execute("insert into wt_user_base(user_name,user_img_url,user_autograph) values(${user.user_name},${user.user_img_url},${user.user_autograph})")
        return true
    }

    override fun updateUser(user: wt_user_base): Boolean {
        TODO("Not yet implemented")
    }

    override fun deleteUser(id: Int): Boolean {
        TODO("Not yet implemented")
    }
}

fun main() {
    val userMapper = UserMapperImpl()
    val user = userMapper.getUser(1)
    println(user)
}
