FROM openjdk:11
RUN mkdir -p /usr/src/apiRest
COPY /target/pruebaNTTdata-1.0.0.jar /usr/src/apiRest
WORKDIR /usr/src/apiRest
EXPOSE 8003
CMD ["java", "-Djava.security.egd=file:/dev/./urandom", "-Duser.timezone=EC", "-jar", "pruebaNTTdata-1.0.0.jar"]