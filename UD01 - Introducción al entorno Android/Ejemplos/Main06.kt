package main.kotlin

// Kotlin also supports arrays. It works mostly like Java

fun main() {

    val cars = arrayOf("Volvo", "BMW", "Ford", "Mazda")

    println("Number of cars: " + cars.size)
    println(cars[0])
    println(cars[1])
    println(cars[2])
    println(cars[3])

    print("Volvo exists? ")
    if ("Volvo" in cars) {
        println("It exists!")
    } else {
        println("It does not exist.")
    }
}