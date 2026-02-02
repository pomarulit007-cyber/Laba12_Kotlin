package example

fun main() {
    val logger by lazy {
        SystemLogger()
    }

    val loadedResources = FileStorage.load()
    loadedResources.forEach { resourceManager.add(it) }

    if (loadedResources.isEmpty()) {
        resourceManager.add(OutpostResource(id = 1, name = "Minerals", amountInit = 300))
        resourceManager.add(OutpostResource(id = 2, name = "Gas", amountInit = 100))
    }

    logger.log("Система управления ресурсами запущена")

    FileStorage.save(resourceManager.getAll())

    var energy: Int by kotlin.properties.Delegates.observable(100) { _, old, new ->
        println("Энергия изменилась: $old → $new")
    }

    energy = 80
    energy = 120

    val gas = OutpostResource(id = 1, name = "Gas", amountInit = 100)
    val mineral = OutpostResource(id = 2, name = "Minerals", amountInit = 250)
    println("Успех! Вы добыли дополнительное количество минералов: ${mineral.amount + 50}")
    val bonusMineral = mineral.copy(id = 3, name = "Minerals Bonus", amountInit = mineral.amount + 50)
    println(gas.toString())
    println(mineral.toString())
    println(bonusMineral.toString())
}
