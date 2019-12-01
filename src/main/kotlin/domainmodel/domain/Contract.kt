package domainmodel.domain

import org.joda.money.CurrencyUnit
import org.joda.money.Money
import org.joda.time.DateTime

class Contract(private val product: Product, val revenue: Money, val whenSigned: DateTime) {

    val revenueRecognitions = mutableListOf<RevenueRecognition>()

    fun recognizedRevenue(asOf: DateTime): Money =
        with(
            revenueRecognitions
                .filter { it.isRecognizableBy(asOf) }
        ) {
            when {
                isEmpty() -> Money.of(CurrencyUnit.USD, 0.0)
                else ->
                    map { it.amount }
                        .reduce { acc, amount ->
                            acc.plus(amount)
                        }
            }
        }


    fun calculateRecognitions() {
        product.calculateRevenueRecognitions(this)
    }
}