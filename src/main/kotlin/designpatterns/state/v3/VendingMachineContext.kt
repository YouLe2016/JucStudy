package designpatterns.state.v3

import designpatterns.state.v3.state.IdleState
import designpatterns.state.v3.state.VendingState

open class VendingMachineContext(private var balance: Int = 0) {
    private var mAmount: Int = 0
    protected var state: VendingState = IdleState
    protected fun process(intent: VendingIntent) {
        state.handle(intent, this)
    }

    fun changeState(newState: VendingState) {
        state = newState
    }

    fun addBalance(amount: Int) {
        balance += amount
        mAmount = amount
    }

    fun reduceBalance() {
        balance -= mAmount
        mAmount = 0
    }
}
