package designpatterns.state.v2

import designpatterns.state.v2.state.HasMoneyState
import designpatterns.state.v2.state.IdleState
import designpatterns.state.v2.state.OutOfStockState
import designpatterns.utils.getPrivateProperty
import designpatterns.utils.setPrivateProperty
import org.junit.jupiter.api.BeforeEach
import kotlin.test.Test
import kotlin.test.assertEquals

class VendingMachineV2Test {
    private val vendingMachine = VendingMachineV2()

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
        vendingMachine.insertMoney()
        assertEquals(
            expected = HasMoneyState,
            actual = getPrivateProperty(vendingMachine, "state"),
            message = "状态应变为 HAS_MONEY"
        )
    }

    @Test
    fun testInsertMoney_hasMoney() {
        // 进入HAS_MONEY 状态
        vendingMachine.insertMoney()
        vendingMachine.insertMoney()
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
        vendingMachine.insertMoney()
        assertEquals(
            expected = OutOfStockState,
            actual = getPrivateProperty(vendingMachine, "state"),
            message = "状态应保持不变"
        )
    }

    @Test
    fun testSelectProduct_idle() {
        vendingMachine.selectProduct("1024")
        assertEquals(
            expected = IdleState,
            actual = getPrivateProperty(vendingMachine,"state"),
            message = "状态应保持不变 IDLE"
        )
        vendingMachine.selectProduct("1025")
        assertEquals(
            expected = IdleState,
            actual = getPrivateProperty(vendingMachine,"state"),
            message = "状态应保持不变 IDLE"
        )
    }
    @Test
    fun testSelectProduct_hasMoney() {
        // 进入HAS_MONEY 状态
        vendingMachine.insertMoney()
        vendingMachine.selectProduct("1024")
        assertEquals(
            expected = IdleState,
            actual = getPrivateProperty(vendingMachine,"state"),
            message = "状态应保持不变 IDLE"
        )

        // 进入HAS_MONEY 状态
        vendingMachine.insertMoney()
        vendingMachine.selectProduct("1025")
        assertEquals(
            expected = HasMoneyState,
            actual = getPrivateProperty(vendingMachine,"state"),
            message = "状态应保持不变 IDLE"
        )
    }

    @Test
    fun testSelectProduct_outOfStock() {
        // 强行设置状态为 OUT_OF_STOCK (因为没有正常逻辑能到达这个状态)
        setPrivateProperty(vendingMachine, "state", OutOfStockState)
        vendingMachine.selectProduct("1024")
        assertEquals(
            expected = OutOfStockState,
            actual = getPrivateProperty(vendingMachine,"state"),
            message = "状态应保持不变 OUT_OF_STOCK"
        )
        vendingMachine.selectProduct("1025")
        assertEquals(
            expected = OutOfStockState,
            actual = getPrivateProperty(vendingMachine,"state"),
            message = "状态应保持不变 OUT_OF_STOCK"
        )
    }

    @Test
    fun testRequestRefund_idle() {
        vendingMachine.requestRefund()
        assertEquals(
            expected = IdleState,
            actual = getPrivateProperty(vendingMachine, "state"),
            message = "状态应为 IDLE"
        )
    }

    @Test
    fun testRequestRefund_hasMoney() {
        // 进入HAS_MONEY 状态
        vendingMachine.insertMoney()

        vendingMachine.requestRefund()
        assertEquals(
            expected = IdleState,
            actual = getPrivateProperty(vendingMachine, "state"),
            message = "退款后状态应为 IDLE"
        )
    }

    @Test
    fun testRequestRefund_outOfStock() {
        setPrivateProperty(vendingMachine, "state", OutOfStockState)

        vendingMachine.requestRefund()
        assertEquals(
            expected = OutOfStockState,
            actual = getPrivateProperty(vendingMachine, "state"),
            message = "状态应为 OUT_OF_STOCK"
        )
    }
}