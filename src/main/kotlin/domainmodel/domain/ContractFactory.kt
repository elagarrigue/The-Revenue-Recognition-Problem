package domainmodel.domain

import org.joda.money.Money
import org.joda.time.DateTime

object ContractFactory {

    fun get(product: Product, money: Money, signDate: DateTime) =
        Contract(product, money, signDate)
            .apply {
                calculateRecognitions()
            }
}