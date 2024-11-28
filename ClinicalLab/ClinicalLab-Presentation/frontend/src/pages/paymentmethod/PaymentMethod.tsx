import React, { useEffect, useState } from 'react';
import { useLocation } from 'react-router-dom';
import SideBar from '../../components/SideBar';
import { ExamRequest } from '../../model/examRequestModel';
import { Client } from '../../model/clientModel';

const BillingInfo: React.FC = () => {
    const location = useLocation();
    const examRequest: ExamRequest | undefined = location.state?.examRequest;

    const [client, setClient] = useState<Client | null>(null);
    const [totalAmount, setTotalAmount] = useState<number | null>(null);
    const [selectedPaymentMethod, setSelectedPaymentMethod] = useState<string>('');
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState<string | null>(null);

    useEffect(() => {
        const fetchClientAndPrice = async () => {
            try {
                if (!examRequest) {
                    throw new Error('Exam request is missing.');
                }

                // Fetch client details
                const clientResponse = await fetch(`http://localhost:8080/clients/${examRequest.clientId}`);
                if (!clientResponse.ok) {
                    throw new Error(`Failed to fetch client: ${clientResponse.statusText}`);
                }
                const clientBody = await clientResponse.json();
                setClient(clientBody);

                // Fetch total price
                const priceResponse = await fetch(
                    `http://localhost:8080/exam-requests/${examRequest.examRequestId}/calculate-price`
                );
                if (!priceResponse.ok) {
                    throw new Error(`Failed to calculate total price: ${priceResponse.statusText}`);
                }
                const priceBody = await priceResponse.text(); // Fetch response as text
                const parsedPrice = parseFloat(priceBody); // Parse the price as a float

                if (isNaN(parsedPrice)) {
                    throw new Error('Invalid total price received from backend.');
                }
                setTotalAmount(parsedPrice); // Set the parsed price as totalAmount
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

        fetchClientAndPrice();
    }, [examRequest]);

    const handlePaymentMethodChange = (event: React.ChangeEvent<HTMLSelectElement>) => {
        setSelectedPaymentMethod(event.target.value);
    };

    const handleUpdateExamRequest = async () => {
        if (!examRequest || !selectedPaymentMethod || totalAmount === null) {
            console.error('Cannot update ExamRequest: Missing data.');
            return;
        }

        const updatedExamRequest = {
            ...examRequest,
            totalPrice: totalAmount, // Set totalPrice with the calculated totalAmount
            paymentMethod: selectedPaymentMethod,
            status: 'Waiting for collection',
        };

        try {
            const response = await fetch('http://localhost:8080/exam-requests', {
                method: 'PUT',
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify(updatedExamRequest),
            });

            if (!response.ok) {
                throw new Error(`Failed to update ExamRequest: ${response.statusText}`);
            }

            const updatedData = await response.json();
            console.log('Updated ExamRequest:', updatedData);
            alert('Exam request updated successfully!');
        } catch (err: unknown) {
            console.error('Error updating ExamRequest:', err);
            alert('Failed to update ExamRequest. Please try again.');
        }
    };

    if (loading) {
        return <p>Loading...</p>;
    }

    if (error) {
        return <p className="text-red-500">{error}</p>;
    }

    return (
        <div className="p-8 bg-gray-100 h-screen">
            {/* Header */}
            <header className="mb-6 flex items-center justify-between">
                <img src="/assets/blab.png" alt="Blab Logo" className="w-auto h-12 mr-4" />
                <h1 className="text-2xl font-semibold text-left">Billing Information</h1>
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
                            {client && (
                                <>
                                    <p className="text-gray-700">{client.name}</p>
                                    <p className="text-gray-700">{client.birthDate}</p>
                                    <p className="text-gray-700">{client.contactEmail}</p>
                                </>
                            )}
                        </div>

                        {/* Exam Request Info */}
                        <div className="bg-white p-6 rounded-lg shadow-md">
                            <h2 className="text-lg font-medium mb-2">Exams Request</h2>
                            {examRequest && (
                                <>
                                    <p className="text-gray-700">ID: {examRequest.examRequestId}</p>
                                    <p className="text-gray-700">Date: {examRequest.requestDate}</p>
                                </>
                            )}
                        </div>
                    </div>

                    {/* Total Amount */}
                    <div className="bg-white p-6 rounded-lg shadow-md mb-8">
                        <h2 className="text-lg font-medium mb-2">Total Amount</h2>
                        <p className="text-2xl font-bold text-gray-800">
                            {totalAmount !== null ? `$${totalAmount.toFixed(2)}` : 'Calculating...'}
                        </p>
                    </div>

                    {/* Financial Section */}
                    {totalAmount !== null && (
                        <div className="bg-white p-6 rounded-lg shadow-md mb-8">
                            <h2 className="text-lg font-medium mb-2">Financial</h2>
                            <p className="text-gray-600 mb-4">
                                The service will be paid by health insurance or private payment.
                            </p>
                            <select
                                className="w-full border border-gray-300 rounded px-3 py-2 focus:outline-none focus:ring focus:ring-blue-300"
                                onChange={handlePaymentMethodChange}
                                value={selectedPaymentMethod}
                            >
                                <option value="">Select Payment Method</option>
                                <option value="Credit Card">Credit Card</option>
                                <option value="Debit Card">Debit Card</option>
                                <option value="Health Insurance">Health Insurance</option>
                                <option value="Cash">Cash</option>
                            </select>
                        </div>
                    )}

                    {/* Footer with Submit Button */}
                    {totalAmount !== null && (
                        <div className="flex justify-end">
                            <button
                                className="bg-blue-600 text-white px-6 py-2 rounded"
                                onClick={handleUpdateExamRequest}
                                disabled={!selectedPaymentMethod}
                            >
                                Confirm Payment
                            </button>
                        </div>
                    )}
                </div>
            </div>
        </div>
    );
};

export default BillingInfo;
