package com.github.dhslrl321.webserver.network

import java.io.BufferedWriter
import java.io.OutputStreamWriter
import java.net.Socket

class ResponseReplier {
    fun reply(socket: Socket) {
        val out = BufferedWriter(OutputStreamWriter(socket.getOutputStream()))
        val response = """
            HTTP/1.1 200 OK
            Content-Type: text/plain
            Content-Length: 13
            
            Hello, World!
            """.trimIndent()
        out.write(response)
        out.flush()
    }
}