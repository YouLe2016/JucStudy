package designpatterns.state.v2.state

import designpatterns.state.usecase.InsertMoneyUseCase
import designpatterns.state.v2.VendingMachineContext

object IdleState : VendingState {
    private val insertUseCase = InsertMoneyUseCase
    override fun insertMoney(context: VendingMachineContext) {
        insertUseCase()
        context.changeState(HasMoneyState)
    }

    override fun selectProduct(code: String, context: VendingMachineContext) {
        println("请先投币")
    }

    override fun requestRefund() {
        TODO("Not yet implemented")
    }
}