package com.github.dhslrl321.webserver.multiplexing

import com.github.dhslrl321.webserver.server.IoServer
import java.io.IOException
import java.net.InetSocketAddress
import java.nio.ByteBuffer
import java.nio.channels.SelectionKey
import java.nio.channels.Selector
import java.nio.channels.ServerSocketChannel
import java.nio.channels.SocketChannel
import java.nio.charset.StandardCharsets
import java.util.*

class IoMultiplexingServer: IoServer {
    private val port = 8080

    override fun start() {
        try {
            val selector: Selector = Selector.open()
            val serverSocketChannel: ServerSocketChannel = ServerSocketChannel.open()
            serverSocketChannel.bind(InetSocketAddress(port))
            serverSocketChannel.configureBlocking(false)
            serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT)

            println("Server started on port $port")

            while (true) {
                selector.select()  // Wait for an event
                val selectedKeys = selector.selectedKeys()
                val iter = selectedKeys.iterator()

                while (iter.hasNext()) {
                    val key = iter.next()

                    if (key.isAcceptable) {
                        handleAccept(serverSocketChannel, selector)
                    }

                    if (key.isReadable) {
                        handleRead(key)
                    }
                    iter.remove()
                }
            }
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    private fun handleAccept(serverSocketChannel: ServerSocketChannel, selector: Selector) {
        val clientChannel = serverSocketChannel.accept()
        clientChannel?.let {
            it.configureBlocking(false)
            it.register(selector, SelectionKey.OP_READ)
            println("Connection accepted: ${it.remoteAddress}")
        }
    }

    private fun handleRead(key: SelectionKey) {
        val clientChannel = key.channel() as SocketChannel
        val buffer = ByteBuffer.allocate(1024)
        val read = clientChannel.read(buffer)
        if (read == -1) {
            clientChannel.close()
            println("Connection closed by client")
            return
        }

        val request = String(buffer.array(), 0, buffer.position(), StandardCharsets.UTF_8).trim()
        println("Received request: $request")
        val body = "Hello, World! ${UUID.randomUUID()}"
        val response = """
                    HTTP/1.1 200 OK
                    Content-Type: text/plain
                    Content-Length: ${body.length}  
                    
                    $body
                    """.trimIndent()
        val buffer1 = ByteBuffer.wrap(response.toByteArray(StandardCharsets.UTF_8))
        while (buffer1.hasRemaining()) {
            clientChannel.write(buffer1)
        }
        clientChannel.close()
    }

}