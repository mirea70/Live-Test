import { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { useMutation } from '@tanstack/react-query';
import { VoteService, type VoteChoice } from '@/lib/api';

export function VotePage() {
    const navigate = useNavigate();
    const [selected, setSelected] = useState<VoteChoice | null>(null);

    const { mutate: vote, isPending } = useMutation({
        mutationFn: (choice: VoteChoice) => VoteService.createVote(choice),
        onSuccess: () => {
            // Toast will be added later
            navigate('/result');
        },
        onError: (error: any) => {
            // Toast will be added later
            alert(error.message || '투표에 실패했습니다.');
        },
    });

    const handleVote = () => {
        if (selected) {
            vote(selected);
        }
    };

    return (
        <div className="container mx-auto px-4 py-12 max-w-2xl min-h-screen flex flex-col justify-center">
            <h1 className="text-4xl font-extrabold text-center mb-12 text-slate-900 tracking-tight">
                오늘의 점심 메뉴는?
            </h1>

            <div className="grid grid-cols-1 md:grid-cols-2 gap-6 mb-10">
                <button
                    onClick={() => setSelected('JJAJANG')}
                    className={`group relative p-8 rounded-3xl border-2 transition-all duration-300 flex flex-col items-center justify-center gap-6 bg-white shadow-sm hover:shadow-xl
            ${selected === 'JJAJANG'
                            ? 'border-indigo-600 ring-4 ring-indigo-50/50 scale-[1.02]'
                            : 'border-slate-100 hover:border-slate-200 hover:-translate-y-1'
                        }
          `}
                >
                    <div className="text-8xl filter drop-shadow-md transition-transform group-hover:scale-110 duration-300">🥢</div>
                    <div className="text-center">
                        <h2 className="text-2xl font-bold text-slate-800">짜장면</h2>
                        <p className="text-slate-500 mt-2 font-medium">달콤짭짤 감칠맛!</p>
                    </div>
                    {selected === 'JJAJANG' && (
                        <div className="absolute top-4 right-4 text-indigo-600">
                            <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24" fill="none" stroke="currentColor" strokeWidth="3" strokeLinecap="round" strokeLinejoin="round"><polyline points="20 6 9 17 4 12"></polyline></svg>
                        </div>
                    )}
                </button>

                <button
                    onClick={() => setSelected('JJAMPPONG')}
                    className={`group relative p-8 rounded-3xl border-2 transition-all duration-300 flex flex-col items-center justify-center gap-6 bg-white shadow-sm hover:shadow-xl
            ${selected === 'JJAMPPONG'
                            ? 'border-red-500 ring-4 ring-red-50/50 scale-[1.02]'
                            : 'border-slate-100 hover:border-slate-200 hover:-translate-y-1'
                        }
          `}
                >
                    <div className="text-8xl filter drop-shadow-md transition-transform group-hover:scale-110 duration-300">🍜</div>
                    <div className="text-center">
                        <h2 className="text-2xl font-bold text-slate-800">짬뽕</h2>
                        <p className="text-slate-500 mt-2 font-medium">얼큰시원 국물맛!</p>
                    </div>
                    {selected === 'JJAMPPONG' && (
                        <div className="absolute top-4 right-4 text-red-500">
                            <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24" fill="none" stroke="currentColor" strokeWidth="3" strokeLinecap="round" strokeLinejoin="round"><polyline points="20 6 9 17 4 12"></polyline></svg>
                        </div>
                    )}
                </button>
            </div>

            <div className="text-center px-4">
                <button
                    onClick={handleVote}
                    disabled={!selected || isPending}
                    className={`w-full py-5 rounded-2xl text-xl font-bold text-white transition-all duration-300 transform
            ${!selected || isPending
                            ? 'bg-slate-200 text-slate-400 cursor-not-allowed'
                            : 'bg-slate-900 hover:bg-slate-800 shadow-xl hover:shadow-2xl hover:-translate-y-1 active:scale-95'
                        }
          `}
                >
                    {isPending ? (
                        <span className="flex items-center justify-center gap-2">
                            <span className="animate-spin rounded-full h-5 w-5 border-b-2 border-white"></span>
                            투표 처리중...
                        </span>
                    ) : '투표하기'}
                </button>
            </div>
        </div>
    );
}
