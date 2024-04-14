package com.github.dhslrl321.webserver.network

import java.lang.Thread.sleep
import java.net.Socket

class AcceptedSocketHandler {

    private val requestHandler = RequestHandler()
    private val responseHandler = ResponseHandler()

    fun handle(acceptedSocket: Socket) {
        requestHandler.handle(acceptedSocket)

        sleep(3000)

        responseHandler.handle(acceptedSocket)
    }
}