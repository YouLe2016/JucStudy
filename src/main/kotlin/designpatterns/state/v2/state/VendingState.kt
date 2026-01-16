package designpatterns.state.v2.state

import designpatterns.state.v2.VendingMachineContext

sealed interface VendingState {
    fun insertMoney(amount: Int, context: VendingMachineContext)

    fun selectProduct(code: String, context: VendingMachineContext)

    fun requestRefund(context: VendingMachineContext)
}
