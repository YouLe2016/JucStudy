package designpatterns.state.v2.state

import designpatterns.state.usecase.InsertMoneyUseCase
import designpatterns.state.v2.VendingMachineContext

object IdleState : VendingState {
    override fun insertMoney(amount: Int, context: VendingMachineContext) {
        InsertMoneyUseCase()
        context.addBalance(amount)
        context.changeState(HasMoneyState)
    }

    override fun selectProduct(code: String, context: VendingMachineContext) {
        println("请先投币")
    }

    override fun requestRefund(context: VendingMachineContext) {
        println("请先投币")
    }
}
