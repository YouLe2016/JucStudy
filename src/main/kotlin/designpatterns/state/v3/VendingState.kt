package designpatterns.state.v3

sealed interface VendingState {
    fun handle(intent: VendingIntent)

    object Default : VendingState {
        override fun handle(intent: VendingIntent) {
            when (intent) {
                is VendingIntent.InsertMoney -> {
                    println("投币操作")
                }

                is VendingIntent.SelectProduct -> {
                    println("取货操作")
                }

                is VendingIntent.RequestRefund -> {
                    println("退币操作")
                }
            }
        }
    }
}