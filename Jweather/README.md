生成jar包
========
在build.gradle中引入shadowJar包
id "com.github.johnrengelman.shadow" version "5.2.0"

执行
----
gradle shadowJar

java -jar ./build/libs/Jweather-all.jar