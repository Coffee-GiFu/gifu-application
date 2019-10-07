FROM openjdk:11.0.4-stretch
COPY target/gifu-0.0.1-SNAPSHOT.jar /
EXPOSE 8888
ENTRYPOINT ["java","-jar","/app.jar"]
#CMD ["-jar", "gifu-0.0.1-SNAPSHOT.jar"]
ADD target/gifu-*-SNAPSHOT.jar app.jar
