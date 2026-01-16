package designpatterns.state.v4.state

import designpatterns.state.v4.VendingIntent

sealed class VendingState {
    abstract fun handle(intent: VendingIntent): VendingState
}