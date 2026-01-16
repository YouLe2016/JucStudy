package designpatterns.state.usecase

object SelectProduceUseCase {
    operator fun invoke(code: String) : Boolean{
        if (code == "1024") {
            println("商品1024已售出")
            return true
        } else {
            println("商品不存在")
            return false
        }
    }
}