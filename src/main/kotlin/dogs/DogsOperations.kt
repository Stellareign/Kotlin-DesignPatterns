package dogs

enum class DogsOperations (val title: String){
    EXIT ("Выйти с сохранить"),
    ADD_DOG ("Добавить собаку"),
    DELETE_DOG("Удалить собаку"),
    SHOW_ALL_DOG_LIST("Показать весь список")
}