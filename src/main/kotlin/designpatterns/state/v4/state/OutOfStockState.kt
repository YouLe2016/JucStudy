package designpatterns.state.v4.state

import designpatterns.state.v4.VendingIntent

object OutOfStockState : VendingState() {
    override fun handle(intent: VendingIntent): VendingState {
        println("商品已售罄")
        return this
    }
}