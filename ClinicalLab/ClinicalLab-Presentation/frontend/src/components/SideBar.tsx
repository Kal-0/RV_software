import React from 'react';
import { Link } from 'react-router-dom';

const SideBar: React.FC = () => {
    return (
        <aside className="w-16 h-full border-r border-gray-200 flex flex-col items-center py-4">
            <div className="flex h-screen flex-col ">
                {/* Home */}
                <Link to="/" className="p-2 hover:bg-gray-100 rounded">
                    <img src="/assets/home.png" alt="Home" className="w-6 h-6" />
                </Link>

                {/* Clients */}
                <Link to="/listclient" className="p-2 hover:bg-gray-100 rounded">
                    <img src="/assets/clients.png" alt="Clients" className="w-6 h-6" />
                </Link>

                {/* Waiting List */}
                <Link to="/examrequest" className="p-2 hover:bg-gray-100 rounded">
                    <img src="/assets/waiting_list.png" alt="Waiting List" className="w-6 h-6" />
                </Link>


                 {/* Exams */}
                 <Link to="/examslist" className="p-2 hover:bg-gray-100 rounded">
                    <img src="/assets/exams.png" alt="Exams" className="w-6 h-6" />
                </Link>

                {/* Service Number */}
                <Link to="/servicenumber" className="p-2 hover:bg-gray-100 rounded">
                    <img src="/assets/number.png" alt="Number" className="w-6 h-6" />
                </Link>

            </div>
        </aside>
    );
};

export default SideBar;
