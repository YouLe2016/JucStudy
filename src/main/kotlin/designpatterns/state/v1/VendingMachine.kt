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
        println("VendingMachine init. state = $state")
    }

    fun insertMoney() {
        when (state) {
            State.IDLE -> {
                insertUseCase()
                state = State.HAS_MONEY
            }
            State.HAS_MONEY -> println("已投币，请勿重复投币")
            State.OUT_OF_STOCK -> println("商品已售罄")
        }
    }

    fun selectProduct(code: String) {
        selectProductUseCase(code)
    }

    fun requestRefund() {
        requestRefundUseCase()
    }
}