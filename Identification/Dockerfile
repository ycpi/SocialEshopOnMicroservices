FROM openjdk:14-alpine
VOLUME /tmp
EXPOSE 9090
#RUN addgroup -S spring && adduser -S spring -G spring
#USER spring:spring

RUN mkdir /code
WORKDIR /code
ADD . /code/
ENTRYPOINT ["java","-jar","./build/libs/identification-0.1.0.jar"]

#FROM openjdk:14-alpine
#RUN addgroup -S spring && adduser -S spring -G spring
#USER spring:spring
#ARG JAR_FILE=./build/libs/*.jar
#COPY ${JAR_FILE} app.jar
