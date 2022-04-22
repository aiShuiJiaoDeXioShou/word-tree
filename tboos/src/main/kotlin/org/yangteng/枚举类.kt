package org.yangteng

enum class 按钮_类型{
    无,警告,绿色,蓝色,红色,黑暗
}

data class 评论(val comment:String,val image:String,val commentChildren: List<评论> = listOf(),val userName:String)
