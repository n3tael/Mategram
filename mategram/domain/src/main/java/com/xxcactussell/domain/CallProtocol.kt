package com.xxcactussell.domain

data class CallProtocol(
    val udpP2p: Boolean,
    val udpReflector: Boolean,
    val minLayer: Int,
    val maxLayer: Int,
    val libraryVersions: List<String>
) : Object
