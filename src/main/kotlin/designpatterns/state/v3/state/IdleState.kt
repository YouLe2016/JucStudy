package designpatterns.state.v3.state

import designpatterns.state.usecase.InsertMoneyUseCase
import designpatterns.state.v3.VendingIntent
import designpatterns.state.v3.VendingMachineContext

object IdleState: VendingState {

    override fun handle(intent: VendingIntent, context: VendingMachineContext) {
        when (intent) {
            is VendingIntent.InsertMoney -> {
                InsertMoneyUseCase()
                context.addBalance(intent.amount)
                context.changeState(HasMoneyState)
            }
            VendingIntent.RequestRefund -> println("请先投币")
            is VendingIntent.SelectProduct -> println("请先投币")
        }
    }
}
