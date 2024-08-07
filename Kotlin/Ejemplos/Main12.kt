package main.kotlin

// Open is used when inheritance to allow a class be inherited by others or
// a function to be overridden.

// Superclass, marked as open to allow inheritance
open class MyParentClass {
    val x = 5
    val y = 10
}

// Subclass, marked as open to allow inheritance
open class MyChildClass: MyParentClass() {

    // This function will be inherited as is, and the child class
    // cannot override it
    fun myFunctionOne() {
        println(x) // x is now inherited from the superclass
    }

    // This function is open, so it CAN be overridden
    open fun myFunctionTwo() {
        println(y) // x is now inherited from the superclass
    }
}

// This does not work if MyChildClass is not open
class MyGrandChildClass: MyChildClass() {

    // Error! We cannot override a non-open function
    // fun myFunctionOne() { }

    // Let's override it...
    override fun myFunctionTwo() {
        println("Override! $y") // x is now inherited from the superclass
    }

}

// Create an object of MyChildClass and call myFunction
fun main() {
    val myObj = MyGrandChildClass()
    myObj.myFunctionOne()
    myObj.myFunctionTwo()
}