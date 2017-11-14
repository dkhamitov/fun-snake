package d.kh.fan.snake

class Field(private val size: Int = 10) {
    fun render(snake: Snake) {
        validate(snake)

        val field = Array(size, { Array(size, { '0' }) })

        snake.forEach { p -> field[p.y][p.x] = '*' }

        field.forEach { row -> println(listOf(*row).joinToString(separator = " ")) }
    }

    private fun validate(snake: Snake) {
        validateBorders(snake.head(), "head")
        validateBorders(snake.tail(), "tail")
    }

    private fun validateBorders(point: Point, what: String) {
        if (point.x !in 0 until size || point.y !in 0 until size) {
            throw IllegalStateException("Snake's $what is outside the field")
        }
    }
}