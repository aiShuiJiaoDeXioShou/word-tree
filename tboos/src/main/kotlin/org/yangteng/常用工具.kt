package org.yangteng

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
