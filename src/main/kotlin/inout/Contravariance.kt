package inout

object Contravariance {
    // Use in for INput parameters
    class Box<in T>

    open class Animal

    class Dog : Animal()
    class Cat : Animal()

    fun main() {
        val animal: Box<Animal> = Box<Animal>()

        //val animal1: Box<Animal> = Box<Dog>() // error
        val animal2: Box<Dog> = Box<Animal>()

        //val animal3: Box<Animal> = Box<Cat>() // error
        val animal4: Box<Cat> = Box<Animal>()
    }

    object Example {

        interface Sender<in T : Message> {
            fun send(message: T)
        }

        interface Message {
            fun getPayload(): String
        }

        class Order

        interface OrderMessage : Message
        class AddOrder(val order: Order): OrderMessage {
            override fun getPayload(): String {
                return "AddOrder-Message"
            }
        }

        class CancelOrder(val orderId: String): OrderMessage {
            override fun getPayload(): String {
                return "CancelOrder-Message"
            }
        }

        interface InvoiceMessage : Message
        class MakeInvoice(val order: Order): InvoiceMessage {
            override fun getPayload(): String {
                return "MakeInvoice-Message"
            }
        }

        class GeneralSender : Sender<Message> {
            override fun send(message: Message) {
                println(message.getPayload())
            }

        }

        @JvmStatic
        fun main(args: Array<String>) {
            val orderSender: Sender<OrderMessage> = GeneralSender()
            orderSender.send(AddOrder(Order()))
            orderSender.send(CancelOrder("someOrderId"))

            val invoiceSender: Sender<InvoiceMessage> = GeneralSender()
            invoiceSender.send(MakeInvoice(Order()))
        }
    }
}
