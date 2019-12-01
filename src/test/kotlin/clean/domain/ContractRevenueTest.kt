package clean.domain

import clean.domain.entities.Product
import clean.domain.usecases.CreateContractUseCase
import clean.domain.usecases.GetRecognizedRevenueUseCase
import org.amshove.kluent.`should equal`
import org.joda.money.CurrencyUnit
import org.joda.money.Money
import org.joda.time.DateTime
import org.junit.Test

class ContractRevenueTest {

    @Test
    fun `given a word processor product contract, it should full revenue after the contract is signed`() {

        val contract = CreateContractUseCase.execute(
            Product.WordProcessor("Thinking Word"),
            Money.of(CurrencyUnit.USD, 1000.0),
            DateTime.parse("2019-10-15")
        )

        val revenueBefore = GetRecognizedRevenueUseCase.execute(contract, DateTime.parse("2019-10-1"))
        val revenueAfter = GetRecognizedRevenueUseCase.execute(contract, DateTime.parse("2019-10-30"))

        revenueBefore `should equal` Money.of(CurrencyUnit.USD, 0.0)
        revenueAfter `should equal` Money.of(CurrencyUnit.USD, 1000.0)
    }

    @Test
    fun `given a spreadsheet product contract, it should full revenue after the contract is signed`() {

        val signDate = DateTime.parse("2019-3-15")
        val contract = CreateContractUseCase.execute(
            Product.Spreadsheet("Thinking Calc"),
            Money.of(CurrencyUnit.USD, 999.0),
            signDate
        )

        val revenueBefore = GetRecognizedRevenueUseCase.execute(contract, signDate.minusDays(10))
        val revenueAfter2Weeks = GetRecognizedRevenueUseCase.execute(contract, signDate.plusDays(2))
        val revenueAfter2Months = GetRecognizedRevenueUseCase.execute(contract, signDate.plusDays(62))
        val revenueAfter4Months = GetRecognizedRevenueUseCase.execute(contract, signDate.plusDays(92))

        revenueBefore `should equal` Money.of(CurrencyUnit.USD, 0.0)
        revenueAfter2Weeks `should equal` Money.of(CurrencyUnit.USD, 333.0)
        revenueAfter2Months `should equal` Money.of(CurrencyUnit.USD, 666.0)
        revenueAfter4Months `should equal` Money.of(CurrencyUnit.USD, 999.0)
    }

    @Test
    fun `given a database product contract, it should full revenue after the contract is signed`() {

        val signDate = DateTime.parse("2019-3-15")
        val contract = CreateContractUseCase.execute(
            Product.Database("Thinking DB"),
            Money.of(CurrencyUnit.USD, 999.0),
            signDate
        )

        val revenueBefore = GetRecognizedRevenueUseCase.execute(contract, signDate.minusDays(10))
        val revenueAfter2Weeks = GetRecognizedRevenueUseCase.execute(contract, signDate.plusDays(2))
        val revenueAfter1Month = GetRecognizedRevenueUseCase.execute(contract, signDate.plusDays(32))
        val revenueAfter2Months = GetRecognizedRevenueUseCase.execute(contract, signDate.plusDays(62))

        revenueBefore `should equal` Money.of(CurrencyUnit.USD, 0.0)
        revenueAfter2Weeks `should equal` Money.of(CurrencyUnit.USD, 333.0)
        revenueAfter1Month `should equal` Money.of(CurrencyUnit.USD, 666.0)
        revenueAfter2Months `should equal` Money.of(CurrencyUnit.USD, 999.0)
    }
}