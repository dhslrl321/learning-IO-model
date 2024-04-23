package com.github.dhslrl321.webserver

import com.github.dhslrl321.webserver.server.IoServers
import com.github.dhslrl321.webserver.server.IoModel.*
import java.util.concurrent.ThreadPoolExecutor

val MODE = SYNC_BLOCKING_IO

fun main() {
    println("[---my-http-server---]")
    println("[--------------------]")
    println("[----initialized!----]")
    println("[io-mode: ${MODE}]\n\n")

    val server = IoServers.get(MODE)
    server.start()


}