package com.github.dhslrl321.webserver.network

import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.Socket

class RequestPrinter {
    fun print(socket: Socket) {
        val inputStream = BufferedReader(InputStreamReader(socket.getInputStream()))
        var line: String
        while ((inputStream.readLine().also { line = it }) != null && line.isNotEmpty()) {
            println(line)
        }
    }
}