package designpatterns.state.v4

sealed class VendingIntent {
    object InsertMoney: VendingIntent()
    object SelectProduct: VendingIntent()
    object RequestRefund: VendingIntent()
}