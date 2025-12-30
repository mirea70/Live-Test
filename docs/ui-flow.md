# ui-flow
## Pages
- / : 후보 목록 + 투표
- /candidates/{id} : 후보 상세 + 투표 로그
- /ranking : 랭킹 TOP N
- /admin/new-candidate : 후보 추가 (선택)

## User Flow
1. 사용자는 메인 페이지에서 후보 목록과 현재 순위를 본다.
2. 사용자는 특정 후보에 투표한다.
3. 투표 후 득표수 및 순위 변동을 확인한다.
4. 후보를 클릭해 상세 페이지로 이동한다.
5. 상세 페이지에서 최근 투표 로그를 확인한다.
6. 랭킹 페이지에서 TOP 10을 확인한다.
7. 관리 페이지에서 후보를 추가한다.

## Per-Page UI Requirements
### / (Candidate List)
- Header: "ChoVote" + navigation (Ranking)
- Candidate List:
    - name / group
    - votes
    - rank badge
    - primary CTA: "투표하기"

### States:
- Loading: skeleton card 3~5개
- Empty: "후보가 없습니다." + 후보 추가 CTA
- Error: alert + "다시 시도" 버튼

### /candidates/{id} (Candidate Detail)
- Candidate Card:
    - name, group
    - votes 강조
    - rank 표시
- Vote Button:
    - 투표 중 disabled + spinner
- Vote Log Section:
    - 최근 투표 로그 최대 10개
    - createdAt 표시
- States:
    - Loading
    - Error: "존재하지 않는 후보입니다." + 목록 이동 버튼

### /ranking
- Table or List:
    - rank / name / votes
- 상위 N명 표시
- States:
    - Loading
    - Empty
    - Error

### /admin/new-candidate
- Form:
    - name input
    - group input
- States:
    - validation error inline
    - submit loading
    - server error alert
- 성공 시: 메인 페이지 이동