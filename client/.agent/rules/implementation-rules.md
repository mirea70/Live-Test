---
trigger: always_on
---

# Implementation Rules (Frontend)

## 0. 기본 규칙
1. 너는 프론트엔트 담당이다. server 폴더는 수정하지 말아라.
2. api 명세는 항상 docs/implementation-rules.md를 기준으로 삼아라.
3. 도메인 모델은 항상 docs/domain-model.md를 기준으로 삼아라.
4. docs 내 존재하는 md파일의 준수사항과 충돌되는 규칙이 있을 경우, docs 내 존재하는 md파일의 규칙을 우선한다.

## 1. UX 우선순위
- "동작 → 안정성 → UX 디테일 → 스타일" 순서로 개발한다.
- 모든 화면은 반드시 아래 4가지 상태 UI를 제공한다:
  1) Loading  2) Empty  3) Error  4) Success

## 2. UI 톤 & 레이아웃 (Modern SaaS baseline)
- 전체 톤: clean / modern / friendly / readable
- Layout:
  - 페이지 최대 폭: max-w-5xl (또는 4xl), 중앙 정렬
  - background: 매우 옅은 neutral (예: slate-50)
  - 카드: rounded-2xl + subtle shadow
- Typography:
  - 제목(h1): text-2xl~3xl / font-semibold
  - 본문: text-sm~base / text-slate-600
  - 정보(메타): text-xs~sm / text-slate-500
- Buttons:
  - Primary / Secondary / Destructive 3종만 사용
  - Destructive 액션은 confirm 또는 2-step UX를 둔다.

## 3. 접근성(A11y) & 기본 사용성
- 모든 input에는 label이 있어야 한다.
- 버튼/링크는 키보드 탭 이동 가능해야 한다.
- 클릭 영역은 모바일 기준 최소 40px 이상을 지향한다.
- focus ring을 유지한다(접근성 상 중요).

## 4. 컴포넌트 구조 규칙
- UI 컴포넌트는 재사용 가능하게 분리한다:
  - components/ui/* (shadcn/ui)
  - components/common/* (Header, Container, EmptyState, ErrorState 등)
  - features/* (도메인 기능 단위: posts, comments 등)
- 비즈니스 로직(데이터 fetch/mutation)은 components가 아니라 features/hooks에 둔다.

## 5. API / 데이터 규칙
- API 호출은 lib/api.ts 하나로 통일한다.
- 모든 API 에러는 공통 파싱 규칙을 가진다:
  - { code, message } 형식 우선 파싱
  - 실패 시 fallback 메시지 제공
- TanStack Query를 사용해:
  - 목록 조회: useQuery
  - 생성/삭제/수정: useMutation
  - 성공 시 invalidateQueries로 최신 데이터 보장

## 6. 폼 UX 규칙
- 모든 폼은 React Hook Form + Zod로 검증한다.
- 제출 버튼은:
  - invalid일 때 비활성화
  - 제출 중 loading 표시
- 실패 시:
  - 필드 에러는 필드 하단
  - 서버 에러는 상단 Alert + toast 동시 제공(선택)

## 7. 토스트/알림 규칙
- 성공/실패는 toast로 피드백 제공 (sonner)
- 심각한 에러는 화면 Alert로도 보여준다.

## 8. 라우팅 규칙
- App Router 사용.
- 페이지 단위: app/* (page.tsx)
- 상세 페이지는 app/[resource]/[id]/page.tsx 규칙을 따른다.

## 9. 성능/품질 (라이브 코테 기준 현실선)
- 과한 애니메이션/일러스트 금지.
- skeleton을 2~3개만 사용해도 UX 점수가 크게 오른다.
- 코드 복잡도보다 "완주 + 안정적인 상태 처리"를 우선한다.

## 10. 모달 구현 규칙
- 모달 구현 시, client/.agent/rules/modal.md 파일을 참고해 준수하여 구현한다.