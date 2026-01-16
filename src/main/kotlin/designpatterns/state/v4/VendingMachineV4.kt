package designpatterns.state.v4

class VendingMachineV4 {
    private var state: VendingState = VendingState.Default

    init {
        println("VendingMachineV4 init")
    }

    fun process(intent: VendingIntent) {
        state = state.handle(intent)
    }
}