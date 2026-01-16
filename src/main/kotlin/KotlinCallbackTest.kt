import kotlin.concurrent.thread

fun main() {
    var i = 0
    val t1 = thread(start = false) {
        println("t1: i = $i")
    }
    println("main: i = $i")
    i = 2
    println("main: i = $i")
    Thread.sleep(1000)
    t1.start()
}