package com.routes

import com.models.*
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.userRouting() {
    route("/user") {
        post("/register") {
            val receivedData = call.receive<User>()
            println(receivedData)
            if (receivedData in userStorage) {
                println("register bad")
                call.respond(HttpStatusCode.Created)
            } else {
                println("register ok")
                userStorage.add(receivedData)
                call.respond(HttpStatusCode.OK)
            }
        }

        post("/login") {
            val receivedData = call.receive<User>()
            println(receivedData)
            if (receivedData.login in userStorage.toLoginList()) {
                println("login ok")
                call.respond(HttpStatusCode.OK)
            } else {
                println("login bad")
                call.respond(HttpStatusCode.Created)
            }
        }

        options("/register") {
            call.response.header(
                "Access-Control-Allow-Headers",
                "Access-Control-Allow-Headers, Origin,Accept, X-Requested-With, Content-Type, Access-Control-Request-Method, Access-Control-Request-Headers"
            )
            call.respond(HttpStatusCode.OK)
        }

        options("/login") {
            call.response.header(
                "Access-Control-Allow-Headers",
                "Access-Control-Allow-Headers, Origin,Accept, X-Requested-With, Content-Type, Access-Control-Request-Method, Access-Control-Request-Headers"
            )
            call.respond(HttpStatusCode.OK)
        }
    }
}