package designpatterns.state.v4.state

import designpatterns.state.v4.VendingIntent

data class OutOfStockState(
    override val balance: Int
) : VendingState(balance, 0) {
    override fun handle(intent: VendingIntent): VendingState {
        println("商品已售罄")
        return this
    }
}
