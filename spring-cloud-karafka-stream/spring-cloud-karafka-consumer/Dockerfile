FROM adoptopenjdk/openjdk11:jre-11.0.6_10-alpine
ADD target/*.jar spring-cloud-karafka-stream.jar
ENTRYPOINT java -jar spring-cloud-karafka-stream.jar