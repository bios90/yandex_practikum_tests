package com.test.test

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*


private var maxBarCapacity = 0
private var maxVisitTime = 0
private lateinit var visitTimes: IntArray


fun main(args: Array<String>) {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val nums1 = reader.readLine().split(" ")
    val guestsCount = nums1[0].toInt()
    maxBarCapacity = nums1[1].toInt()
    maxVisitTime = nums1[2].toInt()
    visitTimes = IntArray(1000000 + maxVisitTime)
    val tokenizer = StringTokenizer(reader.readLine())
    for (i in 0 until guestsCount) {
        val time: Int = tokenizer.nextToken().toInt()
        checkGuest(time)
    }
}


private fun checkGuest(time: Int) {
    var canVisit = true
    var i = time
    while (i > time - maxVisitTime && i >= 0) {
        if (visitTimes[i] >= maxBarCapacity) {
            canVisit = false
            break
        }
        i--
    }
    if (!canVisit) {
        println("NO")
    } else {
        println("YES")
        var i = time
        while (i > time - maxVisitTime && i >= 0) {
            visitTimes[i] = ++visitTimes[i]
            i--
        }
    }
}

