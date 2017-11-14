package d.kh.fan.snake

import io.kotlintest.matchers.shouldBe
import io.kotlintest.specs.BehaviorSpec

class OneLinkSnakeTest : BehaviorSpec({
    Given("one-link snake") {
        val (x, y, snake) = makeSnake()
        When("when I move it UP") {
            snake.up()
            Then("it should change Y to Y - 1") {
                snake.head().x shouldBe x
                snake.head().y shouldBe y - 1
            }
        }
    }

    Given("one-link snake") {
        val (x, y, snake) = makeSnake()
        When("when I move it DOWN") {
            snake.down()
            Then("it should change Y to Y + 1") {
                snake.head().x shouldBe x
                snake.head().y shouldBe y + 1
            }
        }
    }

    Given("one-link snake") {
        val (x, y, snake) = makeSnake()
        When("when I move it LEFT") {
            snake.left()
            Then("it should change X to X - 1") {
                snake.head().x shouldBe x - 1
                snake.head().y shouldBe y
            }
        }
    }

    Given("one-link snake") {
        val (x, y, snake) = makeSnake()
        When("when I move it RIGHT") {
            snake.right()
            Then("it should change X to X + 1") {
                snake.head().x shouldBe x + 1
                snake.head().y shouldBe y
            }
        }
    }
})

private fun makeSnake(): Triple<Int, Int, Snake> {
    val head = Point(0, 0)
    val snake = Snake(listOf(head))
    return Triple(head.x, head.y, snake)
}
