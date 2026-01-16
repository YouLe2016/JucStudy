package designpatterns.state.v2.state

import designpatterns.state.usecase.InsertMoneyUseCase
import designpatterns.state.v2.VendingMachineContext

object IdleState : VendingState {
    private val insertUseCase = InsertMoneyUseCase
    override fun insertMoney(context: VendingMachineContext) {
        insertUseCase()
        context.changeState(HasMoneyState)
    }

    override fun selectProduct() {
        println("已投币，请勿重复投币")
    }

    override fun requestRefund() {
        println("商品已售罄")
    }
}