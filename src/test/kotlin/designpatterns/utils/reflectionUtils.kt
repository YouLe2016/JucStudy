package designpatterns.utils

import java.lang.reflect.Field

// 这是一个通用的辅助函数，用来递归查找父类中的字段
fun getField(clazz: Class<*>, fieldName: String): Field {
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


/**
 * 辅助方法：通过反射获取私有属性的值 (state, balance, amount)
 */
fun getPrivateProperty(obj: Any, propertyName: String): Any? {
    val field: Field = getField(obj.javaClass, propertyName)
    field.isAccessible = true
    return field.get(obj)
}

/**
 * 辅助方法：通过反射强制设置私有属性 (用于测试 OUT_OF_STOCK 等不可达状态)
 */
fun setPrivateProperty(obj: Any, propertyName: String, value: Any?) {
    val field: Field = getField(obj.javaClass, propertyName)
    field.isAccessible = true
    field.set(obj, value)
}