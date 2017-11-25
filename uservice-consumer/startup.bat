@echo off

rem settings for JAVA_OPTS
set JAVA_OPTS=%JAVA_OPTS% -Xmx:1024m -Xms:100m -XX:+UseG1GC

rem start web service with embeded jetty and java8
..\jre\bin\java -jar ./target/consumer.jar --server.port=12315
