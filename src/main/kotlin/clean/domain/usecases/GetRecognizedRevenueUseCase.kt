package clean.domain.usecases

import clean.domain.entities.Contract
import org.joda.money.Money
import org.joda.time.DateTime

object GetRecognizedRevenueUseCase {

    fun execute(contract: Contract, asOf: DateTime): Money =
        with(
            contract.revenueRecognitions
                .filter { it.isRecognizableBy(asOf) }
        ) {
            when {
                isEmpty() ->Money.of(org.joda.money.CurrencyUnit.USD, 0.0)
                else ->
                    map { it.amount }
                        .reduce { acc, amount ->
                            acc.plus(amount)
                        }
            }
        }
}