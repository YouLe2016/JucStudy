package designpatterns.state.v1

import kotlin.test.Test


class VendingMachineTest {
    private val vendingMachine = VendingMachine()

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