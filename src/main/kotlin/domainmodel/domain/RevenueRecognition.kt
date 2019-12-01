package domainmodel.domain

import org.joda.money.Money
import org.joda.time.DateTime

data class RevenueRecognition(val amount: Money, private val date: DateTime) {

    fun isRecognizableBy(asOf: DateTime) = asOf.isAfter(date) || asOf == date
}



