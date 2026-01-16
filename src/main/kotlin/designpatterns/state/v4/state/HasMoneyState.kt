package designpatterns.state.v4.state

import designpatterns.state.v4.VendingIntent

object HasMoneyState : VendingState() {
    override fun handle(intent: VendingIntent): VendingState {
        when (intent) {
            VendingIntent.InsertMoney -> {
                println("已投币，请勿重复投币")
                return this
            }
            VendingIntent.RequestRefund -> TODO()
            is VendingIntent.SelectProduct -> TODO()
        }
    }
}