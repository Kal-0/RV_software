import React, { useState, useEffect } from 'react';
import { useLocation, useNavigate } from 'react-router-dom';
import SideBar from '../../components/SideBar';

const ExamsList: React.FC = () => {
    const [exams, setExams] = useState([]); // Armazena os exames disponíveis
    const [selectedAll, setSelectedAll] = useState(false); // Controle do checkbox principal
    const [selectedExams, setSelectedExams] = useState<number[]>([]); // IDs dos exames selecionados
    const [loading, setLoading] = useState(true); // Indica carregamento
    const [error, setError] = useState<string | null>(null); // Erros durante a execução

    const navigate = useNavigate();
    const location = useLocation();
    const client = location.state?.client; // Dados do cliente passados pela navegação

    // Função para buscar os exames da API
    useEffect(() => {
        const fetchExams = async () => {
            try {
                const response = await fetch('http://localhost:8080/exams');
                if (!response.ok) {
                    throw new Error(`HTTP error! status: ${response.status}`);
                }
                const data = await response.json();
                setExams(data); // Atualiza o estado com os exames
            } catch (err: any) {
                console.error('Error fetching exams:', err);
                setError('Failed to fetch exams.');
            } finally {
                setLoading(false); // Conclui o carregamento
            }
        };

        fetchExams();
    }, []);

    // Lógica para selecionar todos os checkboxes
    const handleSelectAll = () => {
        setSelectedAll(!selectedAll);
        if (!selectedAll) {
            setSelectedExams(exams.map((exam: any) => exam.id));
        } else {
            setSelectedExams([]);
        }
    };

    // Lógica para selecionar/desselecionar um exame individualmente
    const handleSelectExam = (id: number) => {
        setSelectedExams((prevSelected) =>
            prevSelected.includes(id)
                ? prevSelected.filter((examId) => examId !== id)
                : [...prevSelected, id]
        );
    };

    // Função para registrar os `ExamTest` e o `ExamRequest`
    const handleFinalize = async () => {
        if (!client || selectedExams.length === 0) {
            alert('Please select at least one exam and ensure client information is provided.');
            return;
        }

        try {
            // Lista para armazenar os `ExamTest` criados
            const examTestList = [];

            for (const examId of selectedExams) {
                const exam = exams.find((e: any) => e.id === examId);
                const examTestPayload = {
                    examTestId: null,
                    exam,
                    testResult: null,
                    status: 'Pending',
                };

                const response = await fetch('http://localhost:8080/exam-tests', {
                    method: 'POST',
                    headers: { 'Content-Type': 'application/json' },
                    body: JSON.stringify(examTestPayload),
                });

                if (!response.ok) {
                    throw new Error(`Failed to create ExamTest for exam ID: ${examId}`);
                }

                const savedExamTest = await response.json();
                examTestList.push(savedExamTest); // Adiciona o `ExamTest` à lista
            }

            // Calcular o preço total dos exames
            const totalPrice = examTestList.reduce(
                (total, examTest) => total + (examTest.exam?.price || 0),
                0
            );

            // Criar o `ExamRequest` com o cliente e a lista de `ExamTest`
            const examRequest = {
                examRequestId: null,
                client,
                examTestList,
                requestDate: new Date().toISOString().split('T')[0], // Data atual no formato YYYY-MM-DD
                totalPrice,
                paymentMethod: 'Not Paid',
                status: 'Pending',
            };

            const response = await fetch('http://localhost:8080/exam-request', {
                method: 'POST',
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify(examRequest),
            });

            if (!response.ok) {
                throw new Error('Failed to create ExamRequest.');
            }

            const savedExamRequest = await response.json();
            console.log('ExamRequest created:', savedExamRequest);

            alert('Exam Request successfully created!');
            navigate('/summary', { state: { examRequest: savedExamRequest } }); // Navega para a próxima página
        } catch (err: any) {
            console.error('Error:', err);
            alert('An error occurred while finalizing the Exam Request.');
        }
    };

    return (
        <div className="p-8 bg-gray-100 h-screen">
            <header className="mb-6 flex items-center justify-between">
                <img src="/assets/blab.png" alt="Blab Logo" className="w-12 h-12 mr-4" />
                <h1 className="text-2xl font-semibold text-left">Laboratory Exams</h1>
                <button className="bg-orange-500 text-white p-2 rounded-full">A</button>
            </header>

            <div className="flex">
                <div className="flex h-screen overflow-x-hidden">
                    <SideBar />
                </div>

                <div className="flex flex-col w-full">
                    <div className="bg-white rounded shadow-md overflow-auto">
                        {loading ? (
                            <p>Loading...</p>
                        ) : error ? (
                            <p className="text-red-500">{error}</p>
                        ) : (
                            <table className="w-full table-auto text-left">
                                <thead className="bg-gray-200 text-gray-700">
                                <tr>
                                    <th className="px-4 py-2">
                                        <input
                                            type="checkbox"
                                            checked={selectedAll}
                                            onChange={handleSelectAll}
                                        />
                                    </th>
                                    <th className="px-4 py-2">Code</th>
                                    <th className="px-4 py-2">Exam Name</th>
                                    <th className="px-4 py-2">Requirements</th>
                                    <th className="px-4 py-2">Price</th>
                                    <th className="px-4 py-2">Stipulated Time</th>
                                </tr>
                                </thead>
                                <tbody>
                                {exams.map((exam: any) => (
                                    <tr key={exam.id} className="hover:bg-gray-100">
                                        <td className="px-4 py-2">
                                            <input
                                                type="checkbox"
                                                checked={selectedExams.includes(exam.id)}
                                                onChange={() => handleSelectExam(exam.id)}
                                            />
                                        </td>
                                        <td className="px-4 py-2">E{exam.id.toString().padStart(3, '0')}</td>
                                        <td className="px-4 py-2">{exam.name}</td>
                                        <td className="px-4 py-2">{exam.requirements}</td>
                                        <td className="px-4 py-2">${exam.price.toFixed(2)}</td>
                                        <td className="px-4 py-2">{exam.analysisTime} hours</td>
                                    </tr>
                                ))}
                                </tbody>
                            </table>
                        )}
                    </div>

                    <div className="flex justify-end mt-8">
                        <button
                            type="button"
                            onClick={handleFinalize}
                            className="w-full bg-orange-500 text-white px-4 py-2 rounded mt-6"
                        >
                            Finalize
                        </button>
                    </div>
                </div>
            </div>
        </div>
    );
};

export default ExamsList;
