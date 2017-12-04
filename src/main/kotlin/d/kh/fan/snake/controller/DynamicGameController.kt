package d.kh.fan.snake.controller

import d.kh.fan.snake.Direction
import d.kh.fan.snake.Field
import d.kh.fan.snake.Snake
import java.util.Timer
import kotlin.concurrent.scheduleAtFixedRate

class DynamicGameController(private val field: Field, private val snake: Snake) : GameController {
    private var direction = Direction.NULL
    private var timer = Timer()

    init {
        render()
        rescheduleTask()
    }

    override fun run(direction: Direction) {
        this.direction = direction
        rescheduleTask()
    }

    private fun doRun() {
        when (direction) {
            Direction.UP -> snake.up()
            Direction.RIGHT -> snake.right()
            Direction.DOWN -> snake.down()
            Direction.LEFT -> snake.left()
            Direction.NULL -> return
        }
        render()
    }

    private fun render() {
        field.render(snake)
    }

    private fun scheduleTask() {
        timer.scheduleAtFixedRate(0, 1_000, { doRun() })
    }

    private fun rescheduleTask() {
        timer.cancel()
        timer = Timer()
        scheduleTask()
    }
}