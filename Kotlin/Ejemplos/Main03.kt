package main.kotlin

// Kotlin also has variables like Java.

// The difference between var and val is that variables declared with the var CAN be changed/modified,
// while val variables CANNOT. Val is NOT similar to static in Java. Kotlin does NOT use static

// You do not need to specify the variable type. Kotlin can guess it.

fun main() {

    val name: String = "John"
    val birth = 1975

    println("Hello " + name)
    println("Birth Date " + birth)
}