# domain-model

예시:

## Candidate
- id: number
- name: string (2~20, not blank)
- group: string (0~30, optional)
- votes: number (>= 0)
- createdAt: ISO-8601 string

## VoteLog
- id: number
- candidateId: number (FK → Candidate.id)
- createdAt: ISO-8601 string

# 도메인 규칙
투표 시:
- Candidate.votes는 반드시 +1 증가
- VoteLog는 항상 1건 생성

rank는 저장하지 않고 조회 시 계산하는 것을 기본으로 한다.
rank 계산 규칙:
- votes desc
- name asc