package designpatterns.state.v1

import designpatterns.state.usecase.InsertMoneyUseCase
import designpatterns.state.usecase.RequestRefundUseCase
import designpatterns.state.usecase.SelectProduceUseCase

/**
 * 纯逻辑
 */
class VendingMachine() {
    private var state: State = State.IDLE
    private val insertUseCase = InsertMoneyUseCase
    private val selectProductUseCase = SelectProduceUseCase
    private val requestRefundUseCase = RequestRefundUseCase

    init {
        println("VendingMachine init, state = $state")
    }

    fun insertMoney() {
        insertUseCase()
    }

    fun selectProduct() {
        selectProductUseCase()
    }

    fun requestRefund() {
        requestRefundUseCase()
    }
}