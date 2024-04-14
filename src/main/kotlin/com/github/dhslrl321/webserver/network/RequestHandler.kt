package com.github.dhslrl321.webserver.network

import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.Socket
import java.time.Instant
import java.util.UUID

class RequestHandler {
    fun handle(socket: Socket) {
        println("[----http 요청 인입됨----]")
        println("request(${UUID.randomUUID()}) 인입됨::${Instant.now()}")
    }
}