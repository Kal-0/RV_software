
import React from 'react';
import { Link } from 'react-router-dom';
import SideBar from '../../components/SideBar';

const ClientList: React.FC = () => {
    return (
        <div className="p-8 bg-gray-100 h-screen">
            {/* Header */}
            <header className="mb-6 flex items-center justify-between">
                <img src="/assets/blab.png" alt="Blab Logo" className="w-12 h-12 mr-4" />
                <h1 className="text-2xl font-semibold text-left">Laboratory Exams</h1>
                <button className="bg-orange-500 text-white p-2 rounded-full">
                        A
                </button>
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
            <div className="flex justify-end mt-8">
                <Link to="/examrequest" className="text-orange-500 font-medium">
                    <button
                        type="button"
                        className="w-full bg-orange-500 text-white px-4 py-2 rounded mt-6"
                    >
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