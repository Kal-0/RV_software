import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import SideBar from '../../components/SideBar';

// Função para formatar a data no padrão YYYY-MM-DD
const formatDate = (date: string) => {
    const [year, month, day] = date.split('-');
    return `${year}-${month}-${day}`;
};

// Função para remover máscara do CPF
const unmaskCPF = (cpf: string) => {
    return cpf.replace(/\D/g, ''); // Remove todos os caracteres não numéricos
};

const ClientList: React.FC = () => {
    const navigate = useNavigate();

    const [formData, setFormData] = useState({
        cpf: '',
        name: '',
        birthdate: '',
        email: '',
    });

    const [loading, setLoading] = useState(false);
    const [error, setError] = useState<string | null>(null);

    const handleInputChange = (e: React.ChangeEvent<HTMLInputElement>) => {
        const { id, value } = e.target;
        setFormData((prev) => ({ ...prev, [id]: value }));
    };

    const handleChooseExams = async () => {
        setLoading(true);
        setError(null);

        try {
            // Formata os dados conforme o esperado pelo backend
            const formattedData = {
				id: null,
                cpf: unmaskCPF(formData.cpf), // Remove a máscara do CPF
                contactEmail: formData.email,
                name: formData.name,
                birthDate: formatDate(formData.birthdate), // Converte a data para o formato americano
            };
            console.log('Sending data:', formattedData);

            const response = await fetch('http://localhost:8080/clients', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify(formattedData),
            });
            console.log('Response status:', response.status);

            if (!response.ok) {
                throw new Error(`HTTP error! status: ${response.status}`);
            }


            const data = await response.json();
            console.log('Client saved:', data);
            alert('Client saved successfully!');

            // Navega para a página de exames após salvar o cliente
            navigate('/examslist', { state: { client: data } });
        }catch (err: unknown) {
            console.error('Error saving client:', err);

            // Verifica se o erro é uma instância de Error
            if (err instanceof Error) {
                setError(err.message); // Acessa a mensagem do erro
            } else {
                setError('An unexpected error occurred.'); // Mensagem genérica para erros não previstos
            }
        } finally {
            setLoading(false);
        }
    };

    return (
        <div className="p-8 bg-gray-100 h-screen">
            {/* Header */}
            <header className="mb-6 flex items-center justify-between">
                <img src="/assets/blab.png" alt="Blab Logo" className="w-12 h-12 mr-4" />
                <h1 className="text-2xl font-semibold text-left">Client</h1>
                <button className="bg-orange-500 text-white p-2 rounded-full">A</button>
            </header>

            <div className="flex">
                <div className="flex h-screen overflow-x-hidden">
                    <SideBar />
                </div>

                <div className="flex flex-col w-full">
                    <div className="bg-white p-8 rounded-lg shadow-md">
                        <div className="flex items-center justify-between mb-6">
                            <div>
                                <span className="text-lg font-medium">Number</span>
                                <p className="text-orange-500 font-bold text-xl">P0023</p>
                            </div>
                        </div>

                        <form className="space-y-6" onSubmit={(e) => e.preventDefault()}>
                            <fieldset>
                                <legend className="text-lg font-semibold">General</legend>
                                <p className="text-gray-500 mb-4">
                                    Basic information you need to fill in to register or validate
                                </p>

                                <div className="space-y-4">
                                    <div>
                                        <label htmlFor="cpf" className="block text-gray-700 font-medium">
                                            CPF *
                                        </label>
                                        <input
                                            type="text"
                                            id="cpf"
                                            value={formData.cpf}
                                            onChange={handleInputChange}
                                            className="w-full border border-gray-300 rounded px-3 py-2 mt-1 focus:outline-none focus:ring focus:ring-orange-300"
                                            placeholder="Enter CPF"
                                        />
                                    </div>

                                    <div>
                                        <label htmlFor="name" className="block text-gray-700 font-medium">
                                            Name *
                                        </label>
                                        <input
                                            type="text"
                                            id="name"
                                            value={formData.name}
                                            onChange={handleInputChange}
                                            className="w-full border border-gray-300 rounded px-3 py-2 mt-1 focus:outline-none focus:ring focus:ring-orange-300"
                                            placeholder="Enter Name"
                                        />
                                    </div>

                                    <div>
                                        <label htmlFor="birthdate" className="block text-gray-700 font-medium">
                                            Birth Date *
                                        </label>
                                        <input
                                            type="date"
                                            id="birthdate"
                                            value={formData.birthdate}
                                            onChange={handleInputChange}
                                            className="w-full border border-gray-300 rounded px-3 py-2 mt-1 focus:outline-none focus:ring focus:ring-orange-300"
                                        />
                                    </div>

                                    <div>
                                        <label htmlFor="email" className="block text-gray-700 font-medium">
                                            Email *
                                        </label>
                                        <input
                                            type="email"
                                            id="email"
                                            value={formData.email}
                                            onChange={handleInputChange}
                                            className="w-full border border-gray-300 rounded px-3 py-2 mt-1 focus:outline-none focus:ring focus:ring-orange-300"
                                            placeholder="Enter Email"
                                        />
                                    </div>
                                </div>
                            </fieldset>

                            <button

                                type="button"
                                onClick={handleChooseExams}
                                className="w-full bg-orange-500 text-white px-4 py-2 rounded mt-6"
                                disabled={loading}

                            >
                                {loading ? 'Saving...' : 'Choose Exams'}
                            </button>
                        </form>

                        {error && <p className="text-red-500 mt-4">Error: {error}</p>}

                    </div>
                </div>
            </div>
        </div>
    );
};

export default ClientList;
