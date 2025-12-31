# 개발 세션 요약 (Jjajang vs Jjamppong)

## 1. 프로젝트 초기화 및 설정
- **Tech Stack**: Vite + React + TypeScript
- **Styling**: Tailwind CSS (v3.4.17), shadcn/ui, Lucide React
- **Dependencies**: React Router, TanStack Query, Axios, React Hook Form + Zod
- **이슈 해결**:
    - 초기 `npm create vite` 시 디렉토리 충돌 -> 임시 폴더 생성 후 이동
    - Tailwind CSS v4와 PostCSS 플러그인 호환성 문제 -> **v3로 다운그레이드**하여 해결

## 2. 주요 기능 구현
### 프론트엔드 구조
- `src/lib/api.ts`: Axios 인스턴스 및 `VoteService` 구현 (인터셉터로 에러 표준화)
- `src/pages/VotePage.tsx`: 메뉴 선택 카드 UI 및 투표 로직, 로딩/에러 상태 처리
- `src/pages/ResultPage.tsx`: 1초 간격 폴링(`refetchInterval`) 및 실시간 결과 막대 그래프 시각화

### UI/UX
- 현대적인 카드 레이아웃, 직관적인 이모지 사용
- 반응형 디자인 및 Hover/Active 인터랙션 적용
- 모든 텍스트 한글화

## 3. 네트워크 및 환경 설정 변경
- **Port 변경**: 기본 `5173` -> **`3000`** (`vite.config.ts`)
- **외부 접속 허용**: `--host` 옵션 활성화 (`host: true`) -> 공인 IP/네트워크 IP로 접속 가능
- **API 엔드포인트 변경 이력**:
    1. `http://localhost:8080/api` (초기)
    2. `http://localhost:8080/api/v1` (요구사항 반영)
    3. `http://114.205.42.7:8080/api/v1` (공인 IP 서버 지정)

## 4. 트러블슈팅 (Troubleshooting)
### Q1. 네트워크 에러 ("Network Error")
- **원인**: 백엔드 서버가 로컬(`localhost`)에 없어서 연결 실패
- **해결**: 외부 테스트 서버 IP(`114.205.42.7`)로 변경 시도

### Q2. CORS (Cross-Origin Resource Sharing) 에러
- **원인**: 브라우저 보안 정책상 프론트엔드(`:3000`)에서 다른 도메인(`:8080`)으로 직접 호출 차단
- **해결**: Vite **Proxy 설정** 적용
    - 프론트엔드는 `/api/v1`으로 요청
    - Vite 서버가 `http://114.205.42.7:8080`으로 중계하여 CORS 우회

### Q3. 리다이렉션 문제 (`start.asp`)
- **현상**: API 요청 시 `start.asp`로 리다이렉트되며 에러 발생
- **원인**: 해당 IP(`114.205.42.7`)의 8080 포트가 공유기 관리자 페이지 또는 인터넷 인증(Captive Portal) 페이지로 연결됨
- **결론**: 백엔드 서버가 아닌 공유기가 응답하고 있음. 올바른 백엔드 포트 확인 또는 포트 포워딩 필요

## 5. 최종 결과
- 웹 애플리케이션 UI/기능 구현 완료
- `http://localhost:3000` 및 외부 IP 접속 가능
- 현재 백엔드 서버 접속 불가(공유기 충돌) 이슈 확인 완료
