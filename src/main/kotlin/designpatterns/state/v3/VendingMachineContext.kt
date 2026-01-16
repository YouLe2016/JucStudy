package designpatterns.state.v3

import designpatterns.state.v3.state.IdleState
import designpatterns.state.v3.state.VendingState

open class VendingMachineContext {
    protected var state: VendingState = IdleState
    fun process(intent: VendingIntent) {
        state.handle(intent, this)
    }

    fun changeState(newState: VendingState) {
        state = newState
    }
}