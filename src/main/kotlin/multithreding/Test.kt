package org.example.multithreding

import kotlin.concurrent.thread

fun main() {
    /**
     * Запуск нового потока (классический способ)
     */
    val newThread = Thread {
        repeat(100_000) {
            print(" 0 ")
            Thread.sleep(100)
        }
    }.start()

    /**
     * Запись вызова по-другому, нет необходимости вызывать через старт, поток запустится автоматически
     * это самый просто способ вызвать новый поток в Kotlin
     */

    thread {
        repeat(100_000) {
            print(" 1 ")
        }
        Thread.sleep(100)
    }

    repeat(100_000) {
        print(" * ")
    }

}