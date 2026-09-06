# Setup
Download me (into `$ROOT/yclib`) and load me as dep
```groovy
apply from: 'https://raw.githubusercontent.com/YukkuriC/YCLib-Minecraft/refs/heads/main/download_me.groovy'
```

or with cache
```groovy
apply from: file('build/yclib_boot.groovy').tap { if (!exists()) { parentFile.mkdirs(); bytes = new URL('https://raw.githubusercontent.com/YukkuriC/YCLib-Minecraft/refs/heads/main/download_me.groovy').bytes } }
```