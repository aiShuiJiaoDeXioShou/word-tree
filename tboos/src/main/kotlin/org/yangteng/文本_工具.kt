package org.yangteng.verification

fun 截取_x开头_y结尾的文本(startStr:String,endStr:String,target:String): String {
    val toRegex = "[$startStr]([\\s\\S]*)[$endStr]".toRegex()
    val find = toRegex.find(target)
    return find!!.value
}

fun 去除字符串_第一行_最后一行(str:String): String {
   //remove the first and last lines of the string
    val removeStart = "^.*\n".toRegex()
    val str = removeStart.replace(str, "")
    val removeEnd = ".*\n$".toRegex()
    return removeEnd.replace(str, "")
}
