package designpatterns.state.v2.state

import designpatterns.state.usecase.InsertMoneyUseCase
import designpatterns.state.usecase.RequestRefundUseCase
import designpatterns.state.usecase.SelectProduceUseCase
import designpatterns.state.v2.VendingMachineContext

object DefaultState : VendingState {
    private val insertUseCase = InsertMoneyUseCase
    private val selectProductUseCase = SelectProduceUseCase
    private val requestRefundUseCase = RequestRefundUseCase

    override fun insertMoney(context: VendingMachineContext) {
        insertUseCase()
    }

    override fun selectProduct(code: String) {
        selectProductUseCase(code)
    }

    override fun requestRefund() {
        requestRefundUseCase()
    }
}