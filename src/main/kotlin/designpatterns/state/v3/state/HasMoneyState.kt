package designpatterns.state.v3.state

import designpatterns.state.v3.VendingIntent
import designpatterns.state.v3.VendingMachineContext

object HasMoneyState: VendingState {
    override fun handle(
        intent: VendingIntent,
        context: VendingMachineContext
    ) {
        when (intent) {
            VendingIntent.InsertMoney -> println("已投币，请勿重复投币")
            VendingIntent.RequestRefund -> TODO()
            VendingIntent.SelectProduct -> TODO()
        }
    }
}