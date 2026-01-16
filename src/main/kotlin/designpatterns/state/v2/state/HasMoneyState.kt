package designpatterns.state.v2.state

import designpatterns.state.usecase.SelectProduceUseCase
import designpatterns.state.v2.VendingMachineContext

object HasMoneyState : VendingState {
    private val selectProductUseCase = SelectProduceUseCase
    override fun insertMoney(context: VendingMachineContext) {
        println("已投币，请勿重复投币")
    }

    override fun selectProduct(code: String, context: VendingMachineContext) {
        val result = selectProductUseCase(code)
        context.changeState(if (result) IdleState else this)
    }

    override fun requestRefund() {
        TODO("Not yet implemented")
    }
}