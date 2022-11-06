package com

import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import com.plugins.*
import io.ktor.http.*
//import io.ktor.server.plugins.cors
import io.ktor.server.plugins.cors.routing.*
import org.ktorm.database.Database

fun main() {
    embeddedServer(Netty, port = 8081, host = "0.0.0.0", module = Application::module)

        .start(wait = true)
}

fun Application.module() {
    install(CORS) {
        anyHost()
        allowMethod(HttpMethod.Get)
        allowMethod(HttpMethod.Options)
        allowMethod(HttpMethod.Put)
        allowMethod(HttpMethod.Patch)
        allowMethod(HttpMethod.Delete)
        allowHeader(HttpHeaders.ContentType)
        allowHeader(HttpHeaders.AccessControlAllowOrigin)
        allowCredentials = true
    }
    configureSerialization()
    configureRouting()

//    val database = Database.connect(
//        url = "jdbc:postgresql://localhost:5432/LGTHack",
//        driver = "org.postgresql.Driver",
//        user = "PolysHacker",
//        password = "qwerty1234"
//    )
}
