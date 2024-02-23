package com.example.testmobapp.presentation.utils


//Converting days of month to Russian lang
fun convertDayToRus(dayOfWeek: String) = when (dayOfWeek) {
    "MONDAY" -> "Понедельник"
    "TUESDAY" -> "Вторник"
    "WEDNESDAY" -> "Среда"
    "THURSDAY" -> "Четверг"
    "FRIDAY" -> "Пятница"
    "SATURDAY" -> "Суббота"
    else -> "Воскресенье"
}

fun convertDayToRusSmall(dayOfWeek: String) = when (dayOfWeek) {
    "MONDAY" -> "Пн"
    "TUESDAY" -> "Вт"
    "WEDNESDAY" -> "Ср"
    "THURSDAY" -> "Чт"
    "FRIDAY" -> "Пт"
    "SATURDAY" -> "Сб"
    else -> "Вс"
}

fun convertMonthToRus(month: String) = when (month) {
    "JANUARY" -> "Январь"
    "FEBRUARY" -> "Февраль"
    "MARCH" -> "Март"
    "APRIL" -> "Апрель"
    "MAY" -> "Май"
    "JUNE" -> "Июнь"
    "JULY" -> "Июль"
    "AUGUST" -> "Август"
    "SEPTEMBER" -> "Сентябрь"
    "OCTOBER" -> "Октябрь"
    "NOVEMBER" -> "Ноябрь"
    else -> "Декабрь"
}

enum class WeekDays(val rus: String, val rusShort: String) {
    MONDAY("Понедельник", "Пн"),
    TUESDAY("Вторник", "Вт"),
    WEDNESDAY("Среда", "Ср"),
    THURSDAY("Четверг", "Чт"),
    FRIDAY("Пятница", "Пт"),
    SATURDAY("Субботу", "Сб"),
    SUNDAY("Воскресенье", "Вс"),
}

enum class Months(val rus: String) {
    JANUARY("Январь"),
    FEBRUARY("Февраль"),
    MARCH("Март"),
    APRIL("Апрель"),
    MAY("Май"),
    JUNE("Июнь"),
    JULY("Июль"),
    AUGUST("Август"),
    SEPTEMBER("Сентябрь"),
    OCTOBER("Октябрь"),
    NOVEMBER("Ноябрь"),
    DECEMBER("Декабрь")
}