package designpatterns.state.v2.state

import designpatterns.state.v2.VendingMachineContext

object OutOfStockState: VendingState {
    override fun insertMoney(context: VendingMachineContext) {
        println("商品已售罄")
    }

    override fun selectProduct() {
        TODO("Not yet implemented")
    }

    override fun requestRefund() {
        TODO("Not yet implemented")
    }
}