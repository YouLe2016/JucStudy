package designpatterns.state.v2.state

sealed interface VendingState {
    fun insertMoney()

    fun selectProduct()

    fun requestRefund()
}