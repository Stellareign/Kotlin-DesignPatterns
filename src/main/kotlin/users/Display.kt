package users

import java.awt.Dimension
import java.awt.Font
import java.awt.Insets
import javax.swing.JFrame
import javax.swing.JScrollPane
import javax.swing.JTextArea

class Display {
    fun showWindow() {
        val textArea = JTextArea().apply {  // создание текстового поля
            isEditable = false // запрет на ввод текста в окне (или разрешение, если true)
            text = "Hello World"
            font = Font(Font.MONOSPACED, Font.PLAIN, 16)  // тип и размер шрифта
            margin = Insets(12, 10, 2, 5) // отступы
        }

        val scrollPane = JScrollPane(textArea) // компонент для скроллинга текста, выходящего за границы экрна
        JFrame().apply { //создание и настройка окна
            isVisible = true
            size = Dimension(600, 600)
            isResizable = false // запрет на изменение размера окна
            add(scrollPane) //добавление текстового поля внутрь окна, который можно скроллить
        }
//      val repository =UserRepository.getInstanceUserRepository("qwerty")
//        val users = repository.users
//        val stringBuilder = StringBuilder()
//        for (user in users) {
//            stringBuilder.append(user).append("\n")
//        }
//        val content = stringBuilder.toString()
//        textArea.text = content
        /**
         * Запись в функциональном стиле
         */
        UserRepository
            .getInstanceUserRepository("qwerty")
            .users.joinToString("\n") // преобразование в сроку с символом переноса
            .let { textArea.text = it }
    }
}