package designpatterns.state.v3.state

import designpatterns.state.v3.VendingIntent

sealed interface VendingState {
    fun handle(intent: VendingIntent)
}