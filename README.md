# 💰 Dollee Bank - Modular Spring Boot Application

Dollee Bank는 멀티모듈 구조로 설계된 금융 백엔드 시스템입니다. 계좌 관리, 입금 처리, 수수료 정책, 한도 정책 등을 책임별로 분리하여 모듈화하였으며, Docker
Compose를 통해 전체 시스템을 손쉽게 실행할 수 있습니다.

---

## 🏗️ 프로젝트 구조

````
dollee-bank/
├── account-core/       # 도메인 (계좌 생성/조회/입금)
├── deposit/            # 입금 API (입금 처리 및 수수료/한도 정책 적용)
├── account/            # 계좌 생성/조회/삭제 API
├── withdraw/           # 인출 API (인출 처리 및 수수료/한도 정책 적용)
├── transfer/           # 이체 API (이체 처리 및 수수료/한도 정책 적용)
├── common/             # 공통 라이브러리 (VO, 유틸, Exception 등)
├── build.gradle        # 루트 빌드 스크립트
├── docker-compose-dev.yml  # 애플리케이션 실행
└── docker-compose.yml      # 환경 시스템 실행(Mysql, Redis)
````

---

## 🛠️ 실행 환경

- Java 23
- Spring Boot 3.3
- MySQL 8.0
- Redis 7.x

---

## Gradle

Docker / Docker Compose

🚀 실행 방법

1. JAR 파일 빌드

```sh
./gradlew clean bootJar
```

멀티모듈이므로 각 모듈의 jar 파일이 각각 생성됩니다.

2. Docker Compose 실행

````shell
docker compose -f docker-compose.yml -f docker-compose.dev.yml up — build
````

전체 서비스 + MySQL + Redis를 컨테이너로 실행합니다.

---

## 🔗 Swagger API

| 모듈       | URL                                         |
|----------|---------------------------------------------|
| Account  | http://localhost:8080/swagger-ui/index.html |
| Deposit  | http://localhost:8082/swagger-ui/index.html |
| Transfer | http://localhost:8084/swagger-ui/index.html |
| Withdraw | http://localhost:8086/swagger-ui/index.html |
