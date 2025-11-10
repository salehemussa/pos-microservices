FROM openjdk:17-jdk-slim
WORKDIR /app
COPY target/product-service-1.0.0.jar product-service.jar
EXPOSE 8082
ENTRYPOINT ["java", "-jar", "product-service.jar"]
