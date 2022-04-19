package com.wordtree.service.mapper

import com.wordtree.service.entity.Chapter

interface ChapterMapper {
    fun addChapter(chapter: Chapter): Int
    fun updateChapter(chapter: Chapter): Int
    fun getChapterById(id: Int): Chapter
    fun getChapterByName(name: String): Chapter
    fun getChapterByBookId(bookId: Int): List<Chapter>
    fun deleteChapter(id: Int): Int
}
