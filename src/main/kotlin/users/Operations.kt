package users

enum class Operations(val title: String) {
    EXIT ("Выйти с сохранить"),
    ADD_USER ("Добавить пользователя"),
    DELETE_USER("Удалить пользователя"),
    SHOW_USER_LIST("Показать всех пользователей")

}