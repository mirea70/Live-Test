# Development Session Log: Jjajang vs Jjamppong API

이 문서는 프로젝트 개발 과정에서의 주요 대화 내용과 작업 흐름을 요약한 기록입니다.

## 1. 프로젝트 목표 설정
- **목표**: 짜장면 vs 짬뽕 투표 시스템의 백엔드 API 구현.
- **기술 스택**: Java 17, Spring Boot 3.5.9, JPA, H2/MySQL.
- **기능**: 투표하기 (`POST`), 결과 조회하기 (`GET`).

## 2. 초기 구현 (Implementation)
- **설정**: `build.gradle` 의존성 추가 (JPA, Validation 등), `application.yml` DB 설정.
- **도메인**: `VoteChoice` (Enum), `Vote` (Entity), `VoteRepository` 구현.
- **서비스**: 투표 생성 및 결과 계산 로직을 담은 `VoteService` 구현.
- **API**: DTO (`Request/Response`) 정의 및 `VoteController` 구현.
- **예외 처리**: `GlobalExceptionHandler`를 통해 표준화된 에러 응답 처리.

## 3. 테스트 및 디버깅 (Challenges & Solutions)
### 문제 발생
- **상황**: `VoteControllerTest` 실행 시 `IllegalStateException` 및 `JPA metamodel must not be empty` 에러 발생.
- **원인**: `TestApplication` 메인 클래스에 붙은 `@EnableJpaAuditing` 어노테이션이 `@WebMvcTest` 환경에서도 JPA 관련 빈을 강제로 로딩하려 하여 충돌 발생.

### 해결 과정
- **조치**: `@EnableJpaAuditing` 설정을 메인 클래스에서 제거하고, 별도의 `JpaConfig` 설정 클래스로 분리.
- **결과**: `VoteControllerTest`를 포함한 모든 테스트(단위, 통합, 시스템) 통과 확인.

## 4. 추가 요구사항 반영 (Refinement)
- **CORS 설정**: 개발 편의를 위해 모든 출처(`*`)를 허용하는 `WebConfig` 추가 구현.

## 5. 최종 결과
- 백엔드 API 구현 완료.
- H2(테스트용) 및 MySQL(로컬용) 프로필 분리.
- 전체 테스트 슈트 통과 (`./gradlew test`).
