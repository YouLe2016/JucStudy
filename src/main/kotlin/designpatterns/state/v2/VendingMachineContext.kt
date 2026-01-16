package designpatterns.state.v2

import designpatterns.state.v2.state.IdleState
import designpatterns.state.v2.state.VendingState

open class VendingMachineContext {
    protected var state: VendingState = IdleState

    fun changeState(newState: VendingState) {
        state = newState
    }
}