FROM openjdk:11
ADD ./target/chatapp-service-0.0.1-SNAPSHOT.jar /usr/src/chatapp-service-0.0.1-SNAPSHOT.jar
WORKDIR usr/src
ENTRYPOINT ["java","-jar", "chatapp-service-0.0.1-SNAPSHOT.jar"]
