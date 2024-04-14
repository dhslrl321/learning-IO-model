package com.github.dhslrl321.webserver.network

import java.io.BufferedWriter
import java.io.OutputStreamWriter
import java.net.Socket
import java.util.UUID

class ResponseHandler {
    fun handle(socket: Socket) {
        val out = BufferedWriter(OutputStreamWriter(socket.getOutputStream()))

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

        if (socket.isClosed.not()) socket.close()
    }
}