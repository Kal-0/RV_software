import React from 'react';

const ClientList: React.FC = () => {

    const clients = [
        { cpf: "111.000.111-00", name: "Eduardo C. Silva", birthDate: "April 05, 2022", email: "ecs@ecs.com" },
        { cpf: "333.222.000-09", name: "Ana P. Lima", birthDate: "March 2, 1997", email: "apl@apl.com" },
        { cpf: "999.222.222-11", name: "Gabriel L. Silva", birthDate: "June 6, 1967", email: "gls@gls.com" },
        { cpf: "333.222.222-11", name: "Ricardo Toledo", birthDate: "July 21, 2019", email: "rt@rt.com" },
        { cpf: "999.000.222-00", name: "Maria Marques", birthDate: "January 11, 2004", email: "mm@mm.com" },
    ];

    return (
        <div className="p-8 bg-gray-100 h-screen">
            {/* Header */}
            <header className="mb-6 flex items-center justify-between">
                <h1 className="text-2xl font-semibold text-left">Clients</h1>
                <button className="bg-orange-500 text-white p-2 rounded-full">
                        A
                </button>


            </header>

            {/* Search and Filters */}
            <div className="mb-6 flex items-center gap-4">
                <input
                    type="text"
                    placeholder="Search for Name, CPF, Birth Date or ID"
                    className="flex-1 border border-gray-300 rounded px-4 py-2 focus:outline-none focus:ring focus:ring-orange-300"
                />
                <button className="bg-white-500 text-white px-4 py-2 rounded">
                    <img src="/assets/loupe.png" alt="Search" className="w-5 h-5" />
                
                </button>
            </div>
            <div className="flex gap-4 mb-6">
                <button className="bg-orange-500 text-white px-4 py-2 rounded">
                    Quantity: All
                </button>
                <button className="bg-orange-500 text-white px-4 py-2 rounded">
                    Quantity of Exams Requests: All
                </button>
            </div>

            {/* Table */}
            <table className="w-full bg-white rounded-lg shadow-md">
                <thead className="bg-gray-200">
                    <tr>
                        <th className="text-left py-2 px-4 text-gray-700">CPF</th>
                        <th className="text-left py-2 px-4 text-gray-700">Client</th>
                        <th className="text-left py-2 px-4 text-gray-700">Birth Date</th>
                        <th className="text-left py-2 px-4 text-gray-700">Email</th>
                    </tr>
                </thead>
                <tbody>
                    {clients.map((client, index) => (
                        <tr
                            key={index}
                            className={`border-t ${index % 2 === 0 ? "bg-gray-50" : "bg-white"}`}
                        >
                            <td className="py-2 px-4">{client.cpf}</td>
                            <td className="py-2 px-4 text-orange-500 font-medium">
                                {client.name}
                            </td>
                            <td className="py-2 px-4">{client.birthDate}</td>
                            <td className="py-2 px-4">{client.email}</td>
                        </tr>
                    ))}
                </tbody>
            </table>
        </div>
    );
};

export default ClientList;
