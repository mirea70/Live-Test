# UI Flow

## Pages
- `/vote` : 투표 페이지
- `/result` : 결과 페이지

## User Flow
1. 사용자가 `/vote` 진입
2. 짜장면/짬뽕 중 하나 선택
3. “투표하기” 클릭 → `POST /api/v1/votes`
4. 성공 시 `/result`로 이동
5. `/result`에서 `GET /api/v1/votes/result` 주기적 조회(폴링)로 실시간 반영
6. (선택) “다시 투표하기” 클릭 시 `/vote` 이동

## Per-Page UI Requirements

### /vote
**UI**
- Title: “짜장면 vs 짬뽕 투표”
- 선택 컴포넌트: radio 2개 or 카드 선택 2개
- CTA 버튼: “투표하기” (선택 전 disabled)

**States**
- **Idle**: 선택 대기
- **Validation**: 선택 안 하면 버튼 disabled 유지
- **Submitting**: 버튼 로딩 + 중복 클릭 방지
- **Error**:
  - 400: “선택값이 올바르지 않습니다.”
  - 429: “너무 자주 투표하고 있어요. 잠시 후 다시 시도해 주세요.”
  - Network: “네트워크 오류가 발생했어요. 다시 시도해 주세요.”

### /result
**UI**
- Title: “현재 투표 결과”
- Counts:
  - “짜장면: N표”
  - “짬뽕: M표”
- (선택) Link: “다시 투표하기” → `/vote`

**Real-time**
- `GET /api/v1/votes/result`를 2초 간격 폴링
- 탭 비활성화 시 폴링 간격 늘리거나 일시정지(선택)

**States**
- **Loading**: 최초 로딩 스켈레톤/로더
- **Error**: alert + “다시 불러오기” 버튼
- **Empty(0표)**: “아직 투표가 없어요. 첫 투표를 해보세요!”
