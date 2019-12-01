package clean.domain.entities

import org.joda.money.Money
import org.joda.time.DateTime

class Contract(val product: Product, val revenue: Money, val whenSigned: DateTime) {

    val revenueRecognitions = mutableListOf<RevenueRecognition>()
}