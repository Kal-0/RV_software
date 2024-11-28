import React, { useEffect, useState } from 'react';
import { useLocation, useNavigate} from 'react-router-dom';
import SideBar from '../../components/SideBar';
import { Exam, ExamRequest } from '../../model/examRequestModel.tsx';
import { Client } from '../../model/clientModel.tsx';


const ClientList: React.FC = () => {
    const location = useLocation();
    const navigate = useNavigate();
    const examRequest = location.state?.examRequest as ExamRequest;

    const [client, setClient] = useState<Client>();
    const [exams, setExams] = useState<Exam[]>([]);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState<string | null>(null);

    useEffect(() => {
        const fetchClientAndExams = async () => {
            try {
                if (!examRequest) {
                    throw new Error('ExamRequest not provided');
                }

                // Fetch client details
                const clientResponse = await fetch(`http://localhost:8080/clients/${examRequest.clientId}`);
                if (!clientResponse.ok) {
                    throw new Error(`Failed to fetch client: ${clientResponse.status}`);
                }
                const clientData = await clientResponse.json();
                setClient(clientData);

                // Fetch details of each test in examTestList
                const examTestsResponse = await Promise.all(
                    examRequest.examTestList.map(async (testId) => {
                        console.log(testId)
                        const testResponse = await fetch(`http://localhost:8080/exams-tests/${testId}`);
                        if (!testResponse.ok) {
                            throw new Error(`Failed to fetch test: ${testResponse.status}`);
                        }
                        return testResponse.json();
                    })
                );

                // Extract related exam IDs from the tests
                const relatedExamIds = examTestsResponse.map((test) => test.examId);

                // Fetch details for each exam
                const examsData = await Promise.all(
                    relatedExamIds.map(async (examId) => {
                        console.log(examId)
                        const examResponse = await fetch(`http://localhost:8080/exams/${examId}`);
                        if (!examResponse.ok) {
                            throw new Error(`Failed to fetch exam: ${examResponse.status}`);
                        }
                        return examResponse.json();
                    })
                );

                setExams(examsData);
            } catch (err: unknown) {
                if (err instanceof Error) {
                    console.error('Error:', err.message);
                    setError(err.message);
                } else {
                    console.error('Unexpected error occurred.');
                    setError('An unexpected error occurred.');
                }
            } finally {
                setLoading(false);
            }
        };

        fetchClientAndExams();
    }, [examRequest]);

    if (loading) {
        return <p>Loading...</p>;
    }

    if (error) {
        return <p className="text-red-500">{error}</p>;
    }
    const handleFinalize = async () => {
        if (!examRequest) {
            console.error('ExamRequest is not defined.');
            return;
        }
        navigate('/paymentmethod', { state: { examRequest } });
    };
    return (
        <div className="p-8 bg-gray-100 h-screen">
            {/* Header */}
            <header className="mb-6 flex items-center justify-between">
                <img src="/assets/blab.png" alt="Blab Logo" className="w-12 h-12 mr-4" />
                <h1 className="text-2xl font-semibold text-left">Exam Request</h1>
                <button className="bg-orange-500 text-white p-0 w-8 h-8 rounded-full flex items-center justify-center">
                    A
                </button>
            </header>

            <div className="flex">
                <div className="flex h-screen overflow-x-hidden">
                    <SideBar />
                </div>

                <div className="flex flex-col w-full">
                    {/* Client and Exam Request Info */}
                    <div className="grid grid-cols-2 gap-4 mb-8">
                        {/* Client Info */}
                        <div className="bg-white p-6 rounded-lg shadow-md">
                            <h2 className="text-lg font-medium mb-2">Client</h2>
                            <p className="text-gray-700">{client?.name}</p>
                            <p className="text-gray-700">{client?.birthDate}</p>
                            <p className="text-gray-700">{client?.contactEmail}</p>
                        </div>

                        {/* Exam Request Info */}
                        <div className="bg-white p-6 rounded-lg shadow-md">
                            <h2 className="text-lg font-medium mb-2">Exams Request</h2>
                            <p className="text-gray-700">{examRequest.examRequestId}</p>
                            <p className="text-gray-700">{examRequest.requestDate}</p>
                        </div>
                    </div>


                    {/* Exams Table */}
                    <div className="bg-white p-6 rounded-lg shadow-md">
                        <h2 className="text-lg font-medium mb-4">Exams</h2>
                        <table className="w-full border-collapse border border-gray-300 text-left">
                            <thead>
                            <tr className="bg-gray-200">
                                <th className="border border-gray-300 p-2">ID</th>
                                <th className="border border-gray-300 p-2">Exams Name</th>
                                <th className="border border-gray-300 p-2">Requirements</th>
                                <th className="border border-gray-300 p-2">Price</th>
                                <th className="border border-gray-300 p-2">Stipulated Time</th>
                            </tr>
                            </thead>
                            <tbody>
                            {exams.map((exam, index) => (
                                <tr key={`${exam.id}-${index}`}>
                                    <td className="border border-gray-300 p-2">{exam.id}</td>
                                    <td className="border border-gray-300 p-2">{exam.name}</td>
                                    <td className="border border-gray-300 p-2">{exam.requirements}</td>
                                    <td className="border border-gray-300 p-2">${exam.price?.toFixed(2)}</td>
                                    <td className="border border-gray-300 p-2">{exam.analysisTime} hours</td>
                                </tr>
                            ))}
                            </tbody>
                        </table>
                    </div>

                    {/* Footer */}
                    <div className="flex justify-end mt-8">
                        <button className="bg-blue-600 text-white px-6 py-2 rounded" onClick={handleFinalize}>
                            Checkout
                        </button>
                    </div>
                </div>
            </div>
        </div>
    );
};

export default ClientList;
