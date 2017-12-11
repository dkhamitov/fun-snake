@file:JvmName("Game")

package d.kh.fan.snake

import d.kh.fan.snake.controller.DynamicGameController
import d.kh.fan.snake.reader.Key
import d.kh.fan.snake.reader.KeyReaderImpl
import java.io.ByteArrayOutputStream

fun main(args: Array<String>) {
    setupConsole()
    val keyReader = KeyReaderImpl(System.console().reader())

    val snake = Snake(listOf(Point(4, 5), Point(5, 5), Point(6, 5), Point(6, 6), Point(6, 7)))
    val field = Field(size = 15, snake = snake)
    field.render()

    val action: (Direction) -> Unit = {
        when (it) {
            Direction.UP -> snake.up()
            Direction.RIGHT -> snake.right()
            Direction.DOWN -> snake.down()
            Direction.LEFT -> snake.left()
        }
        field.render()
    }
//    val controller = StaticGameController(action)
    val controller = DynamicGameController(action)
    do {
        val key = keyReader.read()
        when (key) {
            Key.QUIT -> return
            Key.UP -> controller.run(Direction.UP)
            Key.RIGHT -> controller.run(Direction.RIGHT)
            Key.DOWN -> controller.run(Direction.DOWN)
            Key.LEFT -> controller.run(Direction.LEFT)
        }
    } while (true)
}

fun setupConsole() {
    val initialConfig = runCmd("-g")
    runCmd("-icanon min 1 -icrnl -inlcr -ixon")
    runCmd("dsusp undef")
    runCmd("-echo")
    Runtime.getRuntime().addShutdownHook(Thread({
        runCmd(initialConfig)
        runCmd("echo")
    }))
}

fun runCmd(cmd: String): String {
    val shell = arrayOf("sh", "-c", "stty $cmd < /dev/tty")
    val bout = ByteArrayOutputStream()
    val p = Runtime.getRuntime().exec(shell)
    val ins = p.inputStream
    val errs = p.errorStream
    ins.copyTo(bout)
    errs.copyTo(bout)
    p.waitFor()
    return bout.toString()
}