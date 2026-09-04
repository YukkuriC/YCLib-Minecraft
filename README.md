# Setup
## Download me (into `$ROOT/yclib`)
```groovy
// add at head
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
```

## Refer to me: `YCLib.xxx`
- For Architectury projects:

```groovy
// insert into
subprojects {
    repositories {
        flatDir { dir "../yclib" }
    }
    dependencies {
        modImplementation "yclib:yclib:$yclib_version"
        if (project.name != 'common') include "yclib:yclib:$yclib_version"
    }
}
```

- For single-platform projects:

```groovy
// insert into
repositories {
    flatDir { dir "yclib" }
}
dependencies {
    // fabric
    modImplementation "yclib:yclib:$yclib_version"
    include "yclib:yclib:$yclib_version"
    // forge
    implementation "yclib:yclib:$yclib_version"
    jarJar "yclib:yclib:$yclib_version"
}
```