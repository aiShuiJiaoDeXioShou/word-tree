package com.wordtree.service.tool

import org.springframework.context.ApplicationContext
import org.springframework.context.support.ClassPathXmlApplicationContext

inline fun <reified T>getSpringBean(nameBean:String): T {
    val context: ApplicationContext = ClassPathXmlApplicationContext("spring-config2.xml")
    return context.getBean(nameBean, T::class.java)
}
