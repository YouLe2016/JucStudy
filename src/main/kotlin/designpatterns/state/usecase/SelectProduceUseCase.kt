package designpatterns.state.usecase

object SelectProduceUseCase {
    operator fun invoke(code: String) : Boolean{
        if (code == "1024") {
            println("售出 商品1024")
            return true
        } else {
            println("商品不存在")
            return false
        }
    }
}
