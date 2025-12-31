# Acceptance Criteria

## Must Have
- `/vote`에서 짜장면/짬뽕 중 하나를 선택해 투표 가능
- 투표는 `POST /api/v1/votes`로 전송되고, 성공 시 `/result`로 이동
- `/result`에서 현재 누적 결과를 `GET /api/v1/votes/result`로 조회해 표시
- 결과는 새로고침해도 유지됨(서버 저장)
- 투표/결과 페이지 모두 Loading/Error 상태 UI가 존재
- 입력 검증: enum 외 값은 400 처리

## Nice to Have (time permitting)
- 서버 재시작 후에도 결과 유지(파일/DB 영속화)
- 어뷰징 방지(예: IP 기준 rate limit, 429)
- 결과 페이지 실시간성 개선(폴링 최적화 or SSE)
- 토스트 알림/성공 메시지
