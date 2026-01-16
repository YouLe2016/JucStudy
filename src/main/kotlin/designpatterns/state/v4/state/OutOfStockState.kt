package designpatterns.state.v4.state

import designpatterns.state.v4.VendingIntent

object OutOfStockState : VendingState() {
    override fun handle(intent: VendingIntent): VendingState {
        when (intent) {
            VendingIntent.InsertMoney -> {
                println("商品已售罄")
            }
            VendingIntent.RequestRefund -> TODO()
            is VendingIntent.SelectProduct -> TODO()
        }
        return this
    }
}