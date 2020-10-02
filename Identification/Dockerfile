FROM openjdk:14-alpine
VOLUME /tmp
EXPOSE 9090
#RUN addgroup -S spring && adduser -S spring -G spring
#USER spring:spring

RUN mkdir /code
WORKDIR /code
ADD . /code/
