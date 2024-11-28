import React, { useState, useEffect } from 'react';
import { useLocation, useNavigate } from 'react-router-dom';
import SideBar from '../../components/SideBar';
import { Exam } from '../../model/examRequestModel.tsx';

const ExamsList: React.FC = () => {
    const [exams, setExams] = useState<Exam[]>([]);
    const [selectedAll, setSelectedAll] = useState(false);
    const [selectedExams, setSelectedExams] = useState<number[]>([]);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState<string | null>(null);

    const navigate = useNavigate();
    const location = useLocation();
    const client = location.state?.client;

    useEffect(() => {
        const fetchExams = async () => {
            try {
                const response = await fetch('http://localhost:8080/exams');
                if (!response.ok) {
                    throw new Error(`HTTP error! status: ${response.status}`);
                }
                const data: Exam[] = await response.json();
                setExams(data);
            } catch (err: unknown) {
                if (err instanceof Error) {
                    console.error('Error fetching exams:', err.message);
                    setError(err.message);
                } else {
                    console.error('Unknown error fetching exams');
                    setError('An unexpected error occurred while fetching exams.');
                }
            } finally {
                setLoading(false);
            }
        };

        fetchExams();
    }, []);

    const handleSelectAll = () => {
        setSelectedAll(!selectedAll);
        if (!selectedAll) {
            setSelectedExams(exams.map((exam: Exam) => exam.id!).filter((id) => id !== null));
        } else {
            setSelectedExams([]);
        }
    };

    const handleSelectExam = (id: number) => {
        setSelectedExams((prevSelected) =>
            prevSelected.includes(id)
                ? prevSelected.filter((examId) => examId !== id)
                : [...prevSelected, id]
        );
    };

    const handleFinalize = async () => {
        if (!client || selectedExams.length === 0) {
            alert('Please select at least one exam and ensure client information is provided.');
            return;
        }

        try {
            const examTestIds: number[] = [];

            for (const examId of selectedExams) {
                const examTestPayload = {
                    id: 1,
                    examId,
                    testResultId: null,
                    status: 'Pending',
                };

                const response = await fetch('http://localhost:8080/exams-tests', {
                    method: 'POST',
                    headers: { 'Content-Type': 'application/json' },
                    body: JSON.stringify(examTestPayload),
                });

                if (!response.ok) {
                    throw new Error(`Failed to create ExamTest for exam ID: ${examId}`);
                }

                const savedExamTest = await response.json();
                examTestIds.push(savedExamTest.id);
            }

            const examRequestPayload = {
                examRequestId: null,
                clientId: client.id,
                examTestList: examTestIds,
                requestDate: new Date().toISOString().split('T')[0],
                totalPrice: 0.0,
                paymentMethod: 'Not Paid',
                status: 'Pending',
            };

            const response = await fetch('http://localhost:8080/exam-requests', {
                method: 'POST',
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify(examRequestPayload),
            });

            if (!response.ok) {
                throw new Error('Failed to create ExamRequest.');
            }

            const savedExamRequest = await response.json();
            alert('Exam Request successfully created!');
            navigate('/examrequest', { state: { examRequest: savedExamRequest } });
        } catch (err: unknown) {
            console.error('Error saving examrequest:', err);
            if (err instanceof Error) {
                alert(`An error occurred: ${err.message}`);
            } else {
                alert('An unexpected error occurred.');
            }
        }
    };

    return (
        <div className="p-8 bg-gray-100 h-screen">
            <header className="mb-6 flex items-center justify-between">
                <img src="/assets/blab.png" alt="Blab Logo" className="w-12 h-12 mr-4" />
                <h1 className="text-2xl font-semibold text-left">Laboratory Exams</h1>
                <button className="bg-orange-500 text-white p-0 w-8 h-8 rounded-full flex items-center justify-center">
                    A
                </button>
            </header>

            <div className="flex">
                <div className="flex h-screen overflow-x-hidden">
                    <SideBar />
                </div>

                <div className="flex flex-col w-full">
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

                    {/* Filters */}
                    <div className="flex gap-4 mb-6">
                        <select
                            className="bg-white border border-gray-300 rounded px-4 py-2 hover:bg-gray-200">
                            <option value="">Quantity: All</option>
                            <option value="1">1</option>
                            <option value="2">5</option>
                            <option value="3">10</option>
                        </select>

                        <select
                            className="bg-white border border-gray-300 rounded px-4 py-2 hover:bg-gray-200"
                        >
                            <option value="">Price: All</option>
                            <option value="20">20.00</option>
                            <option value="50">50.00</option>
                            <option value="60">60.00</option>
                            <option value="80">80.00</option>
                            <option value="80">100.00</option>
                        </select>

                        <select
                            className="bg-white border border-gray-300 rounded px-4 py-2 hover:bg-gray-200"
                        >
                            <option value="">Result Time: All</option>
                            <option value="1">1 day</option>
                            <option value="2">2 days</option>
                            <option value="3">3 days</option>
                            <option value="4">4 days</option>
                            <option value="5">5 days</option>
                        </select>

                        <select
                            className="bg-white border border-gray-300 rounded px-4 py-2 hover:bg-gray-200"
                        >
                            <option value="">Requirements: All</option>
                            <option value="20">20.00</option>
                            <option value="50">50.00</option>
                            <option value="60">60.00</option>
                            <option value="80">80.00</option>
                            <option value="80">100.00</option>
                        </select>

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
                                    {exams.map((exam: Exam) => (
                                        <tr key={exam.id!} className="hover:bg-gray-100">
                                            <td className="px-4 py-2">
                                                <input
                                                    type="checkbox"
                                                    checked={selectedExams.includes(exam.id!)}
                                                    onChange={() => handleSelectExam(exam.id!)}
                                                />
                                            </td>
                                            <td className="px-4 py-2">E{exam.id?.toString().padStart(3, '0')}</td>
                                            <td className="px-4 py-2">{exam.name}</td>
                                            <td className="px-4 py-2">{exam.requirements}</td>
                                            <td className="px-4 py-2">${exam.price?.toFixed(2)}</td>
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
                                className="w-auto bg-orange-500 text-white px-4 py-2 rounded mt-6"
                            >
                                Done
                            </button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    );
};

export default ExamsList;
