package designpatterns.state.v4.state

import designpatterns.state.usecase.InsertMoneyUseCase
import designpatterns.state.usecase.RequestRefundUseCase
import designpatterns.state.usecase.SelectProduceUseCase
import designpatterns.state.v4.VendingIntent

object DefaultState : VendingState() {
    private val insertUseCase = InsertMoneyUseCase
    private val selectProductUseCase = SelectProduceUseCase
    private val requestRefundUseCase = RequestRefundUseCase

    override fun handle(intent: VendingIntent): VendingState {
        when (intent) {
            is VendingIntent.InsertMoney -> {
                insertUseCase()
            }

            is VendingIntent.SelectProduct -> {
                selectProductUseCase()
            }

            is VendingIntent.RequestRefund -> {
                requestRefundUseCase()
            }
        }
        return DefaultState
    }
}