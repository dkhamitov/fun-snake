package d.kh.fan.snake.controller

import d.kh.fan.snake.Direction

interface GameController {
    fun run(direction: Direction)
}