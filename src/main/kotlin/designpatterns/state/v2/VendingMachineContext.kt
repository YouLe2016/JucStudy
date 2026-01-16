package designpatterns.state.v2

import designpatterns.state.v2.state.IdleState
import designpatterns.state.v2.state.VendingState

open class VendingMachineContext(private var balance: Int = 0) {
    protected var state: VendingState = IdleState
    private var mAmount: Int = 0

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

    fun resetAmount() {
        mAmount = 0
    }
}
