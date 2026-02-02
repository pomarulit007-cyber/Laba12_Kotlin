package example

import kotlin.properties.Delegates

data class OutpostResource(
    val id: Int,
    val name: String,
    val amountInit: Int
) {
    var amount: Int by Delegates.observable(amountInit) { _, old, new ->
        println("Ресурс '$name' изменился: $old → $new")
    }
}