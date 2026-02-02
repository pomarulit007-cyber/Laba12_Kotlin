package example

import resources.OutPostResource
import java.io.File

object FileStorage {
    private const val FILE_NAME = "outpost_database.txt"

    fun save(resources: List<OutpostResource>) {
        val file = File(FILE_NAME)

        val text = resources.joinToString(separator = "\n") { resource ->
            "${resource.id.toString().padEnd(length = 3)} | ${resource.name.padEnd(length = 10)} | ${resource.amount}"
        }

        file.writeText(text = text)
        println("Состояние базы сохранено в файл")
    }

    fun load(): List<OutpostResource> {
        val file = File(FILE_NAME)

        if (!file.exists()) {
            println("Файл базы данных не найден")
            return emptyList()
        }

        println("Загружаем состояние из файла...")
        return file.readLines().map { line ->
            val parts = line.split(delimiters = "|").map { it.trim() }
            OutpostResource(
                id = parts[0].toInt(),
                name = parts[1],
                amountInit = parts[2].toInt()
            )
        }
    }
}