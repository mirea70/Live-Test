## Base URL
- http://localhost:8080/api

## Endpoints

### 목록조회 : GET /v1/posts

Response:

```json
{
    "posts": [
        {
            "id": 1,
            "title": "Post 1",
            "content": "Content 1"
        },
        {
            "id": 2,
            "title": "Post 2",
            "content": "Content 2"
        }
    ]
}
```

### 상세조회 : GET /v1/posts/{id}

Response:

```json
{
    "post": {
        "id": 1,
        "title": "Post 1",
        "content": "Content 1"
    }
}
```

### 생성 : POST /v1/posts/new

Request:

```json
{
    "title": "Post 1",
    "content": "Content 1"
}
```

### 수정 : PUT /v1/posts/{id}

Request:

```json
{
    "title": "Post 1",
    "content": "Content 1"
}
```

### 삭제 : DELETE /v1/posts/{id}
