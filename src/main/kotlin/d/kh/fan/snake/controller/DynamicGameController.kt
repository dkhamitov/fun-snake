package d.kh.fan.snake.controller

import d.kh.fan.snake.Direction
import java.util.concurrent.LinkedBlockingQueue
import java.util.concurrent.TimeUnit

class DynamicGameController(private val action: (Direction) -> Unit) : GameController {
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
        var direction = directions.take()
        do {
            action(direction)
            direction = directions.poll(1000, TimeUnit.MILLISECONDS) ?: direction
        } while (true)
    }
}
