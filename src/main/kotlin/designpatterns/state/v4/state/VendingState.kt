package designpatterns.state.v4.state

import designpatterns.state.v4.VendingIntent

sealed class VendingState(
    open val balance: Int,
    open val amount: Int
) {
    abstract fun handle(intent: VendingIntent): VendingState
}
