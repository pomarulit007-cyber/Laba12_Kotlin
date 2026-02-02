package example
Import java.io.IO.println
import kotlin.properties.Delegates

interface Base {
    fun someFun()
}

class BaseImpl() : Base {
    override fun someFun() {}
}

class Derived(someBase: Base) : Base by someBase

interface Messenger{
    fun sendTextMessage()
    fun sendVideoMessage()
    fun send(message: String)
}

class InstantMessenger(val programName: String) : Messenger{
    override fun sendTextMessage() = println("Send Text Message")
    override fun sendVideoMessage() = println("Send Video Message")
}

class SmartPhone(val name: String, m: Messenger, p: PhotoDevice): Messenger by m, PhotoDevice by p{
    override fun sendTextMessage() = println("Send sms")
}

interface PhotoDevice {
    fun takePhoto()
}

class PhotoCamera : PhotoDevice {
    override fun takePhoto() {
        println("Take a photo")
    }
}

class SmartPhone2(
    val name: String,
    private val messenger: Messenger,
    private val camera: PhotoDevice
) : Messenger by messenger, PhotoDevice by camera

interface MessengerWithText {
    fun sendTextMessage(message: String)
    fun sendImageMessage(image: String)
}

class InstantMessenger2(val programName: String) : MessengerWithText {
    override fun sendTextMessage(message: String) {
        println("Текст сообщение отправлено через $programName: $message")
    }

    override fun sendImageMessage(image: String) {
        println("Изображение отправлено через $programName: $image")
    }
}

class SmartPhone3(
    val name: String,
    private val messenger: MessengerWithText
) : MessengerWithText by messenger {
    override fun sendTextMessage(message: String) {
        println("$name отправляет текст: $message")
    }
}

var counter: Int by Delegates.observable(initialValue = 0) { _, old, new -> println("Счётчик изменился: $old -> $new") }

class User {
    var name: String by Delegates.observable(initialValue = "<no name>") { _, old, new -> println("Имя изменено: ' $old' -> ' $new' ") }
}

fun main() {
    val max = InstantMessenger("MAX")
    val PhotoCamera = PhotoCamera()
    val yotaPhone = SmartPhone(name = "YotaPhone", m = max)
    yotaPhone.send("Hello Kotlin")
    yotaPhone.send("Learn Delegation")
    yotaPhone.takePhoto()
    yotaPhone.sendTextMessage()
    yotaPhone.sendVideoMessage()

    counter = 1
    counter = 5
}