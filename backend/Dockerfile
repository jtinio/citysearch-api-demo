### Phase 1 BUILD
FROM maven:3-jdk-11 as builder
# create app folder for sources
RUN mkdir -p /build
WORKDIR /build
COPY pom.xml /build
#Download all required dependencies into one layer
RUN mvn -B dependency:resolve dependency:resolve-plugins
#Copy source code
COPY src /build/src
# Build application using docker profile
RUN mvn package -Pdocker
RUN mv /build/target/backend.jar .
RUN rm -rf src target

### Phase 2 Runtime
FROM amazoncorretto:11-alpine-jdk
# cd /opt/app
WORKDIR /opt/app
#Copy executable jar file from the builder image
COPY --from=builder /build/backend.jar app.jar
# execute application entrypoint -> java -jar /opt/app/app.jar
ENTRYPOINT ["java","-jar","app.jar"]