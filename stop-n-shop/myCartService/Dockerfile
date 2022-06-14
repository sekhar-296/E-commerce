FROM openjdk:11
ADD ./target/myCartService-0.0.1-SNAPSHOT.jar /usr/src/myCartService-0.0.1-SNAPSHOT.jar
WORKDIR usr/src
ENTRYPOINT ["java","-jar", "myCartService-0.0.1-SNAPSHOT.jar"]