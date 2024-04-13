package com.github.dhslrl321.webserver

import com.github.dhslrl321.webserver.network.ResponseReplier
import com.github.dhslrl321.webserver.network.RequestPrinter
import java.net.ServerSocket

class MyWebServer {

}

fun main() {
    val requestPrinter = RequestPrinter()
    val responseReplier = ResponseReplier()

    val serverSocket = ServerSocket(8080)

    while (true) {
        val acceptedSocket = serverSocket.accept()

        requestPrinter.print(acceptedSocket)
        responseReplier.reply(acceptedSocket)
    }
}