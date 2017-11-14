package d.kh.fan.snake

import io.kotlintest.matchers.shouldThrow
import io.kotlintest.specs.FeatureSpec

class MultiLinkSnakeTest : FeatureSpec({
    feature("multi-link snake") {
        scenario("snake is directed to UP side and I move it one step DOWN") {
            val snake = Snake(listOf(Point(0, 0), Point(0, 1)))
            shouldThrow<IllegalStateException> {
                snake.down()
            }
        }

        scenario("snake is directed to DOWN side and I move it one step UP") {
            val snake = Snake(listOf(Point(0, 1), Point(0, 0)))
            shouldThrow<IllegalStateException> {
                snake.up()
            }
        }

        scenario("snake is directed to LEFT side and I move it one step RIGHT") {
            val snake = Snake(listOf(Point(0, 0), Point(1, 0)))
            shouldThrow<IllegalStateException> {
                snake.right()
            }
        }

        scenario("snake is directed to RIGHT side and I move it one step LEFT") {
            val snake = Snake(listOf(Point(1, 0), Point(0, 0)))
            shouldThrow<IllegalStateException> {
                snake.left()
            }
        }
    }
})
