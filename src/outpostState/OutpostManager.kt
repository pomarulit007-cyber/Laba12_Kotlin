package outpostState

class OutpostManager {
    private val _resources = mutableListOf<ObservableResource>()

    val resources: List<ObservableResource> by lazy {
        println("Менеджер ресурсов создан")
        _resources.toList()
    }

    fun addResource(resource: ObservableResource) {
        _resources.add(resource)
    }
}