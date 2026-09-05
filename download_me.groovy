def jarFile = file("$projectDir/yclib/yclib-${yclib_version}.jar")
if (!jarFile.exists()) {
    def jarUrl = "https://github.com/YukkuriC/YCLib-Minecraft/releases/download/$yclib_version/yclib-${yclib_version}.jar"
    println "Downloading ${jarUrl} ..."
    jarFile.parentFile.mkdirs()
    new URL(jarUrl).withInputStream { inputStream ->
        jarFile.withOutputStream { outputStream ->
            outputStream << inputStream
        }
    }
    println "Download completed: $jarFile"
}