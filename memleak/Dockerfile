FROM openjdk:8-jdk-alpine
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-Dcom.sun.management.jmxremote=true" ,"-Dcom.sun.management.jmxremote.port=3333" ,"-Dcom.sun.management.jmxremote.authenticate=false" ,"-Dcom.sun.management.jmxremote.ssl=false" ,"-Djava.rmi.server.hostname=127.0.0.1" ,"-Dcom.sun.management.jmxremote.rmi.port=3333","-jar","/app.jar"]