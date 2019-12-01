package domainmodel.domain

class CompleteRecognitionStrategy : RecognitionStrategy() {

    override fun calculateRevenueRecognitions(contract: Contract) {
        contract.revenueRecognitions.add(
            RevenueRecognition(
                contract.revenue,
                contract.whenSigned
            )
        )
    }
}