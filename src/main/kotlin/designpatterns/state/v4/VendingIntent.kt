package designpatterns.state.v4

sealed class VendingIntent {
    class InsertMoney(val amount: Int): VendingIntent()
    class SelectProduct(val code: String): VendingIntent()
    object RequestRefund: VendingIntent()
}
