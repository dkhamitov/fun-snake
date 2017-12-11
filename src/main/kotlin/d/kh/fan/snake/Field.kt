package d.kh.fan.snake

class Field(private val size: Int = 10, private val snake: Snake) {
    fun render() {
        validate(snake)

        val field = Array(size, { Array(size, { '0' }) })

        snake.forEach { p -> field[p.y][p.x] = '*' }

        clearField()

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

    private fun clearField() {
        val control = charArrayOf(27.toChar(), '[', '2', 'J')
        val console = System.console()
        console.writer().print(control)
        console.flush()
    }
}