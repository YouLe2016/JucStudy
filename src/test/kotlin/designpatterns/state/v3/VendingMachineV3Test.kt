package designpatterns.state.v3

import kotlin.test.Test

class VendingMachineV3Test {
    private val vendingMachine = VendingMachineV3()

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