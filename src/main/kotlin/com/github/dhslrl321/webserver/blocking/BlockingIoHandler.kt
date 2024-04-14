package com.github.dhslrl321.webserver.blocking

import com.github.dhslrl321.webserver.io.IoHandler
import com.github.dhslrl321.webserver.network.AcceptedSocketHandler
import java.net.ServerSocket
import java.util.concurrent.Executors


class BlockingIoHandler: IoHandler {
    private val threadPool = Executors.newFixedThreadPool(POOLING_THREAD_COUNT)
    private val handler = AcceptedSocketHandler()

    override fun handle() {
        val serverSocket = ServerSocket(8080)

        while (true) {
            val acceptedSocket = serverSocket.accept()

            threadPool.execute {
                handler.handle(acceptedSocket)
            }
        }
    }

    companion object {
        const val POOLING_THREAD_COUNT = 10
    }
}