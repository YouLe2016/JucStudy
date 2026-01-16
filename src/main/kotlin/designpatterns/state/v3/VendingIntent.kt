package designpatterns.state.v3

sealed class VendingIntent {
    object InsertMoney : VendingIntent()
    class SelectProduct(val code: String) : VendingIntent()
    object RequestRefund : VendingIntent()
}