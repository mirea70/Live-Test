import { Routes, Route, Navigate } from 'react-router-dom';
import { Toaster } from 'sonner';
import { VotePage } from '@/pages/VotePage';
import { ResultPage } from '@/pages/ResultPage';

function App() {
  return (
    <div className="min-h-screen bg-slate-50 text-slate-900 font-sans">
      <Toaster position="top-center" richColors />
      <Routes>
        <Route path="/" element={<VotePage />} />
        <Route path="/result" element={<ResultPage />} />
        <Route path="*" element={<Navigate to="/" replace />} />
      </Routes>
    </div>
  );
}

export default App;
