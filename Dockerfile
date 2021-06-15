FROM openjdk:15-jdk-alpine

COPY build/libs/kyma_demo-*.jar /var/app/kyma_demo.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "/var/app/kyma_demo.jar"]
