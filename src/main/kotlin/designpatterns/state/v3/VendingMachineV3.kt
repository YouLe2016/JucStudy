package designpatterns.state.v3

/**
 * Kotlin风格
 */
class VendingMachineV3(balance: Int): VendingMachineContext(balance) {
    init {
        println("VendingMachineV3 init. state = $state ")
    }

    fun insertMoney(amount: Int = 1) {
        process(VendingIntent.InsertMoney(amount))
    }

    fun selectProduct(code: String) {
        process(VendingIntent.SelectProduct(code))
    }

    fun requestRefund() {
        process(VendingIntent.RequestRefund)
    }
}
