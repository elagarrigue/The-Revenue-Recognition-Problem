package clean.domain.usecases

import clean.domain.entities.Contract
import clean.domain.entities.Product
import clean.domain.entities.RevenueRecognition

object CalculateRevenueRecognitionsUseCase {

    fun execute(contract: Contract): List<RevenueRecognition> =
        when (contract.product) {
            is Product.WordProcessor ->
                CalculateCompleteRecognitionUseCase.execute(contract)
            is Product.Spreadsheet ->
                CalculateThreeWayRecognitionUseCase.execute(contract, 60, 90)
            is Product.Database ->
                CalculateThreeWayRecognitionUseCase.execute(contract, 30, 60)
        }
}