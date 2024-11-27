
import React from 'react';
import { Link } from 'react-router-dom';
import SideBar from '../../components/SideBar';

const ClientList: React.FC = () => {
    return (
        <div className="p-8 bg-gray-100 h-screen">
            {/* Header */}
            <header className="mb-6 flex items-center justify-between">
                <img src="/assets/blab.png" alt="Blab Logo" className="w-12 h-12 mr-4" />
                <h1 className="text-2xl font-semibold text-left">Exam Request</h1>
                <button className="bg-orange-500 text-white p-2 rounded-full">
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
                    <p className="text-gray-700">Eduardo C. Silva</p>
                    <p className="text-gray-700">April 17, 2001</p>
                    <p className="text-gray-700">eduardocsilva@email.com</p>
                </div>

                {/* Exam Request Info */}
                <div className="bg-white p-6 rounded-lg shadow-md">
                    <h2 className="text-lg font-medium mb-2">Exams Request</h2>
                    <p className="text-gray-700">2165216241</p>
                    <p className="text-gray-700">Request Date</p>
                </div>
            </div>

             <div className="flex justify-end mt-8">    
                <Link to="/examslist" className="bg-orange-500 text-white px-4 py-2 rounded">
                    <button className="bg-orange-500 text-white px-4 py-2 rounded">
                        Update Exams Requests
                    </button>
                </Link>
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
                        <tr>
                            <td className="border border-gray-300 p-2">1</td>
                            <td className="border border-gray-300 p-2">Urine tests</td>
                            <td className="border border-gray-300 p-2">-</td>
                            <td className="border border-gray-300 p-2">$60.00</td>
                            <td className="border border-gray-300 p-2">2 days</td>
                        </tr>
                        <tr>
                            <td className="border border-gray-300 p-2">2</td>
                            <td className="border border-gray-300 p-2">Glucose</td>
                            <td className="border border-gray-300 p-2">Fast of 12 hours</td>
                            <td className="border border-gray-300 p-2">$20.00</td>
                            <td className="border border-gray-300 p-2">4 days</td>
                        </tr>
                        <tr>
                            <td className="border border-gray-300 p-2">3</td>
                            <td className="border border-gray-300 p-2">Stool exams</td>
                            <td className="border border-gray-300 p-2">-</td>
                            <td className="border border-gray-300 p-2">$60.00</td>
                            <td className="border border-gray-300 p-2">1 day</td>
                        </tr>
                        <tr>
                            <td className="border border-gray-300 p-2">4</td>
                            <td className="border border-gray-300 p-2">C-reactive protein</td>
                            <td className="border border-gray-300 p-2">-</td>
                            <td className="border border-gray-300 p-2">$100.00</td>
                            <td className="border border-gray-300 p-2">1 day</td>
                        </tr>
                        <tr>
                            <td className="border border-gray-300 p-2">5</td>
                            <td className="border border-gray-300 p-2">T3, T4, TSH</td>
                            <td className="border border-gray-300 p-2">-</td>
                            <td className="border border-gray-300 p-2">$200.00</td>
                            <td className="border border-gray-300 p-2">2 days</td>
                        </tr>
                    </tbody>
                </table>
            </div>

            {/* Footer */}
            <div className="flex justify-end mt-8">
                <Link to="/paymentmethod" className="bg-blue-600 text-white px-6 py-2 rounded">
                    <button className="bg-blue-600 text-white px-6 py-2 rounded">
                        Checkout
                    </button>
                </Link>
            </div>

                </div>

            </div>
        </div>

    );
};

export default ClientList;