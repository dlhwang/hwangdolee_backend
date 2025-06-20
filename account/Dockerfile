# Stage 1 - 빌드
FROM gradle:8.5-jdk17 AS builder
WORKDIR /app
# 전체 프로젝트 복사 (멀티모듈이기 때문에 루트 복사 필요)
COPY . .
RUN chmod +x ./gradlew
RUN ./gradlew build -x test --stacktrace

RUN ls -l /app
RUN ls -l /app/account/build/libs

# Stage 2 - 런타임
FROM openjdk:23-jdk
WORKDIR /app
COPY --from=builder /app/account/build/libs/account.jar account.jar
RUN ls -l /app
ENTRYPOINT ["java", "-jar", "account.jar"]