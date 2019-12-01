package domainmodel.domain

import java.math.RoundingMode

class ThreeWayRecognitionStrategy(
    private val firstRecognitionOffset: Int, private val secondRecognitionOffset: Int
) : RecognitionStrategy() {

    override fun calculateRevenueRecognitions(contract: Contract) {

        listOf(
            contract.whenSigned,
            contract.whenSigned.plusDays(firstRecognitionOffset),
            contract.whenSigned.plusDays(secondRecognitionOffset)
        ).forEach {
            contract.revenueRecognitions.add(
                RevenueRecognition(
                    contract.revenue.dividedBy(3, RoundingMode.DOWN),
                    it
                )
            )
        }
    }
}