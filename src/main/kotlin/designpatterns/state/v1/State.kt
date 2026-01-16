package designpatterns.state.v1

enum class State {
    /**
     * 空闲
     */
    IDLE,

    /**
     * 已投币
     */
    HAS_MONEY,

    /**
     * 售罄
     */
    OUT_OF_STOCK
}