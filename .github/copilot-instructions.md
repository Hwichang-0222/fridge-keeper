# 🥊 Fridge Keeper Backend — Copilot Agent Instructions

## 🏗️ 아키텍처 및 데이터 흐름

-   **Spring Boot + MyBatis 기반 REST API**
-   앱/웹 동시 개발, 1인 개발 구조
-   주요 디렉토리: `controller`, `service`, `dto`, `mapper`, `config`, `exception`
-   데이터 흐름: Controller → Service(인터페이스+구현체) → Mapper(MyBatis, XML) → DB(MySQL)
-   DTO(`dto/`)는 API 입출력, 도메인(`domain/`)은 DB 매핑용
-   예외는 `GlobalExceptionHandler`에서 JSON 통합 처리

## 🗄️ DB 설계 및 관계

**핵심 테이블**: user, fridge, fridge_user_role, category, item, user_category(추천)
**관계**: - User 1:N Fridge_User_Role - Fridge 1:N Fridge_User_Role - Fridge_User_Role: fridge_id, user_id, role(OWNER/MEMBER) - Fridge 1:N Item - Category 1:N Item - User N:M Category (user_category 연결테이블 활용, 대규모 사용자 환경에 최적)
**DDL.sql** 참고: PK/FK, M:N 관계, 권한/공유/커스텀 구조 명확

### 📊 대규모 사용자 환경을 위한 카테고리 관리

-   **카테고리 테이블(category)**: name, color 등 카테고리 정보는 한 번만 저장
-   **user_category 연결테이블**: user_id, category_id로 사용자별 사용할 카테고리만 연결 관리
-   아이템 추가 시 user_category에 연결된 카테고리만 노출 → UX 간결, 성능 최적화
-   카테고리 복사/공유/추천 등 확장 기능에 유리
-   데이터 중복 최소화, 전체 통계/검색/관리 용이

#### 예시 쿼리

```sql
-- 사용자별 카테고리 조회
SELECT c.* FROM category c
JOIN user_category uc ON c.category_id = uc.category_id
WHERE uc.user_id = ?;
```

-- 카테고리 추가: 직접 생성 또는 다른 사용자 카테고리 연결(user_category row 추가)

-- 기본 카테고리는 모든 사용자에게 자동 연결(user_category row 생성)

```

## 🔐 인증 및 SNS 로그인

-   **Spring Security + OAuth2 Client** 의존성 포함
-   SNS(OAuth) 로그인(구글, 네이버 등) 확장 가능
-   User 테이블(email, password, nickname 등) 기반 인증

## 🛠️ 개발 워크플로우

-   **빌드/실행:**
    -   `./mvnw clean install` (의존성 설치)
    -   `./mvnw spring-boot:run` (서버 실행)
-   **테스트:**
    -   `./mvnw test`
-   **API 문서:**
    -   Swagger UI: [http://localhost:8080/swagger-ui](http://localhost:8080/swagger-ui)

## 📦 주요 패턴 및 규칙

-   **Service 계층:** 인터페이스+구현체 분리, 비즈니스 로직 담당
-   **Mapper 계층:** MyBatis XML에서 SQL 직접 관리, 예시: `ItemMapper.xml`
-   **DTO/도메인 분리:** API 입출력/DB 매핑 역할 구분
-   **예외 처리:** `GlobalExceptionHandler`에서 일괄 처리, 일관된 JSON 반환
-   **Swagger/OpenAPI:** `OpenApiConfig.java`에서 문서 메타데이터 및 경로 지정
-   **CORS:** `WebConfig.java`에서 모든 Origin 허용, Retrofit2 연동 대응

## 🔗 통합 및 외부 연동

-   **DB:** MySQL, 커넥션 정보는 `application.properties`에 명시
-   **Android/웹 연동:** CORS 허용, JSON 응답, Retrofit2 기반 호출

## 🧩 코드 작성 시 참고

-   컨트롤러: `@RestController`, `@RequestMapping` 사용
-   서비스: `@Service`, 인터페이스 분리
-   매퍼: `@Mapper`, XML 쿼리 분리
-   예외: `@ControllerAdvice`에서 처리
-   DTO/도메인/매퍼/서비스/컨트롤러 간 역할 분리
-   사용자 구분, 냉장고 공유, 권한 관리 등 멀티 플랫폼 대응

## 🗂️ 참고 파일

-   `README.md`: 전체 구조 및 실행법
-   `config/OpenApiConfig.java`: Swagger 설정
-   `config/WebConfig.java`: CORS 설정
-   `exception/GlobalExceptionHandler.java`: 예외 처리 패턴
-   `mapper/ItemMapper.xml`: DB 연동 예시
-   `DDL.sql`: 실제 테이블 구조 및 관계

---

> 이 문서는 Fridge Keeper 백엔드 코드베이스에서 AI 에이전트가 즉시 생산적으로 작업할 수 있도록 핵심 구조, 워크플로우, DB/권한/로그인/관계 규칙을 요약합니다. 추가 정보가 필요하면 `README.md`, `DDL.sql`, 각 디렉토리의 대표 파일을 참고하세요.
```
