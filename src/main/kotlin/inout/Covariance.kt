package inout

object Covariance {
    // Use out for OUTput parameters
    class Box<out T>

    open class Animal

    class Dog : Animal()
    class Cat : Animal()

    fun main() {
        val animal: Box<Animal> = Box<Animal>()

        val animal1: Box<Animal> = Box<Dog>()
        //val animal2: Box<Dog> = Box<Animal>() // error

        val animal3: Box<Animal> = Box<Cat>()
        //val animal4: Box<Cat> = Box<Animal>() // error
    }

    object Example {

        interface Animal {
            fun pet()
        }

        class Cat(val name: String) : Animal {
            override fun pet() {
                println("$name says Meow")
            }
        }

        class Dog(val name: String) : Animal {
            override fun pet() {
                println("$name says Wuff")
            }
        }

        @JvmStatic
        fun main(args: Array<String>) {
            /**
             * Remember Definition of List:
             * public interface List<out E> : Collection<E>
             */
            val animals: List<Animal> = listOf<Cat>(Cat("Mitzi"), Cat("Mutzi"))
            animals.forEach { it.pet() }

            /**
             * Remember Definition of MutableList:
             * public interface MutableList<E> : List<E>, MutableCollection<E>
             */
            val mutableAnimals: MutableList<Cat> = mutableListOf<Cat>(Cat("Mitzi"), Cat("Mutzi")) // error
            // addDog(mutableAnimals) // error
            // Cat cat = mutableAnimals.last() // if previous step would compile run will fail here!
        }

        fun addDog(animals: MutableList<Animal>) {
            animals.add(Dog("Wauwi"))
        }


    }
}
