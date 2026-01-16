package designpatterns.state.v3.state

import designpatterns.state.v3.VendingIntent
import designpatterns.state.v3.VendingMachineContext

sealed interface VendingState {
    fun handle(intent: VendingIntent, context: VendingMachineContext)
}