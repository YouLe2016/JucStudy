package designpatterns.state.v4.state

import designpatterns.state.usecase.SelectProduceUseCase
import designpatterns.state.v4.VendingIntent

object HasMoneyState : VendingState() {
    override fun handle(intent: VendingIntent): VendingState {
        when (intent) {
            VendingIntent.InsertMoney -> {
                println("已投币，请勿重复投币")
                return this
            }
            VendingIntent.RequestRefund -> TODO()

            is VendingIntent.SelectProduct -> {
                val result = SelectProduceUseCase(intent.code)
                return if (result) IdleState else this
            }
        }
    }
}