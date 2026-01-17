package designpatterns.state.v4.state

import designpatterns.state.usecase.InsertMoneyUseCase
import designpatterns.state.v4.VendingIntent

data class IdleState(override val balance: Int) : VendingState(balance, 0) {

    override fun handle(intent: VendingIntent): VendingState {
        when (intent) {
            is VendingIntent.InsertMoney -> {
                InsertMoneyUseCase()
                return addBalance(intent.amount)
            }

            VendingIntent.RequestRefund -> {
                println("请先投币")
                return this
            }

            is VendingIntent.SelectProduct -> {
                println("请先投币")
                return this
            }
        }
    }

    fun addBalance(amount: Int): VendingState = HasMoneyState(balance + amount, amount)
}
