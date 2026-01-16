package designpatterns.state.v2

import kotlin.test.Test

class VendingMachineV2Test {
    private val vendingMachine = VendingMachineV2()

    @Test
    fun testInsertMoney() {
        vendingMachine.insertMoney()
    }

    @Test
    fun testSelectProduct() {
        vendingMachine.selectProduct()
    }

    @Test
    fun testRequestRefund() {
        vendingMachine.requestRefund()
    }
}