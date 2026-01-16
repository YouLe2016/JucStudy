package designpatterns.state.v1

import designpatterns.utils.getPrivateProperty
import designpatterns.utils.setPrivateProperty
import org.junit.jupiter.api.BeforeEach
import java.lang.reflect.Field
import kotlin.test.Test
import kotlin.test.assertEquals


class VendingMachineTest {
    private val vendingMachine = VendingMachine()

    @BeforeEach
    fun setUp() {
        assertEquals(
            expected = State.IDLE,
            actual = getPrivateProperty(vendingMachine, "state"),
            message = "状态应为 IDLE"
        )
    }

    @Test
    fun testInsertMoney_idle() {
        vendingMachine.insertMoney()
        assertEquals(
            expected = State.HAS_MONEY,
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
            expected = State.HAS_MONEY,
            actual = getPrivateProperty(vendingMachine, "state"),
            message = "状态应保持不变"
        )
    }

    @Test
    fun testInsertMoney_OutOfStock() {
        // 强行设置状态为 OUT_OF_STOCK (因为没有正常逻辑能到达这个状态)
        setPrivateProperty(vendingMachine, "state", State.OUT_OF_STOCK)
        vendingMachine.insertMoney()
        assertEquals(
            expected = State.OUT_OF_STOCK,
            actual = getPrivateProperty(vendingMachine, "state"),
            message = "状态应保持不变"
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
        vendingMachine.requestRefund()
        assertEquals(
            expected = State.IDLE,
            actual = getPrivateProperty(vendingMachine, "state"),
            message = "状态应保持不变 IDLE"
        )
    }

    @Test
    fun testRequestRefund_hasMoney() {
        // 进入HAS_MONEY 状态
        vendingMachine.insertMoney()

        vendingMachine.requestRefund()
        assertEquals(
            expected = State.IDLE,
            actual = getPrivateProperty(vendingMachine, "state"),
            message = "状态应变为 IDLE"
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
    }
}