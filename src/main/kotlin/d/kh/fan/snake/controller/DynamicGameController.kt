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
            println("Game over: ${ex.message}")
            System.exit(1)
        })
        controlThread.isDaemon = true
        controlThread.start()
    }

    private fun controlTask(): () -> Unit = {
        var direction = directions.take()
        var count = 0
        val interval = 10
        val rate0 = 1_000L
        val step = 50L
        do {
            action(direction)
            val rate = rate0 - count++ / interval * step
            direction = directions.poll(rate, TimeUnit.MILLISECONDS) ?: direction
        } while (true)
    }
}
