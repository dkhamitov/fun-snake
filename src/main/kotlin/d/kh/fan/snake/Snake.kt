package d.kh.fan.snake

class Snake(chain: List<Point> = mutableListOf(Point(0, 0))) : Iterable<Point> {
    private val chain: MutableList<Point> = chain.toMutableList()

    fun head() = chain.first()

    fun tail() = chain.last()

    fun length() = chain.size

    fun up() {
        move(Point(chain.first().x, chain.first().y - 1))
    }

    fun down() {
        move(Point(chain.first().x, chain.first().y + 1))
    }

    fun left() {
        move(Point(chain.first().x - 1, chain.first().y))
    }

    fun right() {
        move(Point(chain.first().x + 1, chain.first().y))
    }

    override fun iterator() = chain.iterator()

    private fun move(head: Point) {
        if (length() > 1 && (head == chain.second() || chain.dropLast(1).contains(head))) {
            throw IllegalStateException("No bump allowed")
        }
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