package com.github.dhslrl321.webserver.nonblocking

import com.github.dhslrl321.webserver.server.IoServer
import java.net.InetSocketAddress
import java.nio.ByteBuffer
import java.nio.channels.ServerSocketChannel
import java.nio.channels.SocketChannel
import java.nio.charset.StandardCharsets
import java.util.*

class NonBlockingPollingServer: IoServer {
    private val port = 8080

    override fun start() {
        val serverSocketChannel = ServerSocketChannel.open()
        serverSocketChannel.bind(InetSocketAddress(port))
        serverSocketChannel.configureBlocking(false)
        println("Server started on port $port")

        while (true) {
            val socketChannel = serverSocketChannel.accept()
            if (socketChannel != null) {
                println("Connection accepted: ${socketChannel.remoteAddress}")
                handleClient(socketChannel)
            }
            // Simulate doing other work
        }
    }

    private fun handleClient(socketChannel: SocketChannel) {
        socketChannel.configureBlocking(false)
        val buffer = ByteBuffer.allocate(1024)

        while (socketChannel.read(buffer) == 0) {
            // Polling: Check if data is available
            println("Waiting for data...")
        }

        // Process the request
        buffer.flip()
        val request = StandardCharsets.UTF_8.decode(buffer).toString()
        println("Received request: $request")

        // Send response
        val body = "Hello, World! ${UUID.randomUUID()}"
        val response = """
                HTTP/1.1 200 OK
                Content-Type: text/plain
                Content-Length: ${body.length}  
                
                $body
                """.trimIndent()
        buffer.clear()
        buffer.put(response.toByteArray(StandardCharsets.UTF_8))
        buffer.flip()
        while (buffer.hasRemaining()) {
            socketChannel.write(buffer)
        }
        socketChannel.close()
    }
}