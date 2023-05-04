FROM openjdk:20 as build

WORKDIR /workspace/app

COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .
COPY /src src

RUN ./mvnw clean
RUN ./mvnw install -D skipTests
RUN mkdir -p target/dependency && (cd target/dependency; jar -xf ../*.jar)

FROM openjdk:20
VOLUME /tmp
ARG DEPENDENCY=/workspace/app/target/dependency
COPY --from=build ${DEPENDENCY}/META-INF /app/META-INF
COPY --from=build ${DEPENDENCY}/BOOT-INF/classes /app
ENTRYPOINT ["java","-cp","app:app/lib/*","org.lehnert.todo.TodoApplication"]

LABEL org.opencontainers.image.source = https://github.com/chfle/spring_todo_app

# port
EXPOSE 9999