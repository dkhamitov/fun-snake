package d.kh.fan.snake.controller

import d.kh.fan.snake.Direction

class StaticGameController(val renderAction: () -> Unit, val moveAction: (Direction) -> Unit) : GameController {
    init {
        renderAction()
    }

    override fun run(direction: Direction) {
        moveAction(direction)
        renderAction()
    }
}