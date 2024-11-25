// Import necessary dependencies
import React from 'react';

const PaymentPage: React.FC = () => {
    return (
        <div className="flex flex-col h-screen bg-gray-100 p-8">
            {/* Header */}
            <header className="mb-8">
                <h1 className="text-2xl font-semibold">Billing Information</h1>
            </header>

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

            {/* Total Amount */}
            <div className="bg-white p-6 rounded-lg shadow-md mb-8">
                <h2 className="text-lg font-medium mb-2">Total Amount</h2>
                <p className="text-2xl font-bold text-gray-800">$1000.00</p>
            </div>

            {/* Financial Section */}
            <div className="bg-white p-6 rounded-lg shadow-md mb-8">
                <h2 className="text-lg font-medium mb-2">Financial</h2>
                <p className="text-gray-600 mb-4">
                    The service will be paid by health insurance or private payment.
                </p>
                <select
                    className="w-full border border-gray-300 rounded px-3 py-2 focus:outline-none focus:ring focus:ring-blue-300"
                >
                    <option value="">Payment Methods</option>
                    <option value="credit-card">Credit Card</option>
                    <option value="debit-card">Debit Card</option>
                    <option value="insurance">Health Insurance</option>
                    <option value="cash">Cash</option>
                </select>
            </div>

            {/* Footer with Submit Button */}
            <div className="flex justify-end">
                <button className="bg-blue-600 text-white px-6 py-2 rounded">
                    Done
                </button>
            </div>
        </div>
    );
};

export default PaymentPage;
