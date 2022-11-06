package com.models

import kotlinx.serialization.Serializable

@Serializable
data class Flat(
    val location: String,
    val rooms: String,
    val category: String,
    val floors: String,
    var walls_material: String,
    val floor: String,
    val total_area: String,
    val kitchen_area: String,
    val is_balcony: String,
    val metro_distance: String,
    val condition: String,
    var price: String
)


var analogsFlats: ArrayList<Flat> = ArrayList<Flat>()
var standardFlats: ArrayList<Flat> = ArrayList<Flat>()
var estimationFlats: ArrayList<Flat> = ArrayList<Flat>()
//
val flat1: Flat = Flat(
    "г. Москва ул. Москворечье д.19",
    "1",
    "Новостройка",
    "5",
    "Пенопласт",
    "4",
    "15.5",
    "0.5",
    "Да",
    "15",
    "Без ремонта",
    "235000",
)

val flat2: Flat = Flat(
    "г. Москва ул. Москворечье д.19",
    "1",
    "Новостройка",
    "5",
    "Бетон",
    "4",
    "15.5",
    "0.5",
    "Да",
    "15",
    "Без ремонта",
    "235000",
)

