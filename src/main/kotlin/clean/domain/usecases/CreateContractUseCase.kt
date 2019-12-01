package clean.domain.usecases

import clean.domain.entities.Contract
import clean.domain.entities.Product
import org.joda.money.Money
import org.joda.time.DateTime

object CreateContractUseCase {

    fun execute(product: Product, money: Money, signDate: DateTime) =
        Contract(product, money, signDate)
            .apply {
                revenueRecognitions.addAll(
                    CalculateRevenueRecognitionsUseCase.execute(this)
                )
            }
}