package d.kh.fan.snake.reader

import com.nhaarman.mockito_kotlin.*
import io.kotlintest.matchers.shouldBe
import io.kotlintest.specs.FeatureSpec
import java.io.Reader

class KeyReaderImplTest : FeatureSpec({
    feature("""KeyReader reads keys pressed in the console and returns Key relevant for
               the game while ignoring the rest of the keyboard""") {
        scenario("Letter 'q' should map to Key.QUIT") {
            //given
            val console = mock<Reader>()
            doAnswer {
                val buffer = it.getArgument<CharArray>(0)
                buffer[0] = 113.toChar()
                1
            }.whenever(console).read(any<CharArray>())

            //when
            val keyReader = KeyReaderImpl(console)

            //then
            keyReader.read() shouldBe Key.QUIT
            verify(console, times(1)).read(any<CharArray>())
        }

        scenario("UP arrow key should map to Key.UP") {
            arrowKeysScenario('A', Key.UP)
        }

        scenario("DOWN arrow key should map to Key.DOWN") {
            arrowKeysScenario('B', Key.DOWN)
        }

        scenario("RIGHT arrow key should map to Key.RIGHT") {
            arrowKeysScenario('C', Key.RIGHT)
        }

        scenario("LEFT arrow key should map to Key.LEFT") {
            arrowKeysScenario('D', Key.LEFT)
        }
    }
})

private fun arrowKeysScenario(thirdChar: Char, expectedKey: Key): Unit {
    //given
    val console = mock<Reader>()
    doAnswer {
        val buffer = it.getArgument<CharArray>(0)
        buffer[0] = 27.toChar()
        buffer[1] = '['
        buffer[2] = thirdChar
        3
    }.whenever(console).read(any<CharArray>())

    //when
    val keyReader = KeyReaderImpl(console)

    //then
    keyReader.read() shouldBe expectedKey
    verify(console, times(1)).read(any<CharArray>())
}