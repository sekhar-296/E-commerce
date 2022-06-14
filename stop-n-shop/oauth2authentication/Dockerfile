FROM openjdk:11
ADD ./target/oauth2authentication-0.0.1-SNAPSHOT.jar /usr/src/oauth2authentication-0.0.1-SNAPSHOT.jar
WORKDIR usr/src
ENTRYPOINT ["java","-jar", "oauth2authentication-0.0.1-SNAPSHOT.jar"]