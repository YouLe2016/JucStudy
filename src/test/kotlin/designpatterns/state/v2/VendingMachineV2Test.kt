package designpatterns.state.v2

import designpatterns.state.v2.state.HasMoneyState
import designpatterns.state.v2.state.OutOfStockState
import java.lang.reflect.Field
import kotlin.test.Test
import kotlin.test.assertEquals

class VendingMachineV2Test {
    private val vendingMachine = VendingMachineV2()

    @Test
    fun testInsertMoney_idle() {
        vendingMachine.insertMoney()
        assertEquals(
            expected = HasMoneyState,
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
            expected = HasMoneyState,
            actual = getPrivateProperty("state"),
            message = "状态应保持不变"
        )
    }

    @Test
    fun testInsertMoney_OutOfStock() {
        // 强行设置状态为 OUT_OF_STOCK (因为没有正常逻辑能到达这个状态)
        setPrivateProperty("state", OutOfStockState)
        vendingMachine.insertMoney()
        assertEquals(
            expected = OutOfStockState,
            actual = getPrivateProperty("state"),
            message = "状态应保持不变"
        )
    }

//    @Test
//    fun testSelectProduct() {
//        vendingMachine.selectProduct()
//    }
//
//    @Test
//    fun testRequestRefund() {
//        vendingMachine.requestRefund()
//    }


    /**
     * 辅助方法：通过反射获取私有属性的值 (state, balance, amount)
     */
    private fun getPrivateProperty(propertyName: String): Any? {
        val field: Field = getField(vendingMachine.javaClass, propertyName)
        field.isAccessible = true
        return field.get(vendingMachine)
    }

    /**
     * 辅助方法：通过反射强制设置私有属性 (用于测试 OUT_OF_STOCK 等不可达状态)
     */
    private fun setPrivateProperty(propertyName: String, value: Any?) {
        val field: Field = getField(vendingMachine.javaClass, propertyName)
        field.isAccessible = true
        field.set(vendingMachine, value)
    }

    // 这是一个通用的辅助函数，用来递归查找父类中的字段
    private fun getField(clazz: Class<*>, fieldName: String): Field {
        var currentClass: Class<*>? = clazz
        // 循环向上查找父类
        while (currentClass != null) {
            try {
                // 尝试在当前类中找
                val field = currentClass.getDeclaredField(fieldName)
                field.isAccessible = true // 暴力破解权限
                return field
            } catch (e: NoSuchFieldException) {
                // 当前类没找到，继续找父类
                currentClass = currentClass.superclass
            }
        }
        throw NoSuchFieldException("Field '$fieldName' not found in hierarchy of ${clazz.name}")
    }
}