package domainmodel.domain

class Product private constructor(val name: String, private val recognitionStrategy: RecognitionStrategy) {

    companion object {
        fun newWordProcessor(name: String) = Product(
            name,
            CompleteRecognitionStrategy()
        )

        fun newSpreadsheet(name: String) = Product(
            name,
            ThreeWayRecognitionStrategy(60, 90)
        )

        fun newDatabase(name: String) = Product(
            name,
            ThreeWayRecognitionStrategy(30, 60)
        )

    }

    fun calculateRevenueRecognitions(contract: Contract) {
        recognitionStrategy.calculateRevenueRecognitions(contract)
    }
}