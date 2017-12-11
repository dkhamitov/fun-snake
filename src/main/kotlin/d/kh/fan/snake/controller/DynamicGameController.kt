package d.kh.fan.snake.controller

import d.kh.fan.snake.Direction
import d.kh.fan.snake.Snake
import java.util.concurrent.LinkedBlockingQueue
import java.util.concurrent.TimeUnit

class DynamicGameController(private val snake: Snake) : GameController {
    private var controlThread = Thread(controlTask())
    private var directions = LinkedBlockingQueue<Direction>()

    init {
        startControlThread()
    }

    override fun run(direction: Direction) {
        directions.add(direction)
    }

    private fun startControlThread() {
        controlThread.setUncaughtExceptionHandler({ _, ex ->
            println(ex.message)
            System.exit(1)
        })
        controlThread.start()
    }

    private fun controlTask(): () -> Unit = {
        var direction = Direction.NULL
        do {
            moveSnake(direction)
            direction = directions.poll(1000, TimeUnit.MILLISECONDS) ?: direction
        } while (true)
    }

    private fun moveSnake(direction: Direction) {
        when (direction) {
            Direction.UP -> snake.up()
            Direction.RIGHT -> snake.right()
            Direction.DOWN -> snake.down()
            Direction.LEFT -> snake.left()
            Direction.NULL -> return
        }
    }
}
