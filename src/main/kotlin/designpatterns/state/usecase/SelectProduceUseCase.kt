package designpatterns.state.usecase

object SelectProduceUseCase {
    operator fun invoke(code: String) {
        if (code == "1024") {
            println("商品1024已售出")
        } else {
            println("商品不存在")
        }
    }
}