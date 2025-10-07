package org.example.multithreding

import java.lang.Thread.sleep
import kotlin.concurrent.thread
import kotlin.random.Random

fun main() {
    val myNum = readln().toInt()
    var seconds = 1
    var flag = false

    thread {
        while (!flag) {
            println("${seconds++} seconds ")
            sleep(1000)
        }
    }
    while (true) {
        val num = Random.nextInt(1, 1000_000_000)
        if (num == myNum) {
            flag = true
            println("Я угадал, это $num!")
            break
        }
    }
}