package designpatterns.state.v3.state

import designpatterns.state.usecase.RequestRefundUseCase
import designpatterns.state.usecase.RequestRefundUseCase.invoke
import designpatterns.state.usecase.SelectProduceUseCase
import designpatterns.state.v3.VendingIntent
import designpatterns.state.v3.VendingMachineContext

object HasMoneyState: VendingState {
    override fun handle(
        intent: VendingIntent,
        context: VendingMachineContext
    ) {
        when (intent) {
            is VendingIntent.InsertMoney -> println("已投币，请勿重复投币")
            VendingIntent.RequestRefund -> {
                RequestRefundUseCase()
                context.reduceBalance()
                context.changeState(IdleState)
            }
            is VendingIntent.SelectProduct -> {
                val result = SelectProduceUseCase(intent.code)
                if (result) {
                    context.changeState(IdleState)
                    context.resetAmount()
                }
            }
        }
    }
}
