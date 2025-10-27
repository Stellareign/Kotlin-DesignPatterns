package dogs.dogWithObserver

import dogs.Dog
import dogs.dogWithoutObserver.DogRepository
import java.awt.Font
import java.awt.Insets
import javax.swing.JFrame
import javax.swing.JScrollPane
import javax.swing.JTextArea
import kotlin.collections.joinToString

class DogDisplayWithObserver {
    private  val textArea = JTextArea().apply {
        isEditable = false
        text = "Hello, Dog"
        font = Font(Font.MONOSPACED, Font.PLAIN, 16)
        margin = Insets(10, 20, 5, 5)
    }
    fun showWindow() {

        val scroll = JScrollPane(textArea)
        JFrame().apply {
            isVisible = true
            setSize(1000, 800)
            isResizable = true
            contentPane.add(scroll)
        }
        DogRepositoryWithObserver.getInstanceDogRepository("qwertyu").addDogObserver(this)

    }
    fun dogsOnChanged(dogs: List<Dog>) {
        dogs.joinToString("\n")
            .let { textArea.text = it }
    }
}