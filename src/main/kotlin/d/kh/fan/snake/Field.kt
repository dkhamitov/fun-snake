package d.kh.fan.snake

class Field(private val size: Int = 10) {
    fun render(snake: Snake) {
        val head = snake.head()
        if (head.x !in 0 until size || head.y !in 0 until size) {
            throw IllegalStateException("Snake's head is outside the field")
        }
        val tail = snake.tail()
        if (tail.x !in 0 until size || tail.y !in 0 until size) {
            throw IllegalStateException("Snake's tail is outside the field")
        }

        val field = Array(size, { Array(size, { '0' }) })

        snake.forEach { p -> field[p.y][p.x] = '*' }

        field.forEach { row -> println(listOf(*row).joinToString(separator = " ")) }
    }
}