package d.kh.fan.snake.controller

import d.kh.fan.snake.Direction

class StaticGameController(private val action: (Direction) -> Unit) : GameController {
    override fun run(direction: Direction) {
        action(direction)
    }
}