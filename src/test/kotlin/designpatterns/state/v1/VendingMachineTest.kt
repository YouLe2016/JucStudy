package designpatterns.state.v1

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
            actual = getPrivateProperty("state"),
            message = "状态应为 IDLE"
        )
    }

    @Test
    fun testInsertMoney_idle() {
        vendingMachine.insertMoney()
        assertEquals(
            expected = State.HAS_MONEY,
            actual = getPrivateProperty("state"),
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
            actual = getPrivateProperty("state"),
            message = "状态应保持不变"
        )
    }

    @Test
    fun testInsertMoney_OutOfStock() {
        // 强行设置状态为 OUT_OF_STOCK (因为没有正常逻辑能到达这个状态)
        setPrivateProperty("state", State.OUT_OF_STOCK)
        vendingMachine.insertMoney()
        assertEquals(
            expected = State.OUT_OF_STOCK,
            actual = getPrivateProperty("state"),
            message = "状态应保持不变"
        )
    }

    @Test
    fun testSelectProduct() {
        vendingMachine.selectProduct("1024")
        vendingMachine.selectProduct("1025")
    }

    @Test
    fun testRequestRefund() {
        vendingMachine.requestRefund()
    }

    /**
     * 辅助方法：通过反射获取私有属性的值 (state, balance, amount)
     */
    private fun getPrivateProperty(propertyName: String): Any? {
        val field: Field = vendingMachine.javaClass.getDeclaredField(propertyName)
        field.isAccessible = true
        return field.get(vendingMachine)
    }

    /**
     * 辅助方法：通过反射强制设置私有属性 (用于测试 OUT_OF_STOCK 等不可达状态)
     */
    private fun setPrivateProperty(propertyName: String, value: Any?) {
        val field: Field = vendingMachine.javaClass.getDeclaredField(propertyName)
        field.isAccessible = true
        field.set(vendingMachine, value)
    }
}