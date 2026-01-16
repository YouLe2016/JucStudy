package designpatterns.state.v2

/**
 * 状态模式
 */
class VendingMachineV2 {
    private var state: VendingState = VendingState.Default

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