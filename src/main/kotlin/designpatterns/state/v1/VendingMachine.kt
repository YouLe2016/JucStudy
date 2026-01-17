package designpatterns.state.v1

import designpatterns.state.usecase.InsertMoneyUseCase
import designpatterns.state.usecase.RequestRefundUseCase
import designpatterns.state.usecase.SelectProduceUseCase

/**
 * 纯逻辑
 */
class VendingMachine(private var balance: Int = 0) {
    private var state: State = State.IDLE
    private var mAmount: Int = 0

    init {
        println("VendingMachine init. state = $state")
    }

    fun insertMoney(amount: Int = 1) {
        when (state) {
            State.HAS_MONEY -> println("已投币，请勿重复投币")
            State.OUT_OF_STOCK -> println("商品已售罄")
            State.IDLE -> {
                InsertMoneyUseCase()
                balance += amount
                mAmount = amount
                state = State.HAS_MONEY
            }
        }
    }

    fun selectProduct(code: String) {
        when (state) {
            State.IDLE -> println("请先投币")
            State.OUT_OF_STOCK -> println("商品已售罄")
            State.HAS_MONEY -> {
                val result = SelectProduceUseCase(code)
                mAmount = 0
                state = if (result) State.IDLE else State.HAS_MONEY
            }
        }
    }

    fun requestRefund() {
        when (state) {
            State.IDLE -> println("请先投币")
            State.OUT_OF_STOCK -> println("商品已售罄")
            State.HAS_MONEY -> {
                RequestRefundUseCase()
                balance -= mAmount
                mAmount = 0
                state = State.IDLE
            }
        }
    }
}
