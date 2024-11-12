// Import necessary dependencies
import React from 'react';

const ClientScreen: React.FC = () => {
    return (
        <div className="flex h-screen bg-gray-100">
            {/* Main Content */}
            <div className="flex-1 p-8">
                <header className="flex justify-between items-center mb-8">
                    <h1 className="text-2xl font-semibold">Client</h1>
                    <button className="bg-orange-500 text-white p-2 rounded-full">
                        A
                    </button>
                </header>

                <div className="bg-white p-8 rounded-lg shadow-md">
                    <div className="flex items-center justify-between mb-6">
                        <div>
                            <span className="text-lg font-medium">Number</span>
                            <p className="text-orange-500 font-bold text-xl">P0023</p>
                        </div>
                        <button className="bg-orange-500 text-white px-4 py-2 rounded">
                            Call Next Number
                        </button>
                    </div>

                    <form className="space-y-6">
                        <fieldset>
                            <legend className="text-lg font-semibold">General</legend>
                            <p className="text-gray-500 mb-4">Basic information you need to fill in to register or validate</p>

                            <div className="space-y-4">
                                <div>
                                    <label htmlFor="cpf" className="block text-gray-700 font-medium">CPF *</label>
                                    <input
                                        type="text"
                                        id="cpf"
                                        className="w-full border border-gray-300 rounded px-3 py-2 mt-1 focus:outline-none focus:ring focus:ring-orange-300"
                                        placeholder="Enter CPF"
                                    />
                                </div>

                                <div>
                                    <label htmlFor="name" className="block text-gray-700 font-medium">Name *</label>
                                    <input
                                        type="text"
                                        id="name"
                                        className="w-full border border-gray-300 rounded px-3 py-2 mt-1 focus:outline-none focus:ring focus:ring-orange-300"
                                        placeholder="Enter Name"
                                    />
                                </div>

                                <div>
                                    <label htmlFor="birthdate" className="block text-gray-700 font-medium">Birth Date *</label>
                                    <input
                                        type="date"
                                        id="birthdate"
                                        className="w-full border border-gray-300 rounded px-3 py-2 mt-1 focus:outline-none focus:ring focus:ring-orange-300"
                                    />
                                </div>

                                <div>
                                    <label htmlFor="email" className="block text-gray-700 font-medium">Email *</label>
                                    <input
                                        type="email"
                                        id="email"
                                        className="w-full border border-gray-300 rounded px-3 py-2 mt-1 focus:outline-none focus:ring focus:ring-orange-300"
                                        placeholder="Enter Email"
                                    />
                                </div>
                            </div>
                        </fieldset>

                        <button
                            type="button"
                            className="w-full bg-orange-500 text-white px-4 py-2 rounded mt-6"
                        >
                            Choose Exams
                        </button>
                    </form>
                </div>
            </div>
        </div>
    );
};

export default ClientScreen;
