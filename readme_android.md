# 📱 Fridge Keeper Android (Client App)

Spring Boot 백엔드(`main` 브랜치)와 연동되는 안드로이드 앱 프로젝트입니다.  
냉장고 속 식재료를 관리하고, 유통기한을 확인하며 CRUD 기능을 제공합니다.

---

## 📁 프로젝트 개요

| 항목 | 내용 |
|------|------|
| **프로젝트명** | Fridge Keeper |
| **브랜치명** | master |
| **역할** | 안드로이드 클라이언트 |
| **개발 언어** | Java |
| **IDE** | Android Studio |
| **서버 연동** | Retrofit2 (REST API) |
| **uc774미지 처리** | Glide |
| **데이터 표시** | RecyclerView |

---

## ⚙️ 주요 기능

| 기능 | 설명 |
|------|------|
| **식재료 목록 조회** | 서버의 `/items` API 호출로 전체 목록 표시 |
| **식재료 등록** | Retrofit으로 POST 요청 전송 |
| **식재료 수정/삭제** | PUT, DELETE 요청 처리 |
| **uc774미지 표시** | Glide를 이용한 쌍다운 표시 |
| **유통기한 관리** | 백엔드 `expirationDate` 기반 표시 및 정렬 |
| **UI 구성** | RecyclerView + CardView 목록 형태 |

---

## 🧱️ 아키템처 구조

```
android/
 ┗📁 api
 ┃ ┗ ApiService.java
 ┗📁 model
 ┃ ┗ Item.java
 ┗📁 adapter
 ┃ ┗ ItemAdapter.java
 ┗📁 ui
 ┃ ┗ MainActivity.java
 ┃ ┗ DetailActivity.java
 ┗ AndroidManifest.xml
```

---

## 🔗 서버 연동

### Retrofit2 예시
```java
Retrofit retrofit = new Retrofit.Builder()
        .baseUrl("http://10.0.2.2:8080/") // 로컬호스트
        .addConverterFactory(GsonConverterFactory.create())
        .build();

ApiService api = retrofit.create(ApiService.class);
Call<List<Item>> call = api.getItems();
```

---

## 🚀 실행 방법

1️⃣ **백엔드 서버 실행**  
→ `main` 브랜치에서 `mvn spring-boot:run`

2️⃣ **안드로이드 앱 실행**  
→ Android Studio에서 `Run App` (에버리터 or 실제 기기)

---

## 🌐 API 예시

| 요청 | 경로 | 설명 |
|------|------|------|
| GET | `/items` | 전체 목록 |
| GET | `/items/{id}` | 단일 항목 조회 |
| POST | `/items` | 신규 등록 |
| PUT | `/items/{id}` | 수정 |
| DELETE | `/items/{id}` | 삭제 |

---

## 👨‍💻 개발자 정보
**홍휘창 (Hwichang-0222)**  
📍 [GitHub](https://github.com/Hwichang-0222)

