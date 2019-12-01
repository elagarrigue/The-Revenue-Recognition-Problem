package clean.domain.usecases

import clean.domain.entities.Contract
import clean.domain.entities.RevenueRecognition

object CalculateCompleteRecognitionUseCase {

    fun execute(contract: Contract): List<RevenueRecognition> =
        listOf(
            RevenueRecognition(
                contract.revenue,
                contract.whenSigned
            )
        )
}