class Product(
    val name: String,
    val price: Int,
    //ToDo: ↓はSummerDiscountManagerのためだけに追加されている
    val canDiscount: Boolean,
)