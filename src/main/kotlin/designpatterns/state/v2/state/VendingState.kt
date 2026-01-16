package designpatterns.state.v2.state

import designpatterns.state.v2.VendingMachineContext

sealed interface VendingState {
    fun insertMoney(context: VendingMachineContext)

    fun selectProduct(code: String, context: VendingMachineContext)

    fun requestRefund()
}