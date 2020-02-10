FROM java:8-jdk-alpine
COPY ./target/balance-dispense-api-1-SNAPSHOT.jar /usr/app/
WORKDIR /usr/app
RUN sh -c 'touch balance-dispense-api.jar'
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-Dspring.profiles.active=uat","-jar","balance-dispense-api.jar"]