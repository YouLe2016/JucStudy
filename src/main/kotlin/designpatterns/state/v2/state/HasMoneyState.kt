package designpatterns.state.v2.state

import designpatterns.state.usecase.RequestRefundUseCase
import designpatterns.state.usecase.SelectProduceUseCase
import designpatterns.state.v2.VendingMachineContext

object HasMoneyState : VendingState {
    override fun insertMoney(amount: Int, context: VendingMachineContext) {
        println("已投币，请勿重复投币")
    }

    override fun selectProduct(code: String, context: VendingMachineContext) {
        val result = SelectProduceUseCase(code)
        if (result) {
            context.changeState(IdleState)
            context.resetAmount()
        }
    }

    override fun requestRefund(context: VendingMachineContext) {
        RequestRefundUseCase()
        context.reduceBalance()
        context.changeState(IdleState)
    }

}
