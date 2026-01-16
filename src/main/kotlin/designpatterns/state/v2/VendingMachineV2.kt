package designpatterns.state.v2

/**
 * 状态模式
 */
class VendingMachineV2 : VendingMachineContext() {
    init {
        println("VendingMachineV2 init. state = $state")
    }

    fun insertMoney() {
        state.insertMoney(this)
    }

    fun selectProduct(code: String) {
        state.selectProduct(code, this)
    }

    fun requestRefund() {
        state.requestRefund(this)
    }
}