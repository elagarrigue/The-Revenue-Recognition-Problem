package clean.domain.usecases

import clean.domain.entities.Contract
import clean.domain.entities.RevenueRecognition
import java.math.RoundingMode

object CalculateThreeWayRecognitionUseCase {

    fun execute(
        contract: Contract,
        firstRecognitionOffset: Int,
        secondRecognitionOffset: Int
    ): List<RevenueRecognition> =
        listOf(
            contract.whenSigned,
            contract.whenSigned.plusDays(firstRecognitionOffset),
            contract.whenSigned.plusDays(secondRecognitionOffset)
        ).map {
            RevenueRecognition(
                contract.revenue.dividedBy(3, RoundingMode.DOWN),
                it
            )
        }
}