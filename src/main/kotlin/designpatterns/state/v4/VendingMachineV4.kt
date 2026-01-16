package designpatterns.state.v4

import designpatterns.state.v4.state.IdleState
import designpatterns.state.v4.state.VendingState

class VendingMachineV4 {
    private var state: VendingState = IdleState

    init {
        println("VendingMachineV4 init. state = $state")
    }

    fun process(intent: VendingIntent) {
        state = state.handle(intent)
    }
}