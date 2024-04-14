package com.github.dhslrl321.webserver

import com.github.dhslrl321.webserver.io.IoHandlers
import com.github.dhslrl321.webserver.io.IoModels
import com.github.dhslrl321.webserver.io.IoModels.SYNC_BLOCKING_IO
import java.net.ServerSocket

class MyWebServer {

}

fun main() {
    val ioModel = IoModels.SYNC_BLOCKING_IO
    println("[---my-http-server---]")
    println("[--------------------]")
    println("[----initialized!----]\n\n")
    println("[io-mode: $]\n\n")

    val ioHandler = IoHandlers.get(SYNC_BLOCKING_IO)
}