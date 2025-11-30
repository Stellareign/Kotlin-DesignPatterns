package users.usersWithObserver_6_6

import Observers.Observer
import users.User
import java.awt.Dimension
import java.awt.Font
import java.awt.Insets
import javax.swing.JFrame
import javax.swing.JScrollPane
import javax.swing.JTextArea


class UserDisplayForObserver : Observer<List<User>> {
    /**
     * Настройка тестового содержания
     */
    private val textArea = JTextArea().apply {
        isEditable = false // запрет на ввод текста в окне (или разрешение, если true)
        font = Font(Font.MONOSPACED, Font.PLAIN, 16)  // тип и размер шрифта
        margin = Insets(12, 10, 2, 5) // отступы
    }

    /**
     * Создание текстового поля (окна)
     */
    fun showWindow() {

        val scrollPane = JScrollPane(textArea) // компонент для скроллинга текста, выходящего за границы экрана
        JFrame().apply { //создание и настройка окна
            isVisible = true
            size = Dimension(600, 600)
            isResizable = false // запрет на изменение размера окна
            add(scrollPane) //добавление текстового поля внутрь окна, который можно скроллить
        }
//        UserRepositoryObserver.getInstanceUserRepository("qwerty").addOnUsersChangedListener(this) // подписка на обновление окна
// для примера удаления обзервера:
//        val observer: (List <User>) -> Unit = {
//            textArea.text = it.joinToString("\n")
//        }
        /**
         * Замена на лямбду функционального интерфейса:
         */
        // пример удаления обзевера
//        UserRepositoryObserver.getInstanceUserRepository("qwerty").addOnUsersChangedListener(observer)
//        thread{
//            Thread.sleep(10000)
//            UserRepositoryObserver.getInstanceUserRepository("qwerty").unregisterObserver(observer)
//        }
        UserRepositoryObserver.getInstanceUserRepository("qwerty").addOnUsersChangedListener { users ->
            users.joinToString("\n") // преобразование в сроку с символом переноса
                .let { textArea.text = it }
        }

    }

    /**
     * Метод-мониторинг состояния списка для отображения в окне актуальных данных
     */

    override fun onChanged(newValue: List<User>) {
        newValue.joinToString("\n") // преобразование в сроку с символом переноса
            .let { textArea.text = it }
    }
}