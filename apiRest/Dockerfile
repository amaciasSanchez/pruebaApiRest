FROM openjdk:11
RUN mkdir -p /usr/src/apirest
COPY /target/nttdata-ws-prueba-1.0.0.jar /usr/src/apirest
WORKDIR /usr/src/apirest
EXPOSE 8075
CMD ["java", "-Djava.security.egd=file:/dev/./urandom", "-Duser.timezone=EC", "-jar", "nttdata-ws-prueba-1.0.0.jar"]