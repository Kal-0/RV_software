import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import Home from './pages/home/Home';
import RegisterClient from './pages/registerclient/RegisterClient';

function App() {
    return (
        <Router>
            <Routes>
                <Route path="/" element={<Home />} />
                <Route path="/registerclient" element={<RegisterClient />} />
            </Routes>
        </Router>
    );
}

export default App;
