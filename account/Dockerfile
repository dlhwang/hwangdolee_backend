#FROM openjdk:23-jdk
#WORKDIR /app
#COPY build/libs/account-0.0.1-SNAPSHOT.jar /app/app.jar
#ENTRYPOINT ["java", "-jar", "app.jar"]

# === 1단계: Gradle 빌드용 이미지 ===
FROM gradle:8.6.0-jdk17 AS builder
RUN mkdir /app
WORKDIR /app

# 프로젝트 전체 복사
COPY . .

# 현재 디렉토리 내용 출력
RUN echo "=== 파일 목록 ===" && ls -al

# 필요한 모듈만 빌드
RUN ./gradlew :account:bootJar

# === 2단계: 런타임 이미지 ===
FROM openjdk:23-jdk
WORKDIR /app

# 빌드된 jar 복사
COPY --from=builder /app/account/build/libs/*.jar account.jar

# 실행
ENTRYPOINT ["java", "-jar", " = 'account'.jar"]