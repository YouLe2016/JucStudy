package designpatterns.state.v1

import designpatterns.utils.getPrivateProperty
import designpatterns.utils.setPrivateProperty
import org.junit.jupiter.api.BeforeEach
import kotlin.test.Test
import kotlin.test.assertEquals


class VendingMachineTest {
    private val initBalance = 10
    private val vendingMachine = VendingMachine(initBalance)

    @BeforeEach
    fun setUp() {
        assertEquals(
            expected = State.IDLE,
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
            expected = State.HAS_MONEY,
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
            expected = State.HAS_MONEY,
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
        // given
        // 强行设置状态为 OUT_OF_STOCK (因为没有正常逻辑能到达这个状态)
        setPrivateProperty(vendingMachine, "state", State.OUT_OF_STOCK)

        // when
        vendingMachine.insertMoney(1)

        // then
        assertEquals(
            expected = State.OUT_OF_STOCK,
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
            expected = State.IDLE,
            actual = getPrivateProperty(vendingMachine, "state"),
            message = "状态应保持不变 IDLE"
        )
        vendingMachine.selectProduct("1025")
        assertEquals(
            expected = State.IDLE,
            actual = getPrivateProperty(vendingMachine, "state"),
            message = "状态应保持不变 IDLE"
        )
    }
    @Test
    fun testSelectProduct_hasMoney() {
        // 进入HAS_MONEY 状态
        vendingMachine.insertMoney()
        vendingMachine.selectProduct("1024")
        assertEquals(
            expected = State.IDLE,
            actual = getPrivateProperty(vendingMachine, "state"),
            message = "状态应为 IDLE"
        )

        // 进入HAS_MONEY 状态
        vendingMachine.insertMoney()
        vendingMachine.selectProduct("1025")
        assertEquals(
            expected = State.HAS_MONEY,
            actual = getPrivateProperty(vendingMachine, "state"),
            message = "状态应保持不变 HAS_MONEY"
        )
    }


    @Test
    fun testSelectProduct_outOfStock() {
        // 强行设置状态为 OUT_OF_STOCK (因为没有正常逻辑能到达这个状态)
        setPrivateProperty(vendingMachine, "state", State.OUT_OF_STOCK)
        vendingMachine.selectProduct("1024")
        assertEquals(
            expected = State.OUT_OF_STOCK,
            actual = getPrivateProperty(vendingMachine, "state"),
            message = "状态应保持不变 OUT_OF_STOCK"
        )
        vendingMachine.selectProduct("1025")
        assertEquals(
            expected = State.OUT_OF_STOCK,
            actual = getPrivateProperty(vendingMachine, "state"),
            message = "状态应保持不变 OUT_OF_STOCK"
        )
    }

    @Test
    fun testRequestRefund_idle() {
        // when
        vendingMachine.requestRefund()

        // then
        assertEquals(
            expected = State.IDLE,
            actual = getPrivateProperty(vendingMachine, "state"),
            message = "状态应保持不变 IDLE"
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

        // when
        vendingMachine.requestRefund()

        // then
        assertEquals(
            expected = State.IDLE,
            actual = getPrivateProperty(vendingMachine, "state"),
            message = "状态应变为 IDLE"
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
        // 强行设置状态为 OUT_OF_STOCK (因为没有正常逻辑能到达这个状态)
        setPrivateProperty(vendingMachine, "state", State.OUT_OF_STOCK)

        vendingMachine.requestRefund()
        assertEquals(
            expected = State.OUT_OF_STOCK,
            actual = getPrivateProperty(vendingMachine, "state"),
            message = "状态应保持不变 OUT_OF_STOCK"
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
