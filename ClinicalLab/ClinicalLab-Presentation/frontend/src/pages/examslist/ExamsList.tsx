// Import necessary dependencies
import React from 'react';

const ExamsDisplay: React.FC = () => {
    return (
        <div className="flex flex-col h-screen bg-gray-100 p-8">
            {/* Header */}
            <header className="flex justify-between items-center mb-8">
                <button className="mr-4 text-orange-500" onClick={() => alert("Back to previous screen")}>
                    &larr; Back
                </button>
                <h1 className="text-2xl font-semibold">Clinical Lab Exams</h1>
                

                <div className="relative w-1/3">
                    {/* Search and Filters */}
                    <input
                        type="text"
                        className="flex-1 border border-gray-300 rounded px-4 py-2 focus:outline-none focus:ring focus:ring-orange-500"
                        placeholder="Search for Clinical Lab Exams, Name or ID"
                    />
                    <button className="absolute inset-y-0 right-0 flex items-center px-3">
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
            </header>

            {/* Exam List */}
            <div className="bg-white rounded shadow-md overflow-auto">
                <table className="w-full table-auto text-left">
                    <thead className="bg-gray-200 text-gray-700">
                        <tr>
                            <th className="px-4 py-2">
                                <input type="checkbox" />
                            </th>
                            <th className="px-4 py-2">Code</th>
                            <th className="px-4 py-2">Exam Name</th>
                            <th className="px-4 py-2">Requirements</th>
                            <th className="px-4 py-2">Price</th>
                            <th className="px-4 py-2">Stipulated Time</th>
                        </tr>
                    </thead>
                    <tbody>
                        {/* Placeholder rows; replace with dynamic data */}
                        <tr className="hover:bg-gray-100">
                            <td className="px-4 py-2">
                                <input type="checkbox" />
                            </td>
                            <td className="px-4 py-2">E001</td>
                            <td className="px-4 py-2">Blood Test</td>
                            <td className="px-4 py-2">Fasting</td>
                            <td className="px-4 py-2">$30</td>
                            <td className="px-4 py-2">24 hours</td>
                        </tr>
                        <tr className="hover:bg-gray-100">
                            <td className="px-4 py-2">
                                <input type="checkbox" />
                            </td>
                            <td className="px-4 py-2">E002</td>
                            <td className="px-4 py-2">X-Ray</td>
                            <td className="px-4 py-2">None</td>
                            <td className="px-4 py-2">$50</td>
                            <td className="px-4 py-2">48 hours</td>
                        </tr>
                        {/* Additional rows as needed */}
                    </tbody>
                </table>
            </div>

            {/* Done Button */}
            <div className="mt-6">
                <button
                    type="button"
                    className="w-full bg-orange-500 text-white px-4 py-2 rounded mt-6"
                >
                    Done
                </button>

            </div>
        </div>
    );
};

export default ExamsDisplay;