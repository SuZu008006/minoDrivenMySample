package chapter08_tightCoupling.bad

class SummerDiscountManager {
    val discountManager = DiscountManager()

    fun add(product: Product): Boolean {
        val tmpPrice: Int = if (product.canDiscount) {
            discountManager.totalPrice + discountManager.getDiscountPrice(product.price)
        } else {
            discountManager.totalPrice + product.price
        }

        if (tmpPrice <= 30000) {
            discountManager.totalPrice = tmpPrice
            return true
        }
        return false
    }
}
