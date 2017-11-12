package d.kh.fan.snake

import io.kotlintest.matchers.shouldBe
import io.kotlintest.specs.StringSpec

class SnakeTest : StringSpec() {
    init {
        "Y coordinate of one-link snake's head should change to Y - 1 when moving UP" {
            val snake = Snake()
            snake.up()
            snake.head().x shouldBe 0
            snake.head().y shouldBe -1
        }

        "Y coordinate of one-link snake's head should change to Y + 1 when moving DOWN" {
            val snake = Snake()
            snake.down()
            snake.head().x shouldBe 0
            snake.head().y shouldBe 1
        }

        "X coordinate of one-link snake's head should change to X - 1 when moving LEFT" {
            val snake = Snake()
            snake.left()
            snake.head().x shouldBe -1
            snake.head().y shouldBe 0
        }

        "X coordinate of one-link snake's head should change to X + 1 when moving RIGHT" {
            val snake = Snake()
            snake.right()
            snake.head().x shouldBe 1
            snake.head().y shouldBe 0
        }
    }
}