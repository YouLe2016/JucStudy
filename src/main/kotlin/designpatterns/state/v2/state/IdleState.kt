package designpatterns.state.v2.state

import designpatterns.state.usecase.InsertMoneyUseCase
import designpatterns.state.v2.VendingMachineContext

object IdleState : VendingState {
    private val insertUseCase = InsertMoneyUseCase
    override fun insertMoney(context: VendingMachineContext) {
        insertUseCase()
        context.changeState(HasMoneyState)
    }

    override fun selectProduct(code: String) {
        TODO("Not yet implemented")
    }

    override fun requestRefund() {
        TODO("Not yet implemented")
    }
}