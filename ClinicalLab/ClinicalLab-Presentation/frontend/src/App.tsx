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
                <Route path="/" element={<Home />} />
                <Route path="/registerclient" element={<RegisterClient />} />
                <Route path="/examslist" element={<ExamsList />} />
                <Route path="/examrequest" element={<ExamRequest />} />
                <Route path="/paymentmethod" element={<PaymentMethod />} />
                <Route path="/servicenumber" element={<ServiceNumber />} />
                <Route path="/listclient" element={<ListClient />} />
            </Routes>
        </Router>
    );
}

export default App;
