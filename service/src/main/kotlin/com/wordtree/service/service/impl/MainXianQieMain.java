package com.wordtree.service.service.impl;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

@Aspect
public class MainXianQieMain {

    @Before("execution(* com.wordtree.service.service.impl.UserServiceImpl.*(..))")
    public void before(){
        System.out.println("在指定接口执行前使用该方法");
    }

    @After("execution(* com.wordtree.service.service.impl.UserServiceImpl.*(..))")
    public void hour(){
        System.out.println("在指定接口执行后使用该方法");
    }
}
