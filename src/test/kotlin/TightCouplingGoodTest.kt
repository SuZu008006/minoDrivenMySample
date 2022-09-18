import chapter08_tightCoupling.good.RegularDiscountedPrice
import chapter08_tightCoupling.good.RegularPrice
import chapter08_tightCoupling.good.SummerDiscountedPrice
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*

class TightCouplingGoodTest {
    private val regularPrice = RegularPrice()
    private val settingPrice = 500
    private val expectedPrice = settingPrice - 300

    @Test
    fun `通常割引が適用される`() {
        regularPrice.regularPrice(settingPrice)
        val regularDiscountedPrice = RegularDiscountedPrice()


        regularDiscountedPrice
            .regularDiscountedPrice(regularPrice)


        assertEquals(expectedPrice, regularDiscountedPrice.amount)
    }

    @Test
    fun `夏季割引が適用される`() {
        regularPrice.regularPrice(settingPrice)
        val summerDiscountedPrice = SummerDiscountedPrice()


        summerDiscountedPrice
            .summerDiscountedPrice(regularPrice)


        assertEquals(expectedPrice, summerDiscountedPrice.amount)
    }
}