FROM openjdk:18.0.1.1-jdk
COPY target/plutus-user-1.0-SNAPSHOT.jar plutus-user.jar
ENTRYPOINT ["java","-jar","-Dspring.profiles.active=default,docker","/plutus-user.jar"]
