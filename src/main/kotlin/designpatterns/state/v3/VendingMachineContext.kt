package designpatterns.state.v3

open class VendingMachineContext {
    private var state: VendingState = VendingState.Default
    fun process(intent: VendingIntent) {
        state.handle(intent)
    }
}