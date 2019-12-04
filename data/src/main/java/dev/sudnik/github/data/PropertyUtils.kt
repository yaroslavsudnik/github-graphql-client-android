package dev.sudnik.github.data

import java.util.*

class PropertyUtils {
    private val prop: Properties by lazy {
        Properties().apply {
            load(this@PropertyUtils::class.java.classLoader.getResourceAsStream("keys.properties"))
        }
    }

    fun getClientId(): String = prop.getProperty(CLIENT_ID_KEY)

    fun getClientSecret(): String = prop.getProperty(CLIENT_SECRET_KEY)

    companion object {
        private const val CLIENT_ID_KEY = "clientId"
        private const val CLIENT_SECRET_KEY = "clientSecret"
    }
}
