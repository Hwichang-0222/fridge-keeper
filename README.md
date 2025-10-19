# 🥊 Fridge Keeper Backend (Spring Boot)

Spring Boot과 MyBatis 기반의 냉장고 식재료 관리 서버 프로젝트입니다.  
REST API를 통해 안드로이드 앱(`master` 브랜치)과 연동되어 작동합니다.

---

## 📁 프로젝트 개요

| 항목 | 내용 |
|------|------|
| **프로젝트명** | Fridge Keeper |
| **브랜치명** | main |
| **역할** | 백엔드 API 서버 |
| **개발 환경** | Spring Boot 3.5.6 / Maven / Java 17 / MyBatis / MySQL |
| **레포지토리** | [https://github.com/Hwichang-0222/fridge-keeper](https://github.com/Hwichang-0222/fridge-keeper) |

---

## ⚙️ 기술 스택

- **Backend:** Spring Boot, MyBatis, JPA, Lombok  
- **Database:** MySQL  
- **API 문서:** Swagger(OpenAPI 3)  
- **빌드 도구:** Maven  
- **서버:** Spring Boot 내장 Tomcat  
- **IDE:** Eclipse 2024-12  

---

## 🥉 주요 기능

| 기능 | 설명 |
|------|------|
| **식재료 등록** | POST `/items` - 식재료 정보 등록 |
| **식재료 조회** | GET `/items` - 전체 목록 조회 |
| **상세 조회** | GET `/items/{id}` - 단일 항목 조회 |
| **수정 및 삭제** | PUT/DELETE `/items/{id}` |
| **uc608외 처리** | `GlobalExceptionHandler`에서 통합 처리 |
| **API 문서** | Swagger UI를 통해 자동 문서화 (`/swagger-ui`) |

---

## 🗂️ 디렉토리 구조

```
backend/
 ┗📁 controller
 ┃ ┗ ItemController.java
 ┗📁 service
 ┃ ┛ ItemService.java
 ┃ ┗ ItemServiceImpl.java
 ┗📁 domain
 ┃ ┗ Item.java
 ┗📁 mapper
 ┃ ┗ ItemMapper.java
 ┃ ┗ ItemMapper.xml
 ┗📁 config
 ┃ ┗ OpenApiConfig.java
 ┗📁 exception
 ┃ ┗ GlobalExceptionHandler.java
 ┗📁 resources
 ┃ ┗ application.properties
 ┗ pom.xml
```

---

## 💮 주요 설정

### `pom.xml`
Spring Boot + MyBatis + Swagger 의존성 포함  
Java 17 기준으로 컴파일 설정 완료

### `application.properties`
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/fridge_db?serverTimezone=Asia/Seoul&characterEncoding=UTF-8
spring.datasource.username=root
spring.datasource.password=1111
mybatis.mapper-locations=classpath:/mapper/**/*.xml
springdoc.swagger-ui.path=/swagger-ui
```

---

## 🚀 실행 방법

```bash
# 1. 의존성 설치
mvn clean install

# 2. 서버 실행
mvn spring-boot:run
```

실행 후 접속:
- API 문서: [http://localhost:8080/swagger-ui](http://localhost:8080/swagger-ui)
- API JSON: [http://localhost:8080/api-docs](http://localhost:8080/api-docs)

---

## 🧠 예외 처리
`GlobalExceptionHandler`를 통해 REST 전역 오류를 통합 관리합니다.  
일관된 JSON 포맷으로 응답하며, HTTP 상태 코드와 메시지를 함께 제공합니다.

---

## 🔗 안드로이드 연동
안드로이드(`master` 브랜치)에서 `Retrofit2`를 이용해 본 서버의 API를 호출합니다.  
백엔드는 `CORS` 허용 및 `JSON` 응답 구조로 구성되어 있습니다.

---

## 👨‍💻 개발자 정보
**홍휘창 (Hwichang-0222)**  
📍 [GitHub](https://github.com/Hwichang-0222)

