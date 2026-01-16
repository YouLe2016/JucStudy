package designpatterns.state.v4

sealed class VendingState {
    abstract fun handle(intent: VendingIntent): VendingState

    object Default : VendingState() {
        override fun handle(intent: VendingIntent): VendingState {
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
            return Default
        }
    }
}