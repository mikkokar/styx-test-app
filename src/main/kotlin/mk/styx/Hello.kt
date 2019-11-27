package mk.styx

import com.hotels.styx.StyxConfig
import com.hotels.styx.StyxServer
import com.hotels.styx.startup.StyxServerComponents

fun main(args: Array<String>) {
    println("Hello, World")

    val config = """                    
        proxy:
          connectors:
            http:
              port: 0
    
            https:
              port: 0
              sslProvider: JDK
              sessionTimeoutMillis: 300000
              sessionCacheSize: 20000
    
        admin:
          connectors:
            http:
              port: 0""".trimIndent()

    val components = StyxServerComponents.Builder()
            .styxConfig(StyxConfig.fromYaml(config, true))
            .build()

    val server = StyxServer(components)
    server.startAsync()?.awaitRunning()

    server.stopAsync().awaitTerminated()
}
