---
trigger: always_on
---

# Tech Stack (Frontend)

## Framework
- React 18
- TypeScript (strict)

## Build Tool
- Vite

## Styling
- Tailwind CSS
- shadcn/ui (Radix 기반 컴포넌트) + lucide-react 아이콘
  - 목적: 기본 UI 퀄리티/일관성/접근성 확보

## Data Fetching
- TanStack Query (React Query)
  - 서버 상태 관리, 캐싱, 로딩/에러, 재시도, invalidation 통일

## Forms & Validation
- React Hook Form
- Zod (schema validation)

## UX Utilities
- Sonner (toast)
- clsx + tailwind-merge (className 관리)

## Date/Format
- dayjs (또는 date-fns) for date formatting

## Testing (optional for live coding)
- Playwright (e2e) or React Testing Library (단, 라이브 코테에서는 생략 가능)

## Code Quality
- ESLint + Prettier