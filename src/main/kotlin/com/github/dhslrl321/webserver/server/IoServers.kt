package com.github.dhslrl321.webserver.server

import com.github.dhslrl321.webserver.blocking.BlockingIoServer
import com.github.dhslrl321.webserver.multiplexing.IoMultiplexingServer
import com.github.dhslrl321.webserver.nonblocking.NonBlockingPollingServer
import com.github.dhslrl321.webserver.server.IoModel.*

object IoServers {
    fun get(type: IoModel): IoServer {
        return when (type) {

            SYNC_BLOCKING_IO -> BlockingIoServer()

            SYNC_NON_BLOCKING_IO -> NonBlockingPollingServer()

            ASYNC_BLOCKING_IO -> IoMultiplexingServer()

            else -> throw UnsupportedOperationException()
        }
    }
}