package com.routes

import com.models.*
import io.ktor.server.routing.*
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

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
            //standardFlats = receivedData
            //val receivedData: Map<Any, Any?> = call.receive()
            println(receivedData)
            call.respondText("OK", status = HttpStatusCode.Created)
        }

        post("/analogs") {// Получение выбранных пользователем квартир-аналогов
            println("/analogs post")
            val receivedData: ArrayList<Flat> = call.receive<ArrayList<Flat>>()  // Получение данных
            analogsFlats = receivedData
            println(receivedData)
            call.respondText("OK", status = HttpStatusCode.Created)
        }

        get("/analogs") {// Отправка квартир-аналогов
            println("/analogs get")
            analogsFlats.add(flat1)
            analogsFlats.add(flat2)
            val resultData: ArrayList<ArrayList<String>> = ArrayList<ArrayList<String>>()
            for (value in analogsFlats) {
                val flatArray: ArrayList<String> = ArrayList<String>();
                flatArray.addFlatAsString(value.also {
                    it.price = "101010101"
                })
                resultData.add(flatArray)
            }
            call.respond(resultData)
        }

        post("/estimations") {// Получение объектов оценки
            println("/estimations post")
            val receivedData: ArrayList<Flat> = call.receive<ArrayList<Flat>>()  // Получение данных
            estimationFlats = receivedData
            println(receivedData)
            //call.respondText("OK", status = HttpStatusCode.Created)
            call.respond(HttpStatusCode.OK)
        }

        get("/estimations") {// Отправка всех объектов оценки
            println("/estimations get")
            val resultData: ArrayList<ArrayList<String>> = ArrayList<ArrayList<String>>()
            for (value in analogsFlats) {
                val flatArray: ArrayList<String> = ArrayList<String>();
                flatArray.addFlatAsString(value.also {
                    it.price = "123456789"
                })
                resultData.add(flatArray)
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