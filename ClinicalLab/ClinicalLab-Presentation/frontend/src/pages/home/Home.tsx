import SideBar from '../../components/SideBar';
import { Link } from 'react-router-dom';
/*import styles from "./Home.css"; */

function Home() {
    return (
        <div className="flex flex-col h-screen">
            {/* Header */}
            <header className="mb-6 flex items-center justify-between">
                <img src="/assets/blab.png" alt="Blab Logo" className="w-auto h-12 mr-4" />
                <h1 className="text-2xl font-semibold text-left">Lab Exams</h1>
                <button className="bg-orange-500 text-white p-0 w-8 h-8 rounded-full flex items-center justify-center">
                    A
                </button>
            </header>

            <div className="flex">
                <div className="flex h-screen overflow-x-hidden">
                    <SideBar />
                </div>

                <div className="flex flex-col w-full">
                    {/* Button */}
                    <div className="flex justify-center mt-8">
                        <Link to="/registerclient" className="text-orange-500 font-medium">
                            <button
                                type="button"
                                className="w-full bg-orange-500 text-white px-4 py-2 rounded mt-6"
                            >
                                Call Next Number
                            </button>
                        </Link>
                    </div>
                </div>
            </div>
        </div>
    );
}

export default Home;
