package inout

object Invariance {
    class Box<T>

    open class Animal

    class Dog : Animal()
    class Cat : Animal()

    fun main() {
        val animal: Box<Animal> = Box<Animal>()

        //val animal1: Box<Animal> = Box<Dog>() // error
        //val animal2: Box<Dog> = Box<Animal>() // error

        //val animal3: Box<Animal> = Box<Cat>() // error
        //val animal4: Box<Cat> = Box<Animal>() // error
    }
}
