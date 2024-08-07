package main.kotlin

import java.io.File
import java.io.BufferedReader

fun main(args: Array<String>) {
    val path = "C:\\Trastero\\"
    val bufferedReader: BufferedReader = File(path + "example.txt").bufferedReader()
    val inputString = bufferedReader.use { it.readText() }
    println(inputString)
}