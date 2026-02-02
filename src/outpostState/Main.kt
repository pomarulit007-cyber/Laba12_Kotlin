package outpostState

fun main() {
    val manager = OutpostManager()

    val minerals = ObservableResource("Minerals", 100)
    val gas = ObservableResource("Gas", 50)
    val crystals = ObservableResource("Crystals", 75)

    manager.addResource(minerals)
    manager.addResource(gas)
    manager.addResource(crystals)

    val observer = ResourceObserver()
    observer.observe(minerals)
    observer.observe(gas)
    observer.observe(crystals)

    minerals.amount = 120
    gas.amount = 30
    crystals.amount = 90

    StateStorage.saveResources(listOf(minerals, gas, crystals))

    val loadedResources = StateStorage.loadResources()

    println("Загруженные ресурсы:")
    loadedResources.forEach {
        println("${it.name}: ${it.amount}")
    }
}