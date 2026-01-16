package designpatterns.state.v1

/**
 * 纯逻辑
 */
class VendingMachine() {
    init {
        println("VendingMachine init")
    }

    fun insertMoney() {
        println("投币操作")
    }

    fun selectProduct() {
        println("取货操作")
    }

    fun requestRefund() {
        println("退币操作")
    }
}