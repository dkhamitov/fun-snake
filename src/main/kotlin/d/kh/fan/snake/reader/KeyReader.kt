package d.kh.fan.snake.reader

import java.io.Reader

interface KeyReader {
    fun read(console: Reader): Key
}