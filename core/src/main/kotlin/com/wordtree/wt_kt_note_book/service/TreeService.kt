package com.wordtree.wt_kt_note_book.service

interface TreeService<T : Any?> {
    /**
     * 刷新方法
     */
    fun flush()

    /**
     * 删除自己
     */
    fun removSelf()

    /**
     * 点击事件方法
     */
    fun treeClickEvent()

    /**
     * 右击点击事件
     */
    fun rightClickOperations()

}
