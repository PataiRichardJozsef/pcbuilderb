FROM openjdk:17-jdk-alpine3.14
COPY "./target/pcbuild2.jar" "/app/pcbuilder2.jar"
EXPOSE 8080
CMD [ "java", "-jar", "/app/pcbuilder2.jar" ]