
import React from 'react';
//import { Link } from 'react-router-dom';
import SideBar from '../../components/SideBar';

const ClientList: React.FC = () => {
    return (
        <div className="p-8 bg-gray-100 h-screen">
            {/* Header */}
            <header className="mb-6 flex items-center justify-between">
                <img src="/assets/blab.png" alt="Blab Logo" className="w-12 h-12 mr-4" />
                <h1 className="text-2xl font-semibold text-left">Numbers</h1>
                <button className="bg-orange-500 text-white p-2 rounded-full">
                        A
                </button>
            </header>
            
            <div className="flex">
                <div className="flex h-screen overflow-x-hidden">
                    <SideBar />
                </div>

                <div className="flex flex-col w-full">               
                    {/* Filters */}
                    <div className="flex justify-between items-center bg-white p-4 shadow-sm">

                        <div className="flex gap-2 items-center">
                            <input
                                type="text"
                                placeholder="Search for Numbers or priority"
                                className="border border-gray-300 rounded-lg px-4 py-2 w-80 focus:outline-none focus:ring focus:ring-orange-300"
                            />
                            <button className="bg-white-500 border-orange text-orange px-4 py-2 rounded-lg">
                                <img src="/assets/loupe.png" alt="Search"  className="w-5 h-5" />
                            </button>
                            
                        </div>
                        <div className="flex gap-4">
                            <select className="border border-gray-300 rounded-lg px-4 py-2 focus:outline-none focus:ring focus:ring-orange-300">
                                <option>Quantity: All</option>
                                <option>1-5</option>
                                <option>6-10</option>
                                <option>More than 10</option>
                            </select>
                            <select className="border border-gray-300 rounded-lg px-4 py-2 focus:outline-none focus:ring focus:ring-orange-300">
                                <option>Status: All</option>
                                <option>Next</option>
                                <option>Waiting</option>
                                <option>Done</option>
                            </select>
                            <select className="border border-gray-300 rounded-lg px-4 py-2 focus:outline-none focus:ring focus:ring-orange-300">
                                <option>Priority: All</option>
                                <option>Priority</option>
                                <option>Standard</option>
                            </select>
                            <select className="border border-gray-300 rounded-lg px-4 py-2 focus:outline-none focus:ring focus:ring-orange-300">
                                <option>Time Stamp: All</option>
                                <option>Newest</option>
                                <option>Oldest</option>
                            </select>
                        </div>
                    </div>

                    {/* Priority and Standard Sections */}
                    <div className="p-6 space-y-8 bg-white rounded-lg shadow-md">

                        {/* Priority Section */}
                        <div>
                            <h2 className="text-lg font-semibold mb-4">PRIORITY</h2>
                            <div className="grid grid-cols-3 gap-6">
                                <div className="bg-orange-100 border-l-4 border-orange-500 p-4 rounded-lg">
                                    <p className="text-gray-700 font-medium">Number</p>
                                    <p className="text-orange-500 font-bold text-lg">P0023</p>
                                    <p className="text-gray-500 text-sm">Next</p>
                                    <p className="text-gray-500 text-sm">06:44:12</p>
                                </div>
                                <div className="bg-white border border-gray-300 p-4 rounded-lg">
                                    <p className="text-gray-700 font-medium">Number</p>
                                    <p className="text-orange-500 font-bold text-lg">P0024</p>
                                    <p className="text-gray-500 text-sm">Waiting</p>
                                    <p className="text-gray-500 text-sm">06:44:12</p>
                                </div>
                                <div className="bg-gray-200 border border-gray-300 p-4 rounded-lg">
                                    <p className="text-gray-700 font-medium">Number</p>
                                    <p className="text-orange-500 font-bold text-lg">P0025</p>
                                    <p className="text-gray-500 text-sm">Done</p>
                                    <p className="text-gray-500 text-sm">06:44:12</p>
                                </div>
                            </div>
                        </div>

                        {/* Standard Section */}
                        <div>
                            <h2 className="text-lg font-semibold mb-4">STANDARD</h2>
                            <div className="grid grid-cols-3 gap-6">
                                {[...Array(6)].map((_, index) => (
                                    <div key={index} className="bg-white border border-gray-300 p-4 rounded-lg">
                                        <p className="text-gray-700 font-medium">Number</p>
                                        <p className="text-orange-500 font-bold text-lg">P0033</p>
                                        <p className="text-gray-500 text-sm">Waiting</p>
                                        <p className="text-gray-500 text-sm">06:44:12</p>
                                    </div>
                                ))}
                            </div>
                        </div>
                    </div>
                </div>

            </div>
        </div>

    );
};

export default ClientList;