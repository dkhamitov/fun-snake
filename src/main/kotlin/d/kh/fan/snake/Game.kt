@file:JvmName("Game")

package d.kh.fan.snake

import d.kh.fan.snake.reader.Key
import d.kh.fan.snake.reader.KeyReaderImpl
import java.io.ByteArrayOutputStream

fun main(args: Array<String>) {
    setupConsole()
    val keyReader = KeyReaderImpl()

    val field = Field(10)
    val snake = Snake()

    do {
        clearScreen()
        field.render(snake)

        val key = keyReader.read(System.console().reader())
        when (key) {
            Key.QUIT -> return
            Key.UP -> snake.up()
            Key.RIGHT -> snake.right()
            Key.DOWN -> snake.down()
            Key.LEFT -> snake.left()
        }
    } while (true)
}

private fun clearScreen() {
    val control = charArrayOf(27.toChar(), '[', '2', 'J')
    val console = System.console()
    console.writer().print(control)
    console.flush()
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