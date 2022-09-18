class RegularPrice {
    var amount: Int = 0
    fun regularPrice(product: Product) {
        this.amount = product.price
    }
}