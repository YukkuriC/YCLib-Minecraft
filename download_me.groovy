if (!project.hasProperty('yclib_version')) throw new GradleException("must add `yclib_version` (e.g. `1.21.1-0.2`) to gradle.properties")

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

allprojects {
    repositories {
        mavenCentral()
        flatDir { dir "$rootDir/yclib" }
    }

    afterEvaluate {
        def depKey = "yclib:yclib:$yclib_version"
        dependencies {
            def cfg = project.configurations.findByName('modImplementation')
                    ?: project.configurations.findByName('implementation')
            if (cfg) {
                project.dependencies.add(cfg.name, depKey)
            } else {
                println "Skipping dep creation on $name"
            }
            if (name != 'common') {
                def nestCfg = project.configurations.findByName('jarJar')
                        ?: project.configurations.findByName('include')
                if (nestCfg) {
                    project.dependencies.add(nestCfg.name, depKey)
                } else {
                    println "Skipping dep nesting on $name"
                }
            }
        }
    }
}