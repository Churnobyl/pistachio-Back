FROM eclipse-temurin:17-jdk-alpine
VOLUME /tmp
COPY app.jar app.jar

# Add MySQL initialization scripts
COPY schema.sql /docker-entrypoint-initdb.d/schema.sql
COPY data.sql /docker-entrypoint-initdb.d/data.sql

ENTRYPOINT ["java","-jar","/app.jar"]