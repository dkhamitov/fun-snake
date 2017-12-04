package d.kh.fan.snake.reader

import java.io.Reader

class KeyReaderImpl : KeyReader {
    private val buffer: CharArray = CharArray(4)

    override fun read(console: Reader): Key {
        while (true) {
            val num = console.read(buffer)
            val key = when (num) {
                1 -> if (buffer.first() == 113.toChar()) Key.QUIT else null
                3 -> if (buffer.first() == 27.toChar()) {
                    when (String(buffer, 1, 2)) {
                        "[A" -> Key.UP
                        "[B" -> Key.DOWN
                        "[C" -> Key.RIGHT
                        "[D" -> Key.LEFT
                        else -> null
                    }
                } else null
                else -> null
            }
            if (key != null) {
                return key
            }
        }
    }
}