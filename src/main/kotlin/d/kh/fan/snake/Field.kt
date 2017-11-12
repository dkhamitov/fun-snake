package d.kh.fan.snake

class Field(private val size: Int = 10) {
    fun render(snake: Snake) {
        val head = snake.head()
        if (head.x < 0 || head.y < 0) {
            throw IllegalStateException("Snake's head is outside the field")
        }
        val tail = snake.tail()
        if (tail.x > size || tail.y > size) {
            throw IllegalStateException("Snake's tail is outside the field")
        }

        val field = Array(size, { Array(size, { 0 }) })

        snake.forEach { p -> field[p.y][p.x] = 1 }

        field.forEach { row -> println(listOf(*row).joinToString(separator = " ")) }
    }
}