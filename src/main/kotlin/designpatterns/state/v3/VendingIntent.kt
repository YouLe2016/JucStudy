package designpatterns.state.v3

sealed class VendingIntent {
    object InsertMoney: VendingIntent()
    object SelectProduct: VendingIntent()
    object RequestRefund: VendingIntent()
}