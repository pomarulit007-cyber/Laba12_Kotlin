package outpostState

class ResourceObserver {
    fun observe(resource: ObservableResource) {
        println("Наблюдатель подключен к ресурсу: ${resource.name}")
    }
}