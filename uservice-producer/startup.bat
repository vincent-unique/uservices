@echo off

rem settings for JAVA_OPTS
set JAVA_OPTS=%JAVA_OPTS% -Xmx:1024m -Xms:100m -XX:+UserG1GC

rem start web service with embeded jetty

..\jre\bin\java -jar ./target/producer.jar --server.port=1024
