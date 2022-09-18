class RegularDiscountedPrice {
    private val DISCOUNT_AMOUNT: Int = 300
    var amount: Int = 0

    fun regularDiscountedPrice(price: RegularPrice) {
        val discountedAmount = price.amount - DISCOUNT_AMOUNT

        amount = discountedAmount
    }
}