package designpatterns.state.v3.state

import designpatterns.state.usecase.SelectProduceUseCase
import designpatterns.state.v3.VendingIntent
import designpatterns.state.v3.VendingMachineContext

object HasMoneyState: VendingState {
    private val selectProductUseCase = SelectProduceUseCase
    override fun handle(
        intent: VendingIntent,
        context: VendingMachineContext
    ) {
        when (intent) {
            VendingIntent.InsertMoney -> println("已投币，请勿重复投币")
            VendingIntent.RequestRefund -> TODO()
            is VendingIntent.SelectProduct -> {
                val result = selectProductUseCase(intent.code)
                context.changeState(if (result) IdleState else this)
            }
        }
    }
}