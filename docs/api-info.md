# API Information

## Base URL
http://localhost:8080/api

## Endpoints

### 투표 생성 : POST /v1/votes
**설명**: 사용자가 한 표를 행사한다. (상태 변경)

**Request**:
```json
{
  "choice": "JJAJANG"
}
```

**Response (201)**:
```json
{
  "voteId": "c2b0c1a8-3a1f-4b9b-9c2b-7a7c7c6c4a10",
  "choice": "JJAJANG",
  "createdAt": "2025-12-31T10:00:00+09:00"
}
```

**Validation Error (400)**:
```json
{
  "code": "INVALID_CHOICE",
  "message": "choice must be one of [JJAJANG, JJAMPPONG]"
}
```

**Abuse Block (429) (가산점 옵션)**:
```json
{
  "code": "TOO_MANY_REQUESTS",
  "message": "You can vote again later."
}
```

### 결과 조회 : GET /v1/votes/result
**설명**: 현재까지 집계 결과를 조회한다. (조회 전용)

**Response (200)**:
```json
{
  "total": 12,
  "counts": {
    "JJAJANG": 7,
    "JJAMPPONG": 5
  },
  "updatedAt": "2025-12-31T10:02:10+09:00"
}
```

### (선택) 실시간 폴링용 경량 조회 : GET /v1/votes/result/brief
**설명**: 결과 페이지에서 1~2초 폴링할 때 payload 최소화.

**Response (200)**:
```json
{
  "jjajang": 7,
  "jjamppong": 5
}
```
