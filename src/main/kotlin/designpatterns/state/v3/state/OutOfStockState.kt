package designpatterns.state.v3.state

import designpatterns.state.v3.VendingIntent
import designpatterns.state.v3.VendingMachineContext

object OutOfStockState : VendingState {
    override fun handle(
        intent: VendingIntent,
        context: VendingMachineContext
    ) {
        when (intent) {
            VendingIntent.InsertMoney -> println("商品已售罄")
            VendingIntent.RequestRefund -> TODO()
            is VendingIntent.SelectProduct -> TODO()
        }
    }
}