package designpatterns.state.v3.state

import designpatterns.state.usecase.InsertMoneyUseCase
import designpatterns.state.usecase.RequestRefundUseCase
import designpatterns.state.usecase.SelectProduceUseCase
import designpatterns.state.v3.VendingIntent
import designpatterns.state.v3.VendingMachineContext

object DefaultState : VendingState {
    private val insertUseCase = InsertMoneyUseCase
    private val selectProductUseCase = SelectProduceUseCase
    private val requestRefundUseCase = RequestRefundUseCase

    override fun handle(intent: VendingIntent, context: VendingMachineContext) {
        when (intent) {
            VendingIntent.InsertMoney -> {
                insertUseCase()
            }

            VendingIntent.RequestRefund -> {
                requestRefundUseCase()
            }

            is VendingIntent.SelectProduct -> {
                selectProductUseCase(intent.code)
            }
        }
    }
}