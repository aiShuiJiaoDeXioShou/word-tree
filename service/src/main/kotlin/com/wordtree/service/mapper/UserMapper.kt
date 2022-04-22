package com.wordtree.service.mapper

import com.wordtree.service.entity.wt_user_base

interface UserMapper {
    /**
     * 获取一个用户
     */
    fun getUser(id: Int): wt_user_base

    /**
     * 添加一个用户
     */
    fun addUser(user: wt_user_base): Boolean

    /**
     * 更新一个用户
     */
    fun updateUser(user: wt_user_base): Boolean

    /**
     * 删除一个用户
     */
    fun deleteUser(id: Int): Boolean
}
