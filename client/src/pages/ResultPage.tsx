import { useQuery } from '@tanstack/react-query';
import { useNavigate } from 'react-router-dom';
import { VoteService } from '@/lib/api';

export function ResultPage() {
    const navigate = useNavigate();

    const { data: result, isLoading, error } = useQuery({
        queryKey: ['voteResult'],
        queryFn: VoteService.getVoteResult,
        refetchInterval: 1000, // 1초마다 갱신 (실시간)
    });

    if (isLoading) {
        return (
            <div className="flex items-center justify-center min-h-screen">
                <div className="animate-spin rounded-full h-12 w-12 border-b-2 border-slate-900"></div>
            </div>
        );
    }

    if (error) {
        return (
            <div className="flex flex-col items-center justify-center min-h-screen gap-6 px-4">
                <div className="text-6xl">😢</div>
                <p className="text-xl text-slate-600 font-medium text-center">
                    결과를 불러오는데 실패했습니다.<br />
                    <span className="text-sm text-slate-400 mt-2 block">잠시 후 다시 시도해주세요.</span>
                </p>
                <button
                    onClick={() => navigate('/')}
                    className="px-8 py-3 bg-slate-900 text-white rounded-xl font-bold hover:bg-slate-800 transition-colors shadow-lg"
                >
                    처음으로 돌아가기
                </button>
            </div>
        );
    }

    if (!result) return null;

    const total = result.total;
    const jjajangCount = result.counts.JJAJANG;
    const jjamppongCount = result.counts.JJAMPPONG;

    const jjajangPercent = total === 0 ? 0 : Math.round((jjajangCount / total) * 100);
    const jjamppongPercent = total === 0 ? 0 : Math.round((jjamppongCount / total) * 100);

    return (
        <div className="container mx-auto px-4 py-12 max-w-2xl min-h-screen flex flex-col justify-center">
            <h1 className="text-4xl font-extrabold text-center mb-12 text-slate-900">투표 결과</h1>

            <div className="space-y-8 bg-white p-8 rounded-3xl shadow-xl border border-slate-100 mb-8 relative overflow-hidden">
                <div className="absolute top-0 left-0 w-full h-2 bg-gradient-to-r from-indigo-500 to-red-500"></div>

                {/* Jjajang Bar */}
                <div className="space-y-3">
                    <div className="flex justify-between items-end">
                        <span className="text-xl font-bold flex items-center gap-3 text-slate-800">
                            <span className="text-3xl bg-indigo-50 p-2 rounded-lg">🥢</span> 짜장면
                        </span>
                        <div className="text-right">
                            <span className="text-2xl font-bold text-indigo-600 block">{jjajangCount}표</span>
                            <span className="text-sm font-medium text-slate-400">{jjajangPercent}%</span>
                        </div>
                    </div>
                    <div className="h-6 bg-slate-100 rounded-full overflow-hidden shadow-inner">
                        <div
                            className="h-full bg-indigo-600 transition-all duration-1000 ease-out relative"
                            style={{ width: `${jjajangPercent}%` }}
                        >
                            <div className="absolute inset-0 bg-white/20 animate-pulse"></div>
                        </div>
                    </div>
                </div>

                {/* Jjamppong Bar */}
                <div className="space-y-3">
                    <div className="flex justify-between items-end">
                        <span className="text-xl font-bold flex items-center gap-3 text-slate-800">
                            <span className="text-3xl bg-red-50 p-2 rounded-lg">🍜</span> 짬뽕
                        </span>
                        <div className="text-right">
                            <span className="text-2xl font-bold text-red-500 block">{jjamppongCount}표</span>
                            <span className="text-sm font-medium text-slate-400">{jjamppongPercent}%</span>
                        </div>
                    </div>
                    <div className="h-6 bg-slate-100 rounded-full overflow-hidden shadow-inner">
                        <div
                            className="h-full bg-red-500 transition-all duration-1000 ease-out relative"
                            style={{ width: `${jjamppongPercent}%` }}
                        >
                            <div className="absolute inset-0 bg-white/20 animate-pulse"></div>
                        </div>
                    </div>
                </div>

                <div className="pt-8 border-t border-slate-100 text-center">
                    <p className="text-slate-500 font-medium bg-slate-50 inline-block px-4 py-2 rounded-full">
                        총 <span className="text-slate-900 font-bold">{total}</span>명이 참여했습니다
                    </p>
                </div>
            </div>

            <div className="text-center">
                <button
                    onClick={() => navigate('/')}
                    className="w-full py-5 rounded-2xl text-xl font-bold bg-slate-900 text-white hover:bg-slate-800 transition-all shadow-lg hover:shadow-xl hover:-translate-y-1 active:scale-95"
                >
                    다시 투표하기
                </button>
            </div>
        </div>
    );
}
