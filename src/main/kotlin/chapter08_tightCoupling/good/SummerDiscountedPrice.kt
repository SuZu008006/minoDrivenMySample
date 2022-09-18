package chapter08_tightCoupling.good

class SummerDiscountedPrice {
    private val DISCOUNT_AMOUNT = 300
    var amount: Int = 0

    fun summerDiscountedPrice(price: RegularPrice) {
        val discountedAmount = price.amount - DISCOUNT_AMOUNT

        amount = discountedAmount
    }
}