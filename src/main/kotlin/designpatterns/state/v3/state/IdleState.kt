package designpatterns.state.v3.state

import designpatterns.state.usecase.InsertMoneyUseCase
import designpatterns.state.v3.VendingIntent
import designpatterns.state.v3.VendingMachineContext

object IdleState: VendingState {
    private val insertUseCase = InsertMoneyUseCase

    override fun handle(intent: VendingIntent, context: VendingMachineContext) {
        when (intent) {
            VendingIntent.InsertMoney -> {
                insertUseCase()
                context.changeState(HasMoneyState)
            }
            VendingIntent.RequestRefund -> TODO()
            is VendingIntent.SelectProduct -> TODO()
        }
    }
}