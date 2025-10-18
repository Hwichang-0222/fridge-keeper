![Spring Boot](https://img.shields.io/badge/SpringBoot-3.5.6-brightgreen)
![Java](https://img.shields.io/badge/Java-17-blue)
![MySQL](https://img.shields.io/badge/MySQL-8.0-orange)
![Android](https://img.shields.io/badge/Android-Java-yellowgreen)



# 🧊 냉장고지킴이 (Fridge Keeper)

**스마트 냉장고 관리 어플리케이션**  
유통기한 관리, 장보기 추천, 재료 기반 메뉴 추천을 제공하는 통합 냉장고 관리 시스템  

---

## 🧭 프로젝트 개요

냉장고지킴이는 냉장고 내부 물품을 효율적으로 관리하고,  
유통기한을 자동으로 알림받을 수 있는 **스마트 냉장고 보조 앱**입니다.  

> “냉장고 안의 재료를 잊지 않고, 똑똑하게 활용하자!”

| 항목 | 내용 |
|------|------|
| **프로젝트명** | Fridge Keeper |
| **브랜치명** | main |
| **역할** | 백엔드 API 서버 |
| **개발 환경** | Spring Boot 3.5.6 / Maven / Java 17 / MyBatis / MySQL |
| **레포지토리** | [https://github.com/Hwichang-0222/fridge-keeper](https://github.com/Hwichang-0222/fridge-keeper)

---

## 🧩 주요 기능

| 기능 | 설명 |
|------|------|
| 🧾 **물품 등록** | 바코드 스캔 또는 직접 입력으로 품목 추가 |
| ⏰ **유통기한 알림** | 만료 1~2일 전 자동 알림 전송 |
| 🧺 **장보기 추천** | 자주 구매하는 품목 기반 자동 추천 |
| 🍳 **메뉴 제안** | 현재 냉장고 재료로 가능한 레시피 추천 |
| 👨‍👩‍👧 **공유 기능** | 가족 구성원과 냉장고 데이터 실시간 공유 |

---

## ⚙️ 기술 스택

| 구분 | 기술 |
|------|------|
| **Backend** | Spring Boot 3.5.6, Java 17 |
| **Database** | MySQL 8.0, MyBatis |
| **Frontend (예정)** | Android (Java 기반) |
| **Build Tool** | Maven |
| **Server** | Spring Boot 내장 Tomcat |
| **Tools** | Eclipse 2024-12, GitHub Desktop |
| **Version Control** | GitHub |

---

## 🗂️ 프로젝트 구조

```bash
fridge-keeper/
├── backend/                   # Spring Boot 백엔드
│   ├── src/
│   │   ├── main/java/com/fridgekeeper/...
│   │   ├── main/resources/
│   │   └── test/java/
│   ├── pom.xml
│   └── application.properties
├── README.md                  # 프로젝트 소개 문서
├── .gitignore
└── .git/
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

## 💾 ERD 설계 (예정)

| 테이블명 | 설명 |
|-----------|------|
| `item` | 냉장고 물품 테이블 (이름, 수량, 유통기한, 카테고리 등) |
| `user` | 사용자 계정 정보 (공유용) |
| `shopping_list` | 장보기 추천 품목 저장 |
| `notification` | 유통기한 알림 스케줄 |

---

## 🧠 개발 목표 (확장 버전)

냉장고지킴이는 단순한 물품 관리 앱이 아니라,  
**생활 패턴에 맞춰 냉장고를 자동 관리하는 스마트 시스템**을 목표로 합니다.

---

### 🎯 주요 목표

- 🧾 **유통기한 관리 자동화**  
  - 물품 등록 시 바코드 스캔 또는 직접 입력을 통해 자동 분류 및 알림 설정  
  - 만료 임박(1~2일 전) 시 푸시 알림 발송, 만료 시 “폐기 예정” 상태 자동 표시  

- 🧺 **장보기 추천 시스템**  
  - 자주 구매하는 품목의 데이터 패턴을 분석해 자동 장보기 리스트 생성  
  - 재고 부족 품목을 자동 감지 후 ‘추천 항목’으로 분류  

- 🍳 **메뉴 추천 기능**  
  - 냉장고에 있는 재료를 기반으로 조리 가능한 레시피 추천  
  - 선택된 메뉴의 재료는 자동 차감되어 재고와 연동  

- 👨‍👩‍👧 **가족 공유 기능**  
  - 여러 기기에서 하나의 냉장고 데이터를 공유  
  - 계정별 권한 설정(보기 전용 / 관리자) 가능  

- 🔔 **스마트 알림 시스템**  
  - 유통기한, 재고 부족, 문 열림 감지(IoT 연동 예정) 등 상황별 자동 알림  

- ⚙️ **IoT 및 하드웨어 연동 (확장 목표)**  
  - 라벨 프린터 연동 → 물품 등록 시 라벨 자동 출력  
  - 아두이노/라즈베리파이 기반 냉장고 문 열림 감지  
  - 향후 SmartThings 또는 ThinQ API 연동 검토  

- 💡 **사용성 향상**  
  - 등록–관리–소비 흐름이 자연스럽게 이어지도록 설계  
  - 불필요한 입력 최소화 (자동완성, OCR, 추천 등)

---

## 🧩 프로젝트 진행 로드맵

> 진행 상태: 🟩 완료 / 🟨 진행 중 / ⬜ 계획 중  

### 🧱 1단계 — 백엔드 구축 (2025.10 ~ 2025.11)
- [🟩] Spring Boot 3.5.6 프로젝트 초기 설정  
- [🟩] MySQL + MyBatis 연동  
- [🟩] REST API 기본 구조 (Controller / Service / Mapper / Domain)  
- [🟩] GitHub 연동 및 버전 관리  
- [🟩] README 및 문서화 완료  

### 📱 2단계 — 안드로이드 연동 (2025.11 ~ 2025.12)
- [🟩] Retrofit2 기반 API 연동  
- [🟩] 물품 등록 / 조회 / 삭제 UI 구성  
- [⬜] 유통기한 알림 (로컬 + Firebase Cloud Messaging)  
- [⬜] 데이터 시각화 (캘린더 뷰, 리스트 뷰)

### 🧠 3단계 — 기능 고도화 (2026.01 이후)
- [⬜] 장보기 추천 알고리즘  
- [⬜] 메뉴 추천 기능 (레시피 API 연동)  
- [⬜] 가족 계정 공유  
- [⬜] Spring Scheduler 기반 자동 알림  
- [⬜] IoT 센서 및 라벨 프린터 연동  

### ☁️ 4단계 — 배포 및 상용화
- [⬜] AWS / GCP 클라우드 서버 배포  
- [⬜] 프리미엄 기능 분리 및 유료화 검토  
- [⬜] 보안 및 데이터 암호화 강화  

---

## 📆 개발 일정 요약 (예정)

| 단계 | 기간 | 주요 목표 |
|------|------|------------|
| **1단계** | 2025.10 ~ 2025.11 | 백엔드(Spring Boot) 구축 |
| **2단계** | 2025.11 ~ 2025.12 | 안드로이드 클라이언트 연동 |
| **3단계** | 2026.01 이후 | AI 추천 및 IoT 기능 확장 |

---

## 📬 개발자 정보

| 항목 | 내용 |
|------|------|
| **이름** | 홍휘창 |
| **GitHub** | [Hwichang-0222](https://github.com/Hwichang-0222) |
| **프로젝트명** | 냉장고지킴이 (Fridge Keeper) |

---

> 💡 *이 프로젝트는 개인 학습 및 포트폴리오 목적으로 제작 중입니다.*

---

💬 **한 줄 요약:**  
> “냉장고지킴이는 냉장고 속 재료를 효율적으로 관리해  
> 버려지는 음식을 줄이고, 스마트한 소비를 돕는 개인 프로젝트입니다.”




🧾 License
  MIT License (예정)

💬 한 줄 요약:
  “냉장고지킴이는 냉장고 속 재료를 효율적으로 관리해
  버려지는 음식 없이 스마트한 소비를 돕는 개인 프로젝트입니다.”
