package org.yangteng

import java.text.DecimalFormat


/**
 * 控制台进度条
 */
class ConsoleProgressBar {
    private var minimum: Double = 0.0 // 进度条起始值
    private var maximum: Double = 100.0 // 进度条最大值
    private var barLen: Double = 100.0 // 进度条长度
    private var showChar = '=' // 用于进度条显示的字符
    private val formater = DecimalFormat("#.##%")

    /**
     * 使用系统标准输出，显示字符进度条及其百分比。
     */
    constructor() {}
    /**
     * 使用系统标准输出，显示字符进度条及其百分比。
     *
     * @param minimum 进度条起始值
     * @param maximum 进度条最大值
     * @param barLen 进度条长度
     * @param showChar 用于进度条显示的字符
     */
    /**
     * 使用系统标准输出，显示字符进度条及其百分比。
     *
     * @param minimum 进度条起始值
     * @param maximum 进度条最大值
     * @param barLen 进度条长度
     */
    @JvmOverloads
    constructor(
        minimum: Double, maximum: Double,
        barLen: Double, showChar: Char = '='
    ) {
        this.minimum = minimum
        this.maximum = maximum
        this.barLen = barLen
        this.showChar = showChar
    }

    /**
     * 显示进度条。
     *
     * @param value 当前进度。进度必须大于或等于起始点且小于等于结束点（start <= current <= end）。
     */
    fun show(value: Double) {
        if (value < minimum || value > maximum) {
            return
        }
        reset()
        minimum = value
        val rate = minimum / maximum
        val len = (rate * barLen).toLong()
        draw(len, rate)
        if (minimum == maximum) {
            afterComplete()
        }
    }

    private fun draw(len: Long, rate: Double) {
        print("Progress: ")
        for (i in 0 until len) {
            print(showChar)
        }
        print(' ')
        print(format(rate))
    }

    private fun reset() {
        print('\r') //光标移动到行首
    }

    private fun afterComplete() {
        print('\n')
    }

    private fun format(num: Double): String {
        return formater.format(num)
    }

    companion object {
        @Throws(InterruptedException::class)
        @JvmStatic
        fun main(args: Array<String>) {
            val cpb = ConsoleProgressBar(0.0, 100.0, 30.0, '#')
            for (i in 1..100) {
                cpb.show(i.toDouble())
                Thread.sleep(100)
            }
        }
    }
}
