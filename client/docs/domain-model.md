# Domain Model

## VoteChoice (enum)
- JJAJANG
- JJAMPPONG

## Vote
- **voteId**: UUID (string)
- **choice**: enum VoteChoice (not null)
- **createdAt**: ISO-8601 string

## VoteResult (projection)
- **total**: number (>= 0)
- **counts**:
  - JJAJANG: number (>= 0)
  - JJAMPPONG: number (>= 0)
- **updatedAt**: ISO-8601 string

## AbusePolicy (optional, 가산점)
- **key**: string (IP / sessionId / deviceId 중 택1)
- **windowSeconds**: number (예: 30)
- **maxVotesPerWindow**: number (예: 1)
- **blockedUntil**: ISO-8601 string (optional)

## 검증 기준(명시):
- `choice`는 반드시 enum 중 하나여야 함 (그 외 값 400)
- 서버는 투표 저장 성공 후에만 카운트를 반영해야 함