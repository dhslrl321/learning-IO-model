package com.github.dhslrl321.webserver.blocking

import com.github.dhslrl321.webserver.server.IoServer
import java.net.ServerSocket
import java.util.concurrent.Executors


class BlockingIoServer: IoServer {
    private val threadPool = Executors.newFixedThreadPool(POOLING_THREAD_COUNT)
    private val handler = AcceptedSocketHandler()

    override fun start() {
        val serverSocket = ServerSocket(8080)

        while (true) {
            val acceptedSocket = serverSocket.accept()

            threadPool.execute {
                handler.handle(acceptedSocket)
            }
        }
    }

    companion object {
        const val POOLING_THREAD_COUNT = 2
    }
}