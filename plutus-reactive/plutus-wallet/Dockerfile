FROM openjdk:18.0.1.1-jdk
COPY target/plutus-wallet-1.0-r-SNAPSHOT.jar plutus-wallet.jar
ENTRYPOINT ["java","-jar","-Dspring.profiles.active=default,docker","/plutus-wallet.jar"]
