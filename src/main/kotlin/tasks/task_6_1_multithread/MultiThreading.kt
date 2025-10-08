package org.example.tasks.task_6_1_multithread

import sun.net.www.content.text.plain
import kotlin.concurrent.thread

fun main() {
    val threads = ThreadRunner()
    threads.runThreads().forEach { println(it) }
}

class ThreadRunner {
    fun runThreads(): Map<String, String> {
        val threadInfo = mutableMapOf<String, String>()

        // Добавьте в Map имя главного потока и описание его работы
        threadInfo["${Thread.currentThread().name}"] = "Главный поток, который управляет выполнением"

        // Запустите три потока, добавляя в Map имя потока и описание его работы,
        val thread1 = thread { threadInfo["${Thread.currentThread().name}"] = "Выполняет вычисления 1" }
        val thread2 = thread { threadInfo["${Thread.currentThread().name}"] = "Выполняет вычисления 2" }
        val thread3 = thread { threadInfo["${Thread.currentThread().name}"] = "Выполняет вычисления 3" }

        // Дождитесь завершения потоков, чтобы они успели записать свои имена в Map
        thread1.join()
        thread2.join()
        thread3.join()

        return threadInfo
    }
}