package com.github.dhslrl321.webserver.blocking

import java.io.BufferedWriter
import java.io.OutputStreamWriter
import java.lang.Thread.sleep
import java.net.Socket
import java.time.Instant
import java.util.*

class AcceptedSocketHandler {

    fun handle(acceptedSocket: Socket) {
        println("[----http 요청 인입됨----]")
        println("request(${UUID.randomUUID()}) 인입됨::${Instant.now()}")

//        sleep(3000)

        handleResponse(acceptedSocket)
    }

    private fun handleResponse(acceptedSocket: Socket) {
        val out = BufferedWriter(OutputStreamWriter(acceptedSocket.getOutputStream()))

        val body = "Hello, World! ${UUID.randomUUID()}"

        val response = """
                HTTP/1.1 200 OK
                Content-Type: text/plain
                Content-Length: ${body.length}  
                
                $body
                """.trimIndent()
        out.write(response)
        out.flush()
        out.close()

        if (acceptedSocket.isClosed.not()) acceptedSocket.close()
    }
}