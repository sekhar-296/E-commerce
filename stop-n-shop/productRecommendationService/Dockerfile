FROM openjdk:11
ADD ./target/productRecommendationService-0.0.1-SNAPSHOT.jar /usr/src/productRecommendationService-0.0.1-SNAPSHOT.jar
WORKDIR usr/src
ENTRYPOINT ["java","-jar", "productRecommendationService-0.0.1-SNAPSHOT.jar"]
