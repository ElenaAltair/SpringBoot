FROM adoptopenjdk/openjdk11:alpine-jre

EXPOSE 8081

COPY build/libs/SpringBoot-0.0.1-SNAPSHOT.jar app.jar

CMD ["java", "-jar", "app.jar"]

#EXPOSE 8080
#ADD build/libs/SpringBoot-0.0.1-SNAPSHOT.jar app.jar
#ENTRYPOINT ["java","-jar","/myapp.jar"]