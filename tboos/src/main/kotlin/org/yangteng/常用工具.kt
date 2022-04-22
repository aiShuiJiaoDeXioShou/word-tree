package org.yangteng

import java.io.InputStream

var bol = false
fun 切换(start:()->Unit, target:()->Unit){
    if (bol){
        bol = !bol
        start()
    }else {
        bol = !bol
        target()
    }
}

fun <T>获取当前目录路径(clazz:Class<T>,name:String): InputStream {
    return clazz.getResourceAsStream("$name")
}
