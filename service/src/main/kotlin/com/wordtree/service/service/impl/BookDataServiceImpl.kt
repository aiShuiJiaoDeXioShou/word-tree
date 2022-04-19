package com.wordtree.service.service.impl

import com.wordtree.service.entity.Book
import com.wordtree.service.entity.Chapter
import com.wordtree.service.service.BookDataService

class BookDataServiceImpl: BookDataService {
    override fun readChapters(book: String): List<Chapter> {
        TODO("Not yet implemented")
    }

    override fun readOneChapter(startChapterName: String, endChapterName: String): Chapter {
        TODO("Not yet implemented")
    }

    override fun readBook(book: String): Book {
        TODO("Not yet implemented")
    }

    override fun replaceChapter(dataString: String, targetString: String): Boolean {
        TODO("Not yet implemented")
    }

    override fun readChapterName(data: String): Array<String> {
        TODO("Not yet implemented")
    }

    override fun deleteChapter(chapter: Chapter): Boolean {
        TODO("Not yet implemented")
    }

    override fun deleteBook(book: Book): Boolean {
        TODO("Not yet implemented")
    }

    override fun addChapter(data: String): Boolean {
        TODO("Not yet implemented")
    }

    override fun addBook(data: String): Boolean {
        TODO("Not yet implemented")
    }

    override fun modifyChapterName(chapter: Chapter): Boolean {
        TODO("Not yet implemented")
    }

    override fun modifyBookName(book: Book): Boolean {
        TODO("Not yet implemented")
    }

    override fun updateChapter(chapter: Chapter): Boolean {
        TODO("Not yet implemented")
    }

    override fun updateBook(book: Book): Boolean {
        TODO("Not yet implemented")
    }
}
