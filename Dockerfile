FROM openjdk:8-jdk-alpine
ADD ./target/balance-dispense-api-0.1-SNAPSHOT.jar balance-dispense-api.jar
EXPOSE 8089
ENTRYPOINT ["java", "-jar","balance-dispense-api.jar"]
