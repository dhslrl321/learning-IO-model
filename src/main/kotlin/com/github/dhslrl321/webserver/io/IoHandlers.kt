package com.github.dhslrl321.webserver.io

import com.github.dhslrl321.webserver.blocking.BlockingIoHandler

object IoHandlers {
    fun get(type: IoModels): IoHandler {
        return when (type) {

            IoModels.SYNC_BLOCKING_IO -> BlockingIoHandler()

            else -> throw UnsupportedOperationException()
        }
    }
}