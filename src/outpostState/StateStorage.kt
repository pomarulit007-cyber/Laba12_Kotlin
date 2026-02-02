package outpostState

import java.io.File

object StateStorage {
    private const val FILE_NAME = "outpost_state.txt"

    fun saveResources(resources: List<ObservableResource>) {
        val file = File(FILE_NAME)

        val text = resources.joinToString("\n") { resource ->
            "${resource.name};${resource.amount}"
        }

        file.writeText(text)
        println("Состояние сохранено в файл")
    }

    fun loadResources(): List<ObservableResource> {
        val file = File(FILE_NAME)

        if (!file.exists()) {
            println("Файл не найден, возвращаем пустой список")
            return emptyList()
        }

        return file.readLines().map { line ->
            val (name, amount) = line.split(";")
            ObservableResource(name, amount.toInt())
        }
    }
}