package designpatterns.state.v4

import designpatterns.state.v4.state.IdleState
import designpatterns.state.v4.state.VendingState

class VendingMachineV4 {
    private var state: VendingState = IdleState

    init {
        println("VendingMachineV4 init. state = $state")
    }

    private fun process(intent: VendingIntent) {
        state = state.handle(intent)
    }
    
    fun insertMoney() {
        process(VendingIntent.InsertMoney)
    }

    fun selectProduct(code: String) {
        process(VendingIntent.SelectProduct(code))
    }

    fun requestRefund() {
        process(VendingIntent.RequestRefund)
    }
}