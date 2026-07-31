plugins {
    id("com.android.application") version "8.7.3" apply false
    id("org.jetbrains.kotlin.android") version "2.0.21" apply false
    id("org.jetbrains.kotlin.plugin.compose") version "2.0.21" apply false
}

val externalBuildDir = providers.environmentVariable("PLEXMUSIC_BUILD_DIR")

allprojects {
    externalBuildDir.orNull?.let { buildRoot ->
        val projectPath = path.trim(':').ifBlank { "root" }.replace(':', '/')
        layout.buildDirectory.set(file("$buildRoot/$projectPath"))
    }
}
