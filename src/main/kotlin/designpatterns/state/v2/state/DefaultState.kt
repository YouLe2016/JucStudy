package designpatterns.state.v2.state

import designpatterns.state.usecase.InsertMoneyUseCase
import designpatterns.state.usecase.RequestRefundUseCase
import designpatterns.state.usecase.SelectProduceUseCase

object DefaultState : VendingState {
    private val insertUseCase = InsertMoneyUseCase
    private val selectProductUseCase = SelectProduceUseCase
    private val requestRefundUseCase = RequestRefundUseCase

    override fun insertMoney() {
        insertUseCase()
    }

    override fun selectProduct() {
        selectProductUseCase()
    }

    override fun requestRefund() {
        requestRefundUseCase()
    }
}