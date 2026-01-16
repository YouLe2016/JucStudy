package designpatterns.state.v3

import designpatterns.state.v3.state.DefaultState
import designpatterns.state.v3.state.VendingState

open class VendingMachineContext {
    protected var state: VendingState = DefaultState
    fun process(intent: VendingIntent) {
        state.handle(intent)
    }
}