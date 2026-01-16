package designpatterns.state.v2.state

import designpatterns.state.v2.VendingMachineContext

object OutOfStockState: VendingState {
    override fun insertMoney(amount: Int, context: VendingMachineContext) {
        println("商品已售罄")
    }

    override fun selectProduct(code: String, context: VendingMachineContext) {
        println("商品已售罄")
    }

    override fun requestRefund(context: VendingMachineContext) {
        println("商品已售罄")
    }
}
