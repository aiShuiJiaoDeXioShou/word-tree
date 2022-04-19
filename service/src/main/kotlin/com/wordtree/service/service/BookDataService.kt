package com.wordtree.service.service

import com.wordtree.service.entity.Book
import com.wordtree.service.entity.Chapter

/**
 * Created by rajeevkumarsingh on 02/08/17.<br>
 *
 * Project - WordTree <br/>
 *
 * Package - com.wordtree.service.service<br>
 *
 * 这是一个特殊的服务类，直接读取文件的内容，还有修改文件的功能，它直接对数据库进行更新。
 *
 * 书籍信息服务<br>
 */
interface BookDataService {
    /**
     * 读取一本书所有的章节
     */
    fun readChapters(book:String):List<Chapter>

    /**
     * 读取一个章节
     */
    fun readOneChapter(startChapterName:String,endChapterName: String):Chapter

    /**
     * 读取一本书籍
     */
    fun readBook(book: String):Book

    /**
     * 替换目标字符串，如果替换成功，则返回为true
     */
    fun replaceChapter(dataString: String,targetString: String):Boolean

    /**
     * 获取所有的章节名
     */
    fun readChapterName(data:String):Array<String>

    /**
     * 删除一章,删除成功则返回值为true
     */
    fun deleteChapter(chapter: Chapter):Boolean

    /**
     * 删除一本书籍,删除成功则返回值为true
     */
    fun deleteBook(book: Book):Boolean

    /**
     * 添加一个章节,添加成功，则返回值为true
     */
    fun addChapter(data: String):Boolean

    /**
     * 添加一本书籍,添加成功，则返回值为true
     */
    fun addBook(data:String):Boolean

    /**
     * 修改一个章节信息，如果修改成功，则返回值为true
     */
    fun modifyChapterName(chapter: Chapter):Boolean

    /**
     * 修改一本书籍信息，如果修改成功，则返回值为true
     */
    fun modifyBookName(book:Book):Boolean

    /**
     * 更新一个章节，更新成功，则返回值为true
     */
    fun updateChapter(chapter: Chapter):Boolean

    /**
     * 更新一本书籍，更新成功，则返回值为true
     */
    fun updateBook(book: Book):Boolean
}
