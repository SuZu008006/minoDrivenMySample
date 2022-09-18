package chapter08_tightCoupling.bad

class DiscountManager {
    var totalPrice = 0

    fun add(product: Product, productDiscount: ProductDiscount): Boolean {
        val discountPrice = getDiscountPrice(product.price)

        val tmpPrice: Int = if (productDiscount.canDiscount) {
            totalPrice + discountPrice
        } else {
            totalPrice + product.price
        }

        if (tmpPrice <= 20000) {
            totalPrice = tmpPrice
            return true
        }
        return false
    }

    fun getDiscountPrice(price: Int): Int {
        return price - 300
    }
}

