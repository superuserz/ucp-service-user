FROM alpine/git
WORKDIR /maven
RUN git clone https://github.com/superuserz/ucp-service-common.git

FROM maven:3.5-jdk-8-alpine
RUN mvn clean install

FROM alpine/git
WORKDIR /app
RUN git clone https://github.com/superuserz/ucp-service-user.git


FROM maven:3.5-jdk-8-alpine
WORKDIR /app
RUN mvn install

FROM openjdk:8-jdk-alpine
WORKDIR /app
COPY /app/target/*.jar app.jar
CMD ["java -jar app.jar"] 