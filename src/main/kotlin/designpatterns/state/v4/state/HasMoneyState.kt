package designpatterns.state.v4.state

import designpatterns.state.usecase.RequestRefundUseCase
import designpatterns.state.usecase.SelectProduceUseCase
import designpatterns.state.v4.VendingIntent

data class HasMoneyState(
    override val balance: Int,
    override val amount: Int
) : VendingState(balance, amount) {
    override fun handle(intent: VendingIntent): VendingState {
        when (intent) {
            is VendingIntent.InsertMoney -> {
                println("已投币，请勿重复投币")
                return this
            }

            VendingIntent.RequestRefund -> {
                RequestRefundUseCase()
                return reduceBalance()
            }

            is VendingIntent.SelectProduct -> {
                val result = SelectProduceUseCase(intent.code)
                return if (result) {
                    resetAmount()
                } else {
                    this
                }
            }
        }
    }

    private fun reduceBalance(): VendingState = IdleState(balance - amount)

    private fun resetAmount() = IdleState(balance)
}
