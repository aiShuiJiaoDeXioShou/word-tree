package com.wordtree.service.entity

//章节信息
data class Chapter(val id:Int = 0,
                   val title:String = "",
                   val chapterIntroduction:String = "",
                   val content:String = "",
                   val number: Int = 0,
                   val bookOwnership:String = "",
                   val bookId:Int = 0)

//书籍信息
data class Book(val id:Int = 0,
                val title: String = "",
                val imgUrl: String = "",
                val introduction:String = "",
                val chapters: List<Chapter> = ArrayList(),
                val number: Int = 0,
                val authorId:Int = 0,
                val bookCategory:String = "")

//书籍数据信息
data class BookData(val bookId:Int = 0,
                    val hits:Long = 0,
                    val pay:Long = 0,
                    val price:Double = 0.0,
                    val labels:Array<String> = arrayOf())

//作者信息
data class Author(val id:Int = 0,
                  val name:String = "",
                  val imgUrl:String = "",
                  val introduction:String = "",
                  val books:List<Book> = ArrayList())

//作者数据信息
data class AuthorData(val pay: Long = 0,
                      val fans: Long = 0,
                      val hits:Long = 0,
                      val labels:Array<String> = arrayOf(),
                      val grade:Int = 0)
