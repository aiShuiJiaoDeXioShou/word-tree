package com.wordtree.service.service

import com.wordtree.service.entity.wt_user_base
import com.wordtree.service.entity.wt_user_data

interface UserService {
    /**
     * 登入操作
     */
    fun login(user: wt_user_base)

    /**
     * 注册
     */
    fun register(user: wt_user_base)

    /**
     * 清除账号，清除历史记录
     */
    fun accountClear(user: wt_user_base)

    /**
     * 退出登入
     */
    fun signOut(user: wt_user_base)

    /**
     * 记录这个账号的数据
     */
    fun recordData(user_data: wt_user_data)
}

