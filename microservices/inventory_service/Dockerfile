FROM openjdk:14-alpine
VOLUME /tmp
EXPOSE 9090

ADD ./build/libs/*.jar app.jar
ENTRYPOINT ["java","-jar", "app.jar"]