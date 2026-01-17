package designpatterns.state.v4

import designpatterns.state.v4.state.HasMoneyState
import designpatterns.state.v4.state.IdleState
import designpatterns.state.v4.state.OutOfStockState
import designpatterns.state.v4.state.VendingState
import designpatterns.utils.getPrivateProperty
import designpatterns.utils.setPrivateProperty
import org.junit.jupiter.api.BeforeEach
import kotlin.test.Test
import kotlin.test.assertEquals

class VendingMachineV4Test {
    private val initBalance = 10
    private val vendingMachine = VendingMachineV4(initBalance)

    @BeforeEach
    fun setUp() {
        val state = getPrivateProperty(vendingMachine, "state") as VendingState
        assertEquals(
            expected = IdleState(initBalance),
            actual = state,
            message = "状态应为 IDLE"
        )
        assertEquals(
            expected = initBalance,
            actual = state.balance,
            message = "balance 应为 $initBalance"
        )
        assertEquals(
            expected = 0,
            actual = state.amount,
            message = "amount 应为：0"
        )
    }

    @Test
    fun testInsertMoney_idle() {
        // given
        val amount = 1

        // when
        vendingMachine.insertMoney(amount)

        // then
        val state= getPrivateProperty(vendingMachine, "state") as VendingState
        val balance = initBalance + amount
        assertEquals(
            expected = HasMoneyState(balance, amount),
            actual = state,
            message = "状态应变为 HAS_MONEY"
        )
        assertEquals(
            expected = balance,
            actual = state.balance,
            message = "balance 应为：$balance"
        )
        assertEquals(
            expected = amount,
            actual = state.amount,
            message = "amount 应为：$amount"
        )
    }

    @Test
    fun testInsertMoney_hasMoney() {
        // given
        // 进入HAS_MONEY 状态
        val amount = 1
        var balance = initBalance + amount
        vendingMachine.insertMoney(amount)

        // when
        vendingMachine.insertMoney(amount)

        // when
        val state = getPrivateProperty(vendingMachine, "state") as VendingState
        assertEquals(
            expected = HasMoneyState(balance, amount),
            actual = state,
            message = "状态应保持不变"
        )
        assertEquals(
            expected = balance,
            actual = state.balance,
            message = "balance 应为：$balance"
        )
        assertEquals(
            expected = amount,
            actual = state.amount,
            message = "amount 应为：$amount"
        )
    }

    @Test
    fun testInsertMoney_OutOfStock() {
        // given
        // 强行设置状态为 OUT_OF_STOCK (因为没有正常逻辑能到达这个状态)
        val outOfStockState = OutOfStockState(initBalance)
        setPrivateProperty(vendingMachine, "state", outOfStockState)

        // when
        vendingMachine.insertMoney()

        // then
        val state = getPrivateProperty(vendingMachine, "state") as VendingState
        assertEquals(
            expected = outOfStockState,
            actual = state,
            message = "状态应保持不变"
        )
        assertEquals(
            expected = initBalance,
            actual = state.balance,
            message = "balance 应为：$initBalance"
        )
        assertEquals(
            expected = 0,
            actual = state.amount,
            message = "amount 应为：0"
        )
    }

    @Test
    fun testSelectProduct_idle() {
        // when
        vendingMachine.selectProduct("1024")

        // then
        val initState = IdleState(initBalance)
        val state = getPrivateProperty(vendingMachine, "state") as VendingState
        assertEquals(
            expected = initState,
            actual = state,
            message = "状态应保持不变 IDLE"
        )
        assertEquals(
            expected = initBalance,
            actual = state.balance,
            message = "balance 应为：$initBalance"
        )
        assertEquals(
            expected = 0,
            actual = state.amount,
            message = "amount 应为：0"
        )

        // when
        vendingMachine.selectProduct("1025")

        // then
        val state2 = getPrivateProperty(vendingMachine, "state") as VendingState
        assertEquals(
            expected = initState,
            actual = state2,
            message = "状态应保持不变 IDLE"
        )
        assertEquals(
            expected = initBalance,
            actual = state2.balance,
            message = "balance 应为：$initBalance"
        )
        assertEquals(
            expected = 0,
            actual = state2.amount,
            message = "amount 应为：0"
        )
    }

    @Test
    fun testSelectProduct_hasMoney() {
        // given
        val amount = 1
        // 进入HAS_MONEY 状态
        vendingMachine.insertMoney(amount)

        // when
        vendingMachine.selectProduct("1024")

        // then
        var balance = initBalance + amount
        val state = getPrivateProperty(vendingMachine, "state") as VendingState
        assertEquals(
            expected = IdleState(balance),
            actual = state,
            message = "状态应保持不变 IDLE"
        )
        assertEquals(
            expected = balance,
            actual = state.balance,
            message = "balance 应为：$balance"
        )
        assertEquals(
            expected = 0,
            actual = state.amount,
            message = "amount 应为：0"
        )

        // given
        // 进入HAS_MONEY 状态
        balance += amount
        vendingMachine.insertMoney(amount)

        // when
        vendingMachine.selectProduct("1025")

        // then
        val state2 = getPrivateProperty(vendingMachine, "state") as VendingState
        assertEquals(
            expected = HasMoneyState(balance, amount),
            actual = state2,
            message = "状态应保持不变 IDLE"
        )
        assertEquals(
            expected = balance,
            actual = state2.balance,
            message = "balance 应为：$balance"
        )
        assertEquals(
            expected = amount,
            actual = state2.amount,
            message = "amount 应为：$amount"
        )
    }

    @Test
    fun testSelectProduct_outOfStock() {
        // given
        // 强行设置状态为 OUT_OF_STOCK (因为没有正常逻辑能到达这个状态)
        val outOfStockState = OutOfStockState(initBalance)
        setPrivateProperty(vendingMachine, "state", outOfStockState)

        // when
        vendingMachine.selectProduct("1024")

        // then
        val state = getPrivateProperty(vendingMachine, "state") as VendingState
        assertEquals(
            expected = outOfStockState,
            actual = state,
            message = "状态应保持不变 OUT_OF_STOCK"
        )
        assertEquals(
            expected = initBalance,
            actual = state.balance,
            message = "balance 应为：$initBalance"
        )
        assertEquals(
            expected = 0,
            actual = state.amount,
            message = "amount 应为：0"
        )
    }

    @Test
    fun testRequestRefund_idle() {
        // when
        vendingMachine.requestRefund()

        // then
        val idleState = IdleState(initBalance)
        val state = getPrivateProperty(vendingMachine, "state") as VendingState
        assertEquals(
            expected = idleState,
            actual = state,
            message = "状态应为 IDLE"
        )
        assertEquals(
            expected = initBalance,
            actual = state.balance,
            message = "balance 应为：$initBalance"
        )
    }

    @Test
    fun testRequestRefund_hasMoney() {
        // given
        // 进入HAS_MONEY 状态
        vendingMachine.insertMoney()

        // when
        vendingMachine.requestRefund()

        // then
        val idleState = IdleState(initBalance)
        val state = getPrivateProperty(vendingMachine, "state") as VendingState
        assertEquals(
            expected = idleState,
            actual = state,
            message = "退款后状态应为 IDLE"
        )
        assertEquals(
            expected = initBalance,
            actual = state.balance,
            message = "balance 应为：$initBalance"
        )
    }

    @Test
    fun testRequestRefund_outOfStock() {
        // given
        val outOfStockState = OutOfStockState(initBalance)
        setPrivateProperty(vendingMachine, "state", outOfStockState)

        // when
        vendingMachine.requestRefund()

        // then
        val state = getPrivateProperty(vendingMachine, "state")
        assertEquals(
            expected = outOfStockState,
            actual = state,
            message = "状态应为 OUT_OF_STOCK"
        )
    }
}
