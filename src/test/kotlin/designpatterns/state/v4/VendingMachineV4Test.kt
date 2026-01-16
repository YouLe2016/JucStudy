package designpatterns.state.v4

import kotlin.test.Test

class VendingMachineV4Test {
    private val vendingMachine = VendingMachineV4()

    @Test
    fun testInsertMoney() {
        vendingMachine.process(VendingIntent.InsertMoney)
    }

    @Test
    fun testSelectProduct() {
        vendingMachine.process(VendingIntent.SelectProduct)
    }

    @Test
    fun testRequestRefund() {
        vendingMachine.process(VendingIntent.RequestRefund)
    }
}