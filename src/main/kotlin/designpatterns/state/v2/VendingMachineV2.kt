package designpatterns.state.v2

import jdk.jfr.DataAmount

/**
 * 状态模式
 */
class VendingMachineV2(balance: Int = 0) : VendingMachineContext(balance) {
    init {
        println("VendingMachineV2 init. state = $state")
    }

    fun insertMoney(amount: Int = 1) {
        state.insertMoney(amount, this)
    }

    fun selectProduct(code: String) {
        state.selectProduct(code, this)
    }

    fun requestRefund() {
        state.requestRefund(this)
    }
}
