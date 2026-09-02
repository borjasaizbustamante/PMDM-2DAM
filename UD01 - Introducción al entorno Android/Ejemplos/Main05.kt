package main.kotlin

// Kotlin also has if-else, while, when, etc. They work similar to Java

// Kotlin also supports break and continue

// Kotlin does not have the typical for (int i = 0;... ) It uses another variant for
// loop arrays etc.

fun main() {

    // IF-THEN-ELSE
    val time = 22
    if (time < 10) {
        println("Good morning.")
    } else if (time < 20) {
        println("Good day.")
    } else {
        println("Good evening.")
    }

    // WHEN, similar to Java's switch - case

    val day = 4
    val result = when (day) {
        1 -> "Monday"
        2 -> "Tuesday"
        3 -> "Wednesday"
        4 -> "Thursday"
        5 -> "Friday"
        6 -> "Saturday"
        7 -> "Sunday"
        else -> "Invalid day."
    }
    println(result)

    // WHILE

    var i = 0
    while (i < 5) {
        println(i)
        i++
    }

    // DO-WHILE

    var j = 0
    do {
        println(j)
        j++
    }
    while (j < 5)

    // FOR

    val nums = arrayOf(1, 5, 10, 15, 20)
    for (x in nums) {
        println(x)
    }
}