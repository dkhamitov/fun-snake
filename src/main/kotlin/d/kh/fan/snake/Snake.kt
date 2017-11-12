package d.kh.fan.snake

import d.kh.fan.snake.Direction.*

class Snake(chain: List<Point> = mutableListOf(Point(0, 0))) : Iterable<Point> {
    private val chain: MutableList<Point> = chain.toMutableList()

    fun head() = chain.first()

    fun tail() = chain.last()

    fun size() = chain.size

    fun up() {
        validateStepBack(UP)
        move(Point(chain.first().x, chain.first().y - 1))
    }

    fun down() {
        validateStepBack(DOWN)
        move(Point(chain.first().x, chain.first().y + 1))
    }

    fun left() {
        validateStepBack(LEFT)
        move(Point(chain.first().x - 1, chain.first().y))
    }

    fun right() {
        validateStepBack(RIGHT)
        move(Point(chain.first().x + 1, chain.first().y))
    }

    override fun iterator() = chain.iterator()

    private fun validateStepBack(direction: Direction) {
        if (chain.size == 1) {
            return
        }
        val stepBack = when (direction) {
            UP -> chain.first().x == chain.second().x && chain.first().y > chain.second().y
            DOWN -> chain.first().x == chain.second().x && chain.first().y < chain.second().y
            LEFT -> chain.first().y == chain.second().y && chain.first().x > chain.second().x
            RIGHT -> chain.first().y == chain.second().y && chain.first().x < chain.second().x
        }
        if (stepBack) {
            throw IllegalStateException("Step $direction not allowed in current state")
        }
    }

    private fun move(head: Point) {
        chain.removeAt(chain.lastIndex)
        chain.add(0, head)
    }
}

private fun <T> List<T>.second(): T {
    if (size < 2) {
        throw NoSuchElementException("List size is less than 2")
    }
    return this[1]
}