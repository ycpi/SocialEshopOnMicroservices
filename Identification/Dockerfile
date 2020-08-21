FROM openjdk:14-alpine
VOLUME /tmp
EXPOSE 8080
RUN addgroup -S spring && adduser -S spring -G spring
#USER spring:spring

RUN mkdir /code
WORKDIR /code
ADD . /code/

# this will fetch gradle 5.4, and the packages we depend on
RUN ./gradlew build


#ADD build/libs/identification-0.0.1-SNAPSHOT.jar springbootpostgresqldocker.jar
#ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/springbootpostgresqldocker.jar"]