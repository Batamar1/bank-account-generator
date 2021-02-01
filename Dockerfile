FROM adoptopenjdk/openjdk11:jdk-11.0.5_10-alpine
ADD . /src
WORKDIR /src
RUN ./gradle build
EXPOSE 8080
ENTRYPOINT ["java","-jar","build/libs/bank-account-generator-1.0-SNAPSHOT.jar"]