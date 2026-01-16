package designpatterns.state.v3

import designpatterns.state.v3.state.HasMoneyState
import designpatterns.state.v3.state.IdleState
import designpatterns.state.v3.state.OutOfStockState
import designpatterns.utils.getPrivateProperty
import designpatterns.utils.setPrivateProperty
import org.junit.jupiter.api.BeforeEach
import kotlin.test.Test
import kotlin.test.assertEquals

class VendingMachineV3Test {
    private val vendingMachine = VendingMachineV3()

    @BeforeEach
    fun setUp() {
        assertEquals(
            expected = IdleState,
            actual = getPrivateProperty(vendingMachine, "state"),
            message = "状态应为 IDLE"
        )
    }

    @Test
    fun testInsertMoney_idle() {
        vendingMachine.process(VendingIntent.InsertMoney)
        assertEquals(
            expected = HasMoneyState,
            actual = getPrivateProperty(vendingMachine, "state"),
            message = "状态应变为 HAS_MONEY"
        )
    }

    @Test
    fun testInsertMoney_hasMoney() {
        // 进入HAS_MONEY 状态
        vendingMachine.process(VendingIntent.InsertMoney)
        vendingMachine.process(VendingIntent.InsertMoney)
        assertEquals(
            expected = HasMoneyState,
            actual = getPrivateProperty(vendingMachine, "state"),
            message = "状态应保持不变"
        )
    }

    @Test
    fun testInsertMoney_OutOfStock() {
        // 强行设置状态为 OUT_OF_STOCK (因为没有正常逻辑能到达这个状态)
        setPrivateProperty(vendingMachine, "state", OutOfStockState)
        vendingMachine.process(VendingIntent.InsertMoney)
        assertEquals(
            expected = OutOfStockState,
            actual = getPrivateProperty(vendingMachine, "state"),
            message = "状态应保持不变"
        )
    }

//    @Test
//    fun testSelectProduct() {
//        vendingMachine.process(VendingIntent.SelectProduct)
//    }
//
//    @Test
//    fun testRequestRefund() {
//        vendingMachine.process(VendingIntent.RequestRefund)
//    }
}