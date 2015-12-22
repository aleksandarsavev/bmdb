set MAVEN_OPTS=-Xdebug -Xnoagent -Djava.compiler=NONE -Xrunjdwp:transport=dt_socket,address=8000,server=y,suspend=n

call mvn jetty:run -Dorg.eclipse.jetty.annotations.maxWait=120
