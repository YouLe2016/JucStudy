package designpatterns.state.v2

import designpatterns.state.v2.state.DefaultState
import designpatterns.state.v2.state.VendingState

/**
 * 状态模式
 */
class VendingMachineV2 {
    private var state: VendingState = DefaultState

    init {
        println("VendingMachineV2 init")
    }

    fun insertMoney() {
        state.insertMoney()
    }

    fun selectProduct() {
        state.selectProduct()
    }

    fun requestRefund() {
        state.requestRefund()
    }

    fun changeState(newState: VendingState) {
        state = newState
    }
}