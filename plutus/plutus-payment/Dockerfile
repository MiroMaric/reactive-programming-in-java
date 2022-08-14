FROM openjdk:18.0.1.1-jdk
COPY target/plutus-payment-1.0-SNAPSHOT.jar plutus-payment.jar
ENTRYPOINT ["java","-jar","-Dspring.profiles.active=default,docker","/plutus-payment.jar"]
