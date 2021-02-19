FROM alpine/git
WORKDIR /maven
RUN git clone https://github.com/superuserz/ucp-service-common.git

FROM alpine/git
WORKDIR /app
RUN git clone https://github.com/superuserz/ucp-service-user.git


FROM maven:3.5-jdk-8-alpine
WORKDIR /app
COPY --from=0 /app/ucp-service-user /app 
RUN mvn install

FROM openjdk:8-jdk-alpine
WORKDIR /app
COPY --from=1 /app/target/*.jar app.jar
CMD ["java -jar app.jar"] 