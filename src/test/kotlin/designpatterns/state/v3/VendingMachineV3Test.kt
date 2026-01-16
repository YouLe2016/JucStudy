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
    private val initBalance = 10
    private val vendingMachine = VendingMachineV3(initBalance)

    @BeforeEach
    fun setUp() {
        assertEquals(
            expected = IdleState,
            actual = getPrivateProperty(vendingMachine, "state"),
            message = "状态应为 IDLE"
        )
        assertEquals(
            expected = initBalance,
            actual = getPrivateProperty(vendingMachine, "balance"),
            message = "balance 应为 $initBalance"
        )
        assertEquals(
            expected = 0,
            actual = getPrivateProperty(vendingMachine, "mAmount"),
            message = "balance 应为：0"
        )
    }

    @Test
    fun testInsertMoney_idle() {
        val amount = 1
        val balance = initBalance + amount

        vendingMachine.insertMoney(amount)
        assertEquals(
            expected = HasMoneyState,
            actual = getPrivateProperty(vendingMachine, "state"),
            message = "状态应变为 HAS_MONEY"
        )
        assertEquals(
            expected = balance,
            actual = getPrivateProperty(vendingMachine, "balance"),
            message = "balance 应为：$balance"
        )
        assertEquals(
            expected = amount,
            actual = getPrivateProperty(vendingMachine, "mAmount"),
            message = "mAmount 应为：$amount"
        )
    }

    @Test
    fun testInsertMoney_hasMoney() {
        // given
        val amount = 1
        val balance = initBalance + amount
        // 进入HAS_MONEY 状态
        vendingMachine.insertMoney(amount)

        // when
        vendingMachine.insertMoney(amount)

        // then
        assertEquals(
            expected = HasMoneyState,
            actual = getPrivateProperty(vendingMachine, "state"),
            message = "状态应保持不变"
        )
        assertEquals(
            expected = balance,
            actual = getPrivateProperty(vendingMachine, "balance"),
            message = "balance 应为：$balance"
        )
        assertEquals(
            expected = amount,
            actual = getPrivateProperty(vendingMachine, "mAmount"),
            message = "balance 应为：$balance"
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
        assertEquals(
            expected = initBalance,
            actual = getPrivateProperty(vendingMachine, "balance"),
            message = "balance 应为：$initBalance"
        )
        assertEquals(
            expected = 0,
            actual = getPrivateProperty(vendingMachine, "mAmount"),
            message = "mAmount 应为：0"
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
        // when
        vendingMachine.requestRefund()

        // then
        assertEquals(
            expected = IdleState,
            actual = getPrivateProperty(vendingMachine, "state"),
            message = "状态应为 IDLE"
        )
        assertEquals(
            expected = initBalance,
            actual = getPrivateProperty(vendingMachine, "balance"),
            message = "balance 应为：$initBalance"
        )
        assertEquals(
            expected = 0,
            actual = getPrivateProperty(vendingMachine, "mAmount"),
            message = "mAmount 应为：0"
        )
    }

    @Test
    fun testRequestRefund_hasMoney() {
        // given
        // 进入HAS_MONEY 状态
        vendingMachine.insertMoney()

        vendingMachine.requestRefund()

        // then
        assertEquals(
            expected = IdleState,
            actual = getPrivateProperty(vendingMachine, "state"),
            message = "退款后状态应为 IDLE"
        )
        assertEquals(
            expected = initBalance,
            actual = getPrivateProperty(vendingMachine, "balance"),
            message = "balance 应为：$initBalance"
        )
        assertEquals(
            expected = 0,
            actual = getPrivateProperty(vendingMachine, "mAmount"),
            message = "mAmount 应为：0"
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
        assertEquals(
            expected = initBalance,
            actual = getPrivateProperty(vendingMachine, "balance"),
            message = "balance 应为：$initBalance"
        )
        assertEquals(
            expected = 0,
            actual = getPrivateProperty(vendingMachine, "mAmount"),
            message = "mAmount 应为：0"
        )
    }
}
