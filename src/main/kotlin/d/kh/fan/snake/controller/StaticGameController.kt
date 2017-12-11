package d.kh.fan.snake.controller

import d.kh.fan.snake.Direction
import d.kh.fan.snake.Snake

class StaticGameController(val snake: Snake) : GameController {
    override fun run(direction: Direction) {
        when (direction) {
            Direction.UP -> snake.up()
            Direction.RIGHT -> snake.right()
            Direction.DOWN -> snake.down()
            Direction.LEFT -> snake.left()
            Direction.NULL -> return
        }
    }
}