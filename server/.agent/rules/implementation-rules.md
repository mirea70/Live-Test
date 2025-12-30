---
trigger: always_on
---

# Implementation Rules (Backend)

## 기본 규칙
1. 너는 백엔드 담당이다. client 폴더는 수정하지 말아라.
2. 구현해야할 API는 항상 docs/implementation-rules.md를 기준으로 삼아라.
3. 도메인 모델은 항상 docs/domain-model.md를 기준으로 삼아라.
4. 네이밍규칙은 프로덕트 코드는 카멜 케이스, 테스트 코드는 스네이크케이스로 항상 지켜라.
5. 네이밍 시, 줄임말은 무조건 금지한다.
6. 데이터를 교환할 때는 항상 DTO를 최대한 활용한다.

## 아키텍처 규칙 : 전형적인 레이어드 아키텍처
- 프레젠테이션 레이어 : 외부와 통신을 담당하는 컨트롤러들을 모아둔 계층. 외부로부터의 기본적인 입력값에 대한 유효성 검증을 실시한다. 비즈니스 레이어의 서비스를 호출해 비즈니스 로직에 대한 책임을 위임한다.
- 비즈니스 레이어 : 일반적인 서비스 클래스에 해당하며, 레포지토리 레이어를 통해 DB와 통신하면서, 비즈니스 로직을 전개한다. 트랜잭션에 대한 책임을 가지며, 비즈니스 규칙에 위배될 경우 에러를 던진다.
- 레포지토리 레이어 : 

## 테스트 규칙
1. 단위 테스트 : 기본적으로 직접 작성한 함수, 클래스, 모듈에 대한 모든 단위 테스트를 작성한다.
2. 통합 테스트 : 프레젠테이션 계층에 존재하는 컨트롤러들은 Spring과 함께 테스트하고, Service 로직은 Mocking한다. 레포지토리레이어에 존재하는 Repository들은 @DataJPATest를 활용하여 DB와의 통합테스트로 수행한다.
3. 시스템 테스트 : @SpringBootApplication으로 시스템을 올리고 mockMvc를 활용해 직접 API를 호출하여 해피 케이스와, 주요 실패케이스를 검증한다.

## 에러 핸들링 규칙
1. 에러는 항상 커스텀 에러를 정의해 던진다.
2. GlobalHandlerException에서 일괄적으로 최종핸들링하여, 외부로 반환한다.
3. 에러 반환 포맷 : code(HTTP 상태코드 값과 동일), message(에러메시지)
- (클래스 예시)
@Getter
public class ErrorResponse {
    private Integer code;
    private String message;

    private ErrorResponse() {}

    private ErrorResponse(BaseErrorInfo errorInfo) {
        this.code = errorInfo.getCode();
        this.message = errorInfo.getMessage();
    }

    public static ErrorResponse of(BaseErrorInfo errorInfo) {
        return new ErrorResponse(errorInfo);
    }
}

## 프로그래밍 규칙
1. 도메인 객체를 정의하고, 도메인 규칙이나 로직들은 최대한 해당 도메인 내에서 해결하여 응집도를 높인다. 도메인 규칙에 위배되면 커스텀 에러를 던진다. 비즈니스 레이어에서는 이 도메인 객체들을 조합하여 비즈니스 로직을 전개한다.
2. 도메인 객체를 생성할 때는, 생성자는 은닉하고 팩토리 메서드를 외부에 제공하여 항상 의도에 맞는 완전 객체로 생성되게끔 해야한다.
3. SOLID 원칙을 최대한 준수해가며 개발하며, 특히 OCP 원칙을 준수할 수 있도록 무조건 노력한다.
4. 도메인 특성에 따라 동시성을 고려해 개발을 진행한다.