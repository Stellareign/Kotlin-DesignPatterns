package dogs.dogWithMutableObservableInt_6_10

import java.awt.Font
import java.awt.Insets
import javax.swing.JFrame
import javax.swing.JScrollPane
import javax.swing.JTextArea
import kotlin.collections.joinToString

class DogDisplayWithObserver_6_10 {

    fun showWindow() {
        val textArea = JTextArea().apply {
            isEditable = false
            text = "Hello, Dog"
            font = Font(Font.MONOSPACED, Font.PLAIN, 16)
            margin = Insets(10, 20, 5, 5)
        }

        val scroll = JScrollPane(textArea)
        JFrame().apply {
            isVisible = true
            setSize(1000, 800)
            isResizable = true
            contentPane.add(scroll)
        }
        DogRepositoryWithObserver_6_10.getInstanceDogRepository("qwertyu").dogs.registerObserver { dogs -> // после создания функционального интерфейса можно заменить на лямбда-выражение
            dogs.joinToString("\n")
                .let { textArea.text = it }
        }
    }
}