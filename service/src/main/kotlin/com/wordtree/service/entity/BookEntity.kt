package com.wordtree.service.entity

//章节信息
data class Chapter(var id:Int = 0,
                   var title:String = "",
                   var chapterIntroduction:String = "",
                   var content:String = "",
                   var number: Int = 0,
                   var bookOwnership:String = "",
                   var bookId:Int = 0)

//书籍信息
data class Book(var id:Int = 0,
                var title: String = "",
                var imgUrl: String = "",
                var introduction:String = "",
                var chapters: List<Chapter> = ArrayList(),
                var number: Int = 0,
                var authorId:Int = 0,
                var bookCategory:String = "")

//书籍数据信息
data class BookData(var bookId:Int = 0,
                    var hits:Long = 0,
                    var pay:Long = 0,
                    var price:Double = 0.0,
                    var labels:Array<String> = arrayOf())

//作者信息
data class Author(var id:Int = 0,
                  var name:String = "",
                  var imgUrl:String = "",
                  var introduction:String = "",
                  var books:List<Book> = ArrayList())

//作者数据信息
data class AuthorData(var pay: Long = 0,
                      var fans: Long = 0,
                      var hits:Long = 0,
                      var labels:Array<String> = arrayOf(),
                      var grade:Int = 0)
