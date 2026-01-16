package designpatterns.state.v3

/**
 * Kotlin风格
 */
class VendingMachineV3: VendingMachineContext() {
    init {
        println("VendingMachineV3 init. state = $state ")
    }

    fun insertMoney() {
        process(VendingIntent.InsertMoney)
    }

    fun selectProduct(code: String) {
        process(VendingIntent.SelectProduct(code))
    }

    fun requestRefund() {
        process(VendingIntent.RequestRefund)
    }
}