package com.routes

import com.models.*
import com.valuation.estimationFlatsPrice
import java.net.http.HttpRequest
import io.ktor.server.routing.*
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import java.net.URI
import java.net.http.HttpClient
import java.net.http.HttpResponse

fun Flat.toMap(): Map<String, String> {
    return mapOf(
        "location" to this.location,
        "rooms" to this.rooms,
        "category" to this.category,
        "floors" to this.floors,
        "walls_material" to this.walls_material,
        "floor" to this.floor,
        "total_area" to this.total_area,
        "kitchen_area" to this.kitchen_area,
        "is_balcony" to this.is_balcony,
        "metro_distance" to this.metro_distance,
        "condition" to this.condition,
        "price" to this.price
    )
}

fun ArrayList<String>.addFlatAsString(flat: Flat) {
    this.add(flat.location)
    this.add(flat.rooms)
    this.add(flat.category)
    this.add(flat.floors)
    this.add(flat.walls_material)
    this.add(flat.floor)
    this.add(flat.total_area)
    this.add(flat.kitchen_area)
    this.add(flat.is_balcony)
    this.add(flat.metro_distance)
    this.add(flat.condition)
    this.add(flat.price)
}


fun Route.flatRouting() {
    route("/api") {
        post("/standard") {// Получение эталонных объектов
            println("/standard post")
            val receivedData: Map<String, Flat?> = call.receive<Map<String, Flat?>>()  // Получение данных
            for ( flat in receivedData.values ) {
                if (flat != null) {
                    standardFlats.add(flat)
                }
            }
            call.respondText("OK", status = HttpStatusCode.Created)
        }

        post("/analogs") {// Получение выбранных пользователем квартир-аналогов
            println("/analogs post")
            val receivedData: ArrayList<Flat> = call.receive<ArrayList<Flat>>()  // Получение данных
            analogsFlats = receivedData
            call.respondText("OK", status = HttpStatusCode.Created)
        }

        get("/analogs") {// Отправка квартир-аналогов
            println("/analogs get")
            val resultData: ArrayList<ArrayList<String>> = ArrayList();
            for (flat in analogsFlats) {
                val params = flat.toMap()
                val urlParams = params.map{ (k, v) -> "${k}=${v}" }.joinToString("&")
                val url: String = "http://127.0.0.1:5000?${urlParams}"
                println(url)
                val client = HttpClient.newBuilder().build()
                val request = HttpRequest.newBuilder().uri(URI.create(url)).build()
                val response = client.send(request, HttpResponse.BodyHandlers.ofString())

            }
            call.respond(resultData)
        }

        post("/estimations") {// Получение объектов оценки
            println("/estimations post")
            val receivedData: ArrayList<Flat> = call.receive<ArrayList<Flat>>()  // Получение данных
            estimationFlats = receivedData
            call.respond(HttpStatusCode.OK)
        }

        get("/estimations") {// Отправка всех объектов оценки
            println("/estimations get")
            val resultData: ArrayList<ArrayList<String>> = ArrayList<ArrayList<String>>()
            estimationFlats = estimationFlatsPrice(analogsFlats, standardFlats, estimationFlats)
            for (flat in estimationFlats) {
                val array: ArrayList<String> = ArrayList()
                array.addFlatAsString(flat)
                resultData.add(array)
            }
            call.respond(resultData)
        }

        options("/estimations") {
            call.response.header(
                "Access-Control-Allow-Headers",
                "Access-Control-Allow-Headers, Origin,Accept, X-Requested-With, Content-Type, Access-Control-Request-Method, Access-Control-Request-Headers"
            )
            call.respond(HttpStatusCode.OK)
        }

        options("/analogs") {
            call.response.header(
                "Access-Control-Allow-Headers",
                "Access-Control-Allow-Headers, Origin,Accept, X-Requested-With, Content-Type, Access-Control-Request-Method, Access-Control-Request-Headers"
            )
            call.respond(HttpStatusCode.OK)
        }

        options("/standard") {
            call.response.header(
                "Access-Control-Allow-Headers",
                "Access-Control-Allow-Headers, Origin,Accept, X-Requested-With, Content-Type, Access-Control-Request-Method, Access-Control-Request-Headers"
            )
            call.respond(HttpStatusCode.OK)
        }
    }
}