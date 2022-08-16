FROM gradle:5.6.0-jdk8 as build
WORKDIR /smthpics-backend
COPY build.gradle build.gradle
COPY settings.gradle settings.gradle
COPY src src
COPY conf conf
RUN gradle shadowJar

FROM openjdk:8-jdk-slim
WORKDIR /smthpics-backend
COPY --from=build /smthpics-backend/build\libs\smthpics-backend-1.0.0-all.jar app.jar
COPY conf conf
EXPOSE 8080
CMD ["java", "-jar", "app.jar"]
