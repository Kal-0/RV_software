import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import Home from './pages/home/Home';
import RegisterClient from './pages/registerclient/RegisterClient';
import ExamsList from './pages/examslist/ExamsList';
import ExamRequest from './pages/examrequest/ExamRequest';
import PaymentMethod from './pages/paymentmethod/PaymentMethod';
import ServiceNumber from './pages/servicenumber/ServiceNumber';
import ListClient from './pages/listclient/ListClient';

function App() {
    return (
        <Router>
            <Routes>
                 {/* Home como layout pai */}
                <Route path="/" element={<Home />} />
                    {/* Rotas filhas renderizadas na Main */}
                    <Route path="/servicenumber" element={<ServiceNumber />} />
                    <Route path="/listclient" element={<ListClient />} />
                    <Route path="/registerclient" element={<RegisterClient />} />
                    <Route path="/examslist" element={<ExamsList />} />
                    <Route path="/examrequest" element={<ExamRequest />} />
                    <Route path="/paymentmethod" element={<PaymentMethod />} />
            </Routes>
        </Router>
    );
}

export default App;
