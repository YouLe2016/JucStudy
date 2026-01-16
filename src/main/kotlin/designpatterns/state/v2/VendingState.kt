package designpatterns.state.v2

sealed interface VendingState {
    fun insertMoney()

    fun selectProduct()

    fun requestRefund()

    object Default : VendingState {

        override fun insertMoney() {
            println("投币操作")
        }

        override fun selectProduct() {
            println("取货操作")
        }

        override fun requestRefund() {
            println("退币操作")
        }
    }
}