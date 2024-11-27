import React, { useState, useEffect } from 'react';
import { Link } from 'react-router-dom';
import SideBar from '../../components/SideBar';

const ClientList: React.FC = () => {
    const [exams, setExams] = useState([]); // Estado para armazenar os exames
    const [selectedAll, setSelectedAll] = useState(false); // Controle do checkbox principal
    const [selectedExams, setSelectedExams] = useState<number[]>([]); // IDs dos exames selecionados
    const [loading, setLoading] = useState(true); // Estado para indicar carregamento
    const [error, setError] = useState<string | null>(null); // Estado para erros

    // Função para buscar os dados da API
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
                setLoading(false); // Marca o carregamento como concluído
            }
        };

        fetchExams();
    }, []); // Executa apenas uma vez quando o componente é montado

    // Lógica para selecionar todos os checkboxes
    const handleSelectAll = () => {
        setSelectedAll(!selectedAll); // Inverte o estado
        if (!selectedAll) {
            // Seleciona todos os exames
            setSelectedExams(exams.map((exam: any) => exam.id));
        } else {
            // Limpa a seleção
            setSelectedExams([]);
        }
    };

    // Lógica para selecionar/desselecionar um exame individualmente
    const handleSelectExam = (id: number) => {
        setSelectedExams((prevSelected) =>
            prevSelected.includes(id)
                ? prevSelected.filter((examId) => examId !== id) // Remove da seleção
                : [...prevSelected, id] // Adiciona à seleção
        );
    };

    return (
        <div className="p-8 bg-gray-100 h-screen">
            {/* Header */}
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
                    {/* Search */}
                    <div className="mb-6 flex gap-4 items-center">
                        <input
                            type="text"
                            placeholder="Search for Name, CPF, Birth Date or ID"
                            className="flex-1 border border-gray-300 rounded px-4 py-2 focus:outline-none focus:ring focus:ring-orange-300"
                        />
                        <button className="bg-white-500 text-white px-4 py-2 rounded">
                            <img src="/assets/loupe.png" alt="Search" className="w-5 h-5" />
                        </button>
                    </div>

                    {/* Exam List */}
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

                    {/* Done Button */}
                    <div className="flex justify-end mt-8">
                        <Link to="/examrequest" className="text-orange-500 font-medium">
                            <button type="button" className="w-full bg-orange-500 text-white px-4 py-2 rounded mt-6">
                                Done
                            </button>
                        </Link>
                    </div>
                </div>
            </div>
        </div>
    );
};

export default ClientList;
