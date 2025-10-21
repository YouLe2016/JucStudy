import java.awt.Color
import java.awt.Robot
import java.awt.event.KeyEvent
import kotlin.concurrent.thread
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

fun main() {
    val robot = Robot()
    val targetColor = Color(0x01, 0x0D, 0x07)
     val x = 790
//    val x = 860
    val y = 1040
    var count = 0

    // 创建时间格式化器
    val formatter = DateTimeFormatter.ofPattern("HH:mm:ss")

    println("开始监听屏幕坐标 ($x, $y) 的颜色是否为 #010D07 ... 当前时间: ${LocalDateTime.now().format(formatter)}")

    thread {
        while (true) {
            val color = robot.getPixelColor(x, y)
            if (color == targetColor) {
                println("检测到目标颜色 #010D07，按下 R 键，当前时间: ${LocalDateTime.now().format(formatter)}，触发次数: ${++count}")
                robot.keyPress(KeyEvent.VK_R)
                Thread.sleep(50) // 模拟按下时长
                robot.keyRelease(KeyEvent.VK_R)
                Thread.sleep(40_000) // 避免触发太快
            }
            Thread.sleep(100) // 每100ms检测一次
        }
    }.join()
}
