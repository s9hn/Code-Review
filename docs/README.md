# 안녕하세요 ‘함께 일하고 싶은 동료’를 목표로 성장하는 Android Developer 김세훈 입니다.

## 구현 기능 소개

| **검색 기능**                                     | **즐겨찾기 기능**                                   | **필터 기능**                                     | **상세보기 기능**                                   |
|-----------------------------------------------|-----------------------------------------------|-----------------------------------------------|-----------------------------------------------|
| <img src="images/feature1.gif" width="200" /> | <img src="images/feature2.gif" width="200" /> | <img src="images/feature3.gif" width="200" /> | <img src="images/feature4.gif" width="200" /> |
| 실시간 검색 및 페이징 처리                               | 도서 즐겨찾기 추가/삭제                                 | 가격대별 필터링 및 정렬                                 | 도서 상세 정보 조회                                   |

* images 패키지에서 확인하실 수 있습니다!

## 빌드 방법

### 개발 환경 요구사항

- **Android Studio**: Ladybug 2024.2.1 이상
- **JDK**: 17 이상
- **Gradle**: 8.0 이상

### 빌드 및 실행

1. Android Studio에서 프로젝트 열기
2. Gradle Sync 완료 후 Build → Clean Project
3. Build → Rebuild Project 실행
4. 최소 API 28(Android 9.0) 이상의 디바이스 또는 에뮬레이터에서 실행

### 프로젝트 스펙

- **Compile SDK**: 36 (Android 15)
- **Min SDK**: 28 (Android 9.0)
- **Target SDK**: 36 (Android 15)
- **Java Version**: 17
- **Kotlin**: 2.x

## 사용 프레임워크 및 기술스택

#### 모듈화

기능·레이어 단위 분리로 캡슐화와 병렬 빌드 및 재사용성을 높였고 의존 방향을 명확히 했습니다

#### 클린 아키텍처

도메인·데이터·프레젠테이션 관심사를 분리해 테스트 용이성과 교체 용이성을 확보했습니다

#### Jetpack Compose

선언형 UI와 단방향 상태 관리로 생산성을 높이고 미리보기·리컴포지션으로 변경 대응력을 높였습니다

#### Kotlin Coroutines & Flow

구조화된 비동기와 스트림을 활용해 디바운스·취소·스레드 제어를 단순화했습니다

#### Dagger-Hilt(KSP)

의존성 그래프 자동화와 스코프 기반 주입으로 결합도를 낮추고 테스트와 교체를 용이하게 했습니다

#### Coil

Compose 친화적 이미지 로더로 메모리·디스크 캐싱과 크로스페이드로 스크롤 성능과 UX를 개선했습니다

#### Retrofit + kotlinx.serialization

타입 안전 직렬화와 인터페이스 기반 API 정의로 네트워크 레이어를 간결하고 안전하게 구성했습니다

#### Room

타입 안전 ORM과 SQL 검증, Flow·Paging 연동으로 로컬 즐겨찾기 저장과 쿼리·페이징을 효율화했습니다

#### Paging 3

스트리밍 로드와 캐시, 로드 상태 제공으로 무한 스크롤 데이터의 성능과 사용자 경험을 향상했습니다

## 프로젝트 구조

```
app/
├── core/
│   ├── designsystem/    # 공통 디자인 시스템, 테마
│   ├── router/          # 네비게이션 라우팅
│   ├── network/         # API 통신 (Retrofit, OkHttp)
│   └── database/        # Room 데이터베이스
├── feature/
│   ├── main/           # Compose Bottom Navigation
│   ├── books-ui/       # 공통 UI 컴포넌트 및 모델
│   ├── books-search/   # 도서 검색 화면
│   ├── books-favorite/ # 즐겨찾기 화면
│   └── books-detail/   # 도서 상세 화면
├── data/
│   ├── search/         # 검색 Repository 구현
│   └── favorite/       # 즐겨찾기 Repository 구현
└── domain/
    ├── book/           # 도서 Entity, 공통 모델
    ├── search/         # 검색 Use Case
    └── favorite/       # 즐겨찾기 Use Case
```

## 주요 구현 포인트

### 아키텍처 & 모듈 구조

- **모듈화 & 클린 아키텍처**: 피처/코어/도메인/데이터로 분리하여 역할과 의존 방향을 명확히 하였습니다.
- **네트워크 레이어**: Retrofit + kotlinx.serialization, OkHttp 로깅/헤더 인터셉터 구성, `local.properties` →
  `BuildConfig`로 주입(기반 URL/키)하였습니다.
- **DI(Hilt + KSP)**: 앱/각 모듈에 Hilt 적용, 네트워크/DB 모듈에 `@Module @InstallIn(SingletonComponent::class)`로
  프로바이더를 구성하였습니다.

### 데이터 레이어

- **로컬 데이터베이스**: Room(Entity/Dao/Database) + PagingSource로 로컬 즐겨찾기 조회/검색/정렬/가격 필터를 처리하였습니다.
- **도메인 모델**: `Book` 모델 정의, `isbn` 기반 고유 `id`/`stableId`(해시→양수 Long) 제공, 출간일은 `YYYY-MM-DD`만 파싱하도록
  고정하였습니다.

### UI/UX (Compose)

- **검색 기능**: 입력 디바운스(Flow), 즉시 검색 트리거, 정렬 변경 시 재요청, Paging3 스트림 `cachedIn` 처리하였습니다.
- **즐겨찾기**: 로컬 검색/정렬/가격 필터(Flow 결합) + Paging3 처리하였습니다.
- **네비게이션**: 탭별 그래프 분리, 상세 화면 전달은 직렬화 라우트 대응(권장: ID만 전달)하였습니다.
- **UX 개선**: 바텀바 겹침 방지(Scaffold padding 연동), 외부 터치 키보드 내리기, 클릭 스로틀, RangeSlider/Track 커스텀, 스낵바 호이스팅을
  적용하였습니다.

## 트러블슈팅

### 빌드 & 컴파일 이슈

- **KSP ExitCode / 컴파일 실패**: 최상위 `build.gradle`에 `ksp`, `hilt` 플러그인을 `apply false`로 등록하고, 모듈별로 재적용하여
  해결하였습니다. KSP/Kotlin 버전 정합도 맞추었습니다.
- **BuildConfig 인식 실패**: 라이브러리 모듈에 `buildFeatures.buildConfig = true` 및 `buildConfigField` 선언 후, 해당
  모듈의 `BuildConfig`를 참조하도록 수정하였습니다.
- **kotlinx.serialization 경고/미적용**: 직렬화가 필요한 모듈에 `kotlin-serialization` 플러그인과 라이브러리를 추가하여 해결하였습니다.
- **중복 클래스 에러**: Room 컴파일러를 `implementation`로 추가하여 충돌하였기에 `ksp(libs.room.compiler)`로 변경, 중복 의존 정리로
  해결하였습니다.

### 네비게이션 & 데이터 처리

- **Navigation 직렬화 오류**: `androidx.navigation:navigation-kotlinx-serialization` 추가 및 라우트 데이터 클래스
  `@Serializable` 부여로 해결하였습니다. ID만 전달 후 상세에서 조회하는 방식을 권장합니다.
- **날짜 파싱 예외**: 응답 형식을 `YYYY-MM-DD`로 고정하고 `LocalDate.parse(value, ISO_LOCAL_DATE)`만 허용하도록 매퍼를
  수정하였습니다.

### UI/UX 개선

- **바텀바와 리스트 겹침**: `Scaffold`의 `paddingValues`를 `LazyColumn`의 `contentPadding`과 연동하여 아이템이 바텀바를 덮지
  않도록 처리하였습니다.
- **Coil 이미지 로딩**: `remember(imageUrl)`로 `ImageRequest`를 생성하고 `crossfade`, 기본 메모리/디스크 캐시 동작을
  활용하였습니다.
- **입력 UX 개선**: 외부 터치 시 키보드 내리기, 버튼 클릭 스로틀로 중복 액션을 방지하였습니다.