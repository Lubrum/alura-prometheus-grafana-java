FROM eclipse-temurin:17.0.6_10-jdk-alpine@sha256:ed5b03e6bac93bfc3c2e23c592f2d8f6556f5f6120f439f62aec3134f9a7f917 AS builder

WORKDIR /build

COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .
RUN ./mvnw dependency:go-offline

COPY src src
RUN ./mvnw clean install -DskipTests
RUN cp /build/target/*.jar app.jar
RUN java -Djarmode=layertools -jar app.jar extract

FROM eclipse-temurin:17.0.6_10-jre-alpine@sha256:2b33ef284e6dc43a61903cef6d36dbce13414a9e5444e2c96cdd5e35123f9903

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
