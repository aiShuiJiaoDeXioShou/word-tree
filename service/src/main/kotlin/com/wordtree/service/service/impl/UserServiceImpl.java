package com.wordtree.service.service.impl;

import com.wordtree.service.entity.wt_user_base;
import com.wordtree.service.entity.wt_user_data;
import com.wordtree.service.service.UserService;
import org.jetbrains.annotations.NotNull;

public class UserServiceImpl implements UserService {
    @Override
    public void login(@NotNull wt_user_base user) {
        System.out.println("你好世界");
    }

    @Override
    public void register(@NotNull wt_user_base user) {
        System.out.println("你好世界");
    }

    @Override
    public void accountClear(@NotNull wt_user_base user) {
        System.out.println("你好世界");
    }

    @Override
    public void signOut(@NotNull wt_user_base user) {
        System.out.println("你好世界");
    }

    @Override
    public void recordData(@NotNull wt_user_data user_data) {
        System.out.println("你好世界");
    }
}
