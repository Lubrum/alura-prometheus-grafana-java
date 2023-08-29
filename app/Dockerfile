FROM eclipse-temurin:20.0.2_9-jdk-alpine@sha256:27f64fb39e4e052bba5fdd4659875b8ab421eb3c5d85ab9d4a8025808fc39cfa AS builder

WORKDIR /build

COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .
RUN ./mvnw dependency:go-offline

COPY src src
RUN ./mvnw clean install -DskipTests
RUN cp /build/target/*.jar app.jar
RUN java -Djarmode=layertools -jar app.jar extract

FROM eclipse-temurin:20.0.2_9-jre-alpine@sha256:987af6a7e762cf950598c8f5ebaad9fff9dcf220fc8a3bddb9fd55b49b48a3e4

WORKDIR /app

RUN echo http://dl-2.alpinelinux.org/alpine/edge/community/ >> /etc/apk/repositories && \
    apk --no-cache add shadow && \
    addgroup -S spring &&  \
    adduser -S spring -G spring && \
    usermod -a -G spring root && \
    mkdir logs

COPY --from=builder /build/dependencies/ ./
COPY --from=builder /build/spring-boot-loader/ ./
COPY --from=builder /build/snapshot-dependencies/ ./
COPY --from=builder /build/application/ ./

RUN chown -R spring:spring /app

USER spring:spring
ENTRYPOINT ["java","-Xms128M","-Xmx128M","-Dspring.profiles.active=prod", "org.springframework.boot.loader.JarLauncher"]
