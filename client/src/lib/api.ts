import axios, { AxiosError } from 'axios';

export type VoteChoice = 'JJAJANG' | 'JJAMPPONG';

export interface VoteResponse {
    voteId: string;
    choice: VoteChoice;
    createdAt: string;
}

export interface VoteResult {
    total: number;
    counts: {
        JJAJANG: number;
        JJAMPPONG: number;
    };
    updatedAt: string;
}

export interface ApiError {
    code?: string;
    message: string;
}

const api = axios.create({
    baseURL: '/api/v1',
    timeout: 5000,
});

api.interceptors.response.use(
    (response) => response,
    (error: AxiosError<{ code: string; message: string }>) => {
        // 공통 에러 파싱
        if (error.response?.data) {
            const { code, message } = error.response.data;
            return Promise.reject({ code, message } as ApiError);
        }
        return Promise.reject({ message: error.message || '알 수 없는 오류가 발생했습니다.' } as ApiError);
    }
);

export const VoteService = {
    // 투표 생성
    createVote: async (choice: VoteChoice): Promise<VoteResponse> => {
        const response = await api.post<VoteResponse>('/votes', { choice });
        return response.data;
    },

    // 결과 조회
    getVoteResult: async (): Promise<VoteResult> => {
        const response = await api.get<VoteResult>('/votes/result');
        return response.data;
    },
};

export default api;
