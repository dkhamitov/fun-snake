package d.kh.fan.snake.reader

import java.io.Reader

class KeyReaderImpl(private val console: Reader) : KeyReader {
    private val buffer: CharArray = CharArray(4)

    override fun read(): Key {
        while (true) {
            val num = console.read(buffer)
            val key = when {
                num == 1 && buffer.first() == 113.toChar() -> Key.QUIT
                num == 3 && buffer.first() == 27.toChar() -> parseArrowKey(String(buffer, 1, 2))
                else -> null
            }
            if (key != null) {
                return key
            }
        }
    }

    private fun parseArrowKey(key: String): Key? {
        return when (key) {
            "[A" -> Key.UP
            "[B" -> Key.DOWN
            "[C" -> Key.RIGHT
            "[D" -> Key.LEFT
            else -> null
        }
    }
}