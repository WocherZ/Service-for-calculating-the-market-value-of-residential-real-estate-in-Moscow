package com.valuation

import com.models.*

fun rangeSquare(square: Double): String {
    return if (square < 30) "<30"
    else if (30 <= square && square < 50) "30-50"
    else if (50 <= square && square < 65) "50-65"
    else if (65 <= square && square < 90) "65-90"
    else if (90 <= square && square < 120) "90-120"
    else ">120"
}

fun rangeKitchen(kitchen: Double): String {
    return if (kitchen < 7) "<7"
    else if (7 <= kitchen && kitchen < 10) "7-10"
    else "10-15"
}

val correctionAuction: Double = -4.5
val correctionFloor = mapOf<Pair<String, String>, Double>( // (analog, estimation)
    Pair("first", "first") to 0.0,
    Pair("first", "middle") to 7.5,
    Pair("first", "last") to 3.2,
    Pair("middle", "first") to -7.0,
    Pair("middle", "middle") to 0.0,
    Pair("middle", "last") to -4.0,
    Pair("last", "first") to -3.1,
    Pair("last", "middle") to 7.5,
    Pair("last", "last") to 0.0
)

val correctionSquare = mapOf<Pair<String, String>, Double>(  // (analog, estimation)
    Pair("<30", "<30") to 0.0, Pair("<30", "30-50") to -6.0, Pair("<30", "50-65") to -12.0, Pair("<30", "65-90") to -17.0, Pair("<30", "90-120") to -22.0, Pair("<30", ">120") to -24.0,
    Pair("30-50", "<30") to 6.0, Pair("30-50", "30-50") to 0.0, Pair("30-50", "50-65") to -7.0, Pair("30-50", "65-90") to -12.0, Pair("30-50", "90-120") to -17.0, Pair("30-50", ">120") to -19.0,
    Pair("50-65", "<30") to 14.0, Pair("50-65", "30-50") to 7.0, Pair("50-65", "50-65") to 0.0, Pair("50-65", "65-90") to -6.0, Pair("50-65", "90-120") to -11.0, Pair("50-65", ">120") to -13.0,
    Pair("65-90", "<30") to 21.0, Pair("65-90", "30-50") to 14.0, Pair("65-90", "50-65") to 6.0, Pair("65-90", "65-90") to 0.0, Pair("65-90", "90-120") to -6.0, Pair("65-90", ">120") to -8.0,
    Pair("90-120", "<30") to 28.0, Pair("90-120", "30-50") to 21.0, Pair("90-120", "50-65") to 13.0, Pair("90-120", "65-90") to 6.0, Pair("90-120", "90-120") to 0.0, Pair("90-120", ">120") to -3.0,
    Pair(">120", "<30") to 31.0, Pair(">120", "30-50") to 24.0, Pair(">120", "50-65") to 16.0, Pair(">120", "65-90") to 9.0, Pair(">120", "90-120") to 3.0, Pair(">120", ">120") to 0.0,
)

val correctionKitchen = mapOf<Pair<String, String>, Double>(  // (analog, estimation)
    Pair("<7", "<7") to 0.0, Pair("<7", "7-10") to 3.0, Pair("<7", "10-15") to 9.0,
    Pair("7-10", "<7") to -2.9, Pair("7-10", "7-10") to 0.0, Pair("7-10", "10-15") to 5.8,
    Pair("10-15", "<7") to -8.3, Pair("10-15", "7-10") to -5.5, Pair("10-15", "10-15") to 0.0,
)

val correctionBalcony = mapOf<Pair<String, String>, Double>(
    Pair("Нет", "Нет") to 0.0,
    Pair("Нет", "Да") to 5.3,
    Pair("Да", "Нет") to -5.0,
    Pair("Да", "Да") to 0.0,
)

val correctionState = mapOf<Pair<String, String>, Double>( // (analog, estimation)
    Pair("Без отделки", "Без отделки") to 0.0,
    Pair("Без отделки", "Эконом") to 13400.0,
    Pair("Без отделки", "Улучшенный") to 20100.0,
    Pair("Эконом", "Без отделки") to -13400.0,
    Pair("Эконом", "Эконом") to 0.0,
    Pair("Эконом", "Улучшенный") to 6700.0,
    Pair("Улучшенный", "Без отделки") to -20100.0,
    Pair("Улучшенный", "Эконом") to -6700.0,
    Pair("Улучшенный", "Улучшенный") to 0.0
)



fun calcCorrections(analog: Flat, flat: Flat): Flat {
    val multiplierFloor: Double
    val multiplierSquare: Double
    val multiplierKitchen: Double
    val multiplierBalcony: Double
    val amountState: Double

    try {
        val analogPrice: Double = analog.price.toDouble()
        val flatPrice: Double = flat.price.toDouble()
        val analogSquare: Double = analog.total_area.toDouble()
        val flatSquare: Double = flat.total_area.toDouble()

        val analogUnitCost = analogPrice / analogSquare
        val flatUnitCost = flatPrice / flatSquare

        val corrFloor: Double? = correctionFloor[Pair(if (analog.floor == "1") "first" else if (analog.floor == analog.floors) "last" else "middle",
            if (flat.floor == "1") "first" else if (flat.floor == flat.floors) "last" else "middle")]
        multiplierFloor = if (corrFloor != null) (100 + corrFloor) / 100 else 1.0

        val corrSquare: Double? = correctionSquare[Pair(rangeSquare(analogSquare), rangeSquare(flatSquare))]
        multiplierSquare = if (corrSquare != null) (100 + corrSquare) / 100 else 1.0

        val corrKitchen: Double? = correctionKitchen[Pair(rangeKitchen(analog.kitchen_area.toDouble()), rangeKitchen(flat.kitchen_area.toDouble()))]
        multiplierKitchen = if (corrKitchen != null) (100 + corrKitchen) / 100 else 1.0

        val corrBalcony: Double? = correctionBalcony[Pair(analog.is_balcony, flat.is_balcony)]
        multiplierBalcony = if (corrBalcony != null) (100 + corrBalcony) / 100 else 1.0

        val corrState: Double? = correctionState[Pair(analog.condition, flat.condition)]
        amountState = corrState ?: 0.0

        flat.price = ((analogUnitCost * multiplierFloor * multiplierSquare * multiplierBalcony * multiplierKitchen * correctionAuction + amountState) * flatSquare).toString()

    } catch (e : Exception) {
        flat.price = analog.price
    }
    return flat
}


fun calcStandardPrice(analogsFlats: List<Flat>, standardFlats: List<Flat>): List<Flat> {
    val result: ArrayList<Flat> = ArrayList()
    for (standardFlat in standardFlats) {
        var correctionFlat: Flat = standardFlat
        val analogs: ArrayList<Flat> = ArrayList()
        for (analogFlat in analogsFlats) {
            if (analogFlat.rooms == standardFlat.rooms) { analogs.add(analogFlat) }
        }
        for (analogFlat in analogs) {
            correctionFlat = calcCorrections(analogFlat, correctionFlat)
        }
        result.add(correctionFlat)
    }
    return result
}


fun estimationFlatsPrice(
    analogsFlats: List<Flat>, // с price
    standardFlats: List<Flat>,
    estimationFlats: ArrayList<Flat>): ArrayList<Flat> {
    val newStandardFlats = calcStandardPrice(analogsFlats, standardFlats) // Расчитанные эталонные объекты с ценой
    val newEstFlats: ArrayList<Flat> = ArrayList()
    for (estimationFlat in estimationFlats) {
        val analogs: ArrayList<Flat> = ArrayList()
        var estFlat: Flat = estimationFlat
        for (analog in analogsFlats) {
            if (analog.rooms == estimationFlat.rooms) { analogs.add(analog) }
        }
        for (standard in standardFlats) {
            estFlat = calcCorrections(standard, estFlat)
        }
        newEstFlats.add(estFlat)
    }
    return newEstFlats
}