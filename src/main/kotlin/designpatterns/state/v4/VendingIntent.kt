package designpatterns.state.v4

sealed class VendingIntent {
    object InsertMoney: VendingIntent()
    class SelectProduct(val code: String): VendingIntent()
    object RequestRefund: VendingIntent()
}