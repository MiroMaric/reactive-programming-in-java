FROM openjdk:18.0.1.1-jdk
COPY target/payment-provider-1.0-r-SNAPSHOT.jar payment-provider.jar
ENTRYPOINT ["java","-jar","-Dspring.profiles.active=default,docker","/payment-provider.jar"]
