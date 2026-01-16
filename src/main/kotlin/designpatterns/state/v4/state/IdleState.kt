package designpatterns.state.v4.state

import designpatterns.state.usecase.InsertMoneyUseCase
import designpatterns.state.v4.VendingIntent

object IdleState: VendingState() {
    private val insertUseCase = InsertMoneyUseCase

    override fun handle(intent: VendingIntent): VendingState {
        when (intent) {
            VendingIntent.InsertMoney -> {
                insertUseCase()
                return HasMoneyState
            }
            VendingIntent.RequestRefund -> TODO()
            VendingIntent.SelectProduct -> TODO()
        }
    }
}