package tasks.task_6_4_Synhronize

/**
 * Описание
 * В текущем коде DatabaseConnection реализован как обычный Singleton, но он не является потокобезопасным.
 * В многопоточной среде возможна ситуация, когда два потока одновременно вызовут getInstance(), что может привести к
 * созданию нескольких экземпляров.
 * Нужно реализовать Double-Checked Locking, чтобы обеспечить потокобезопасность и избежать ненужной синхронизации после
 * первой инициализации.
 */
fun main() {
    DatabaseConnection.getInstance().query("SELECT * FROM users ORDER BY id DESC")
}
class DatabaseConnection private constructor() {

    companion object {
        private var instance: DatabaseConnection? = null
        private val lock = Any()

        fun getInstance(): DatabaseConnection {
            instance?.let { return it }
            synchronized(lock) {
                instance?.let { return it }
                return DatabaseConnection().also { instance = it }
            }
        }
    }

    fun query(sql: String): String {
        return "Результат запроса: $sql"
    }
}