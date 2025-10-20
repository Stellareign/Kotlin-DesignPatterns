package dogs

import java.awt.Font
import java.awt.Insets
import java.awt.TextArea
import javax.swing.JFrame
import javax.swing.JScrollPane
import javax.swing.JTextArea

class DogDisplay {
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
        DogRepository
            .getInstanceDogRepository("qwertyu")
            .dogs.joinToString("\n")
            .let{textArea.text = it}
    }
}