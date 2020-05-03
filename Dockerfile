# Build Stage
FROM maven:3.6.3-jdk-14 as build

COPY pom.xml .

RUN mvn install -DskipTests

# copy pom first and install dep to use cache
COPY . .

RUN mvn test


# Production Ready Stage 
#FROM openjdk:14-jdk as release
#COPY --from=build /artifact /app