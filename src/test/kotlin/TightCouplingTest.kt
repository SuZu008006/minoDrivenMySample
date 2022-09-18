import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Nested

class TightCouplingTest {
    @Nested
    inner class BadPattern {
        @Nested
        inner class RegularDiscount {
            @Nested
            inner class AboveCertainValue {
                private lateinit var discountManager: DiscountManager

                @BeforeEach
                fun setUp() {
                    discountManager = DiscountManager()
                }

                @Test
                fun `追加する商品が割引対象である時、300円を引いてカートに追加する`() {
                    val product = Product("いちご", 500, false)
                    val expectedDiscountedProductPrice = product.price - 300
                    val productDiscount = ProductDiscount(true)


                    val isAddInCart = discountManager.add(product, productDiscount)


                    assertEquals(true, isAddInCart)
                    assertEquals(expectedDiscountedProductPrice, discountManager.totalPrice)
                }

                @Test
                fun `追加する商品が割引対象でない時、お値段据え置きでカートに追加する`() {
                    val product = Product("ばなな", 600, false)
                    val expectedProductPrice = product.price
                    val productDiscount = ProductDiscount(false)


                    val isAddInCart = discountManager.add(product, productDiscount)


                    assertEquals(true, isAddInCart)
                    assertEquals(expectedProductPrice, discountManager.totalPrice)
                }
            }

            @Nested
            inner class BelowCertainValue {
                private lateinit var discountManager: DiscountManager

                @BeforeEach
                fun setUp() {
                    discountManager = DiscountManager()

                    val geta = Product("ゲタ", 20000, false)
                    val getaProductDiscount = ProductDiscount(false)

                    discountManager.add(geta, getaProductDiscount)

                    assertEquals(20000, discountManager.totalPrice)
                }

                @Test
                fun `追加する商品が割引対象である時、カートに追加されない`() {
                    val product = Product("いちご", 500, false)
                    val productDiscount = ProductDiscount(true)


                    val actualDiscountPrice = discountManager.add(product, productDiscount)


                    assertEquals(false, actualDiscountPrice)
                }

                @Test
                fun `追加する商品が割引対象でない時、カートに追加されない`() {
                    val product = Product("ばなな", 600, false)
                    val productDiscount = ProductDiscount(false)


                    val actualDiscountPrice = discountManager.add(product, productDiscount)


                    assertEquals(false, actualDiscountPrice)
                }
            }
        }

        @Nested
        inner class SummerDiscount {
            @Nested
            inner class AboveCertainValue {
                private lateinit var summerDiscountManager: SummerDiscountManager

                @BeforeEach
                fun setUp() {
                    summerDiscountManager = SummerDiscountManager()
                }

                @Test
                fun `追加する商品が割引対象である時、300円を引いてカートに追加する`() {
                    val product = Product("いちご", 500, true)
                    val expectedDiscountedProductPrice = product.price - 300


                    val isAddInCart = summerDiscountManager.add(product)


                    assertEquals(true, isAddInCart)
                    assertEquals(expectedDiscountedProductPrice, summerDiscountManager.discountManager.totalPrice)
                }

                @Test
                fun `追加する商品が割引対象でない時、お値段据え置きでカートに追加する`() {
                    val product = Product("ばなな", 600, false)
                    val expectedProductPrice = product.price


                    val isAddInCart = summerDiscountManager.add(product)


                    assertEquals(true, isAddInCart)
                    assertEquals(expectedProductPrice, summerDiscountManager.discountManager.totalPrice)
                }
            }

            @Nested
            inner class BelowCertainValue {
                private lateinit var summerDiscountManager: SummerDiscountManager

                @BeforeEach
                fun setUp() {
                    summerDiscountManager = SummerDiscountManager()

                    val geta = Product("ゲタ", 30000, false)
                    summerDiscountManager.add(geta)

                    assertEquals(30000, summerDiscountManager.discountManager.totalPrice)
                }

                @Test
                fun `追加する商品が割引対象である時、カートに追加されない`() {
                    val product = Product("いちご", 500, true)


                    val actualDiscountPrice = summerDiscountManager.add(product)


                    assertEquals(false, actualDiscountPrice)
                }

                @Test
                fun `追加する商品が割引対象でない時、カートに追加されない`() {
                    val product = Product("ばなな", 600, false)


                    val actualDiscountPrice = summerDiscountManager.add(product)


                    assertEquals(false, actualDiscountPrice)
                }
            }
        }
    }

    @Nested
    inner class GoodPattern {

        private val regularPrice = RegularPrice()
        private val product = Product("いちご", 500, true)
        private val expectedPrice = product.price - 300

        @Test
        fun `通常割引が適用される`() {
            regularPrice.regularPrice(product)
            val regularDiscountedPrice = RegularDiscountedPrice()


            regularDiscountedPrice
                .regularDiscountedPrice(regularPrice)


            assertEquals(expectedPrice, regularDiscountedPrice.amount)
        }

        @Test
        fun `夏季割引が適用される`() {
            regularPrice.regularPrice(product)
            val summerDiscountedPrice = SummerDiscountedPrice()


            summerDiscountedPrice
                .summerDiscountedPrice(regularPrice)


            assertEquals(expectedPrice, summerDiscountedPrice.amount)
        }
    }
}