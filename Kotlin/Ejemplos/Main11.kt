package main.kotlin

// And of course, we can write functions inside classes in Kotlin

class MyThirdCar(var brand: String, var model: String, var year: Int) {
    // Class function
    fun drive() {
        println("Wrooom!")
    }

    // Class function with parameters
    fun speed(maxSpeed: Int) {
        println("Max speed is: " + maxSpeed)
    }

    // Private function! cannot be called from outside
    private fun stop () {
        println("Car stopped!")
    }
}

fun main() {
    val c1 = MyThirdCar("Ford", "Mustang", 1969)

    // Call the functions
    c1.drive()
    c1.speed(200)

    // This does not work!
    // c1.stop()

}