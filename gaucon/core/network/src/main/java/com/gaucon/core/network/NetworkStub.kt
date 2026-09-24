package com.gaucon.core.network

/**
 * Stub — no cloud SDK bound (CONSENSUS §5.6 / BUILD_READY §2.1).
 */
interface ContentApi {
    suspend fun latestVersion(): Int
}

interface CloudBackupGateway {
    suspend fun isEnabled(): Boolean
}

class NoOpContentApi : ContentApi {
    override suspend fun latestVersion(): Int = 0
}

class NoOpCloudBackupGateway : CloudBackupGateway {
    override suspend fun isEnabled(): Boolean = false
}
