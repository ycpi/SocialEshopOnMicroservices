FROM openjdk:8-jdk-alpine
VOLUME /tmp
EXPOSE 8080
RUN addgroup -S spring && adduser -S spring -G spring
USER spring:spring
ADD build/libs/identification-0.0.1-SNAPSHOT.jar springbootpostgresqldocker.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/springbootpostgresqldocker.jar"]