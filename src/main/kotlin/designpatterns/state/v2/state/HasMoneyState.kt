package designpatterns.state.v2.state

import designpatterns.state.v2.VendingMachineContext

object HasMoneyState : VendingState {
    override fun insertMoney(context: VendingMachineContext) {
        println("已投币，请勿重复投币")
    }

    override fun selectProduct() {
        TODO("Not yet implemented")
    }

    override fun requestRefund() {
        TODO("Not yet implemented")
    }
}