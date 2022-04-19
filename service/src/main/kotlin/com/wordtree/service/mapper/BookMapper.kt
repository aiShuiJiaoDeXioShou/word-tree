package com.wordtree.service.mapper

import com.wordtree.service.entity.Book

interface BookMapper {
    fun delBook(bookId: Int)
    fun getBook(bookId: Int): Book
    fun getBooks(): List<Book>
    fun addBook(book: Book)
    fun updateBook(book: Book)
}
