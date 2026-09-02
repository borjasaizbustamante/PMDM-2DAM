package main.kotlin

// Kotlin uses constructors like Java. This code is equivalent to
// the Main09.kt

class MyOtherCar(var brand: String, var model: String, var year: Int)

fun main() {
    val c1 = MyOtherCar("Ford", "Mustang", 1969)

    // This initialization does not work!

    //val c2 = MyOtherCar()
}