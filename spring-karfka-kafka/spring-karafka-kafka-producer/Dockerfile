FROM adoptopenjdk/openjdk11:jre-11.0.6_10-alpine
ADD target/*.jar spring-karafka-kafka.jar
ENTRYPOINT java -jar spring-karafka-kafka.jar