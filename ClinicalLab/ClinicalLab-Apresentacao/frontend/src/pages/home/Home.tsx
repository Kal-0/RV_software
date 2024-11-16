import SideBar from '../../components/SideBar';
/*import styles from "./Home.css"; */

function Home() {
    return (
        <div className="flex flex-col h-screen">
            {/* Navbar */}
            <header className="bg-white shadow p-4 flex items-center">
                <img src="/assets/blab.png" alt="Blab Logo" className="w-12 h-12 mr-4" />
            </header>

            <div className="flex h-screen overflow-x-hidden">
                <SideBar />
            </div>

            <main className="flex-1 p-8 bg-gray-50">
                <div>
                    <h2 className="text-xl font-semibold text-gray-700">Welcome back!</h2>
                    <p className="text-gray-600">Here’s an overview of today’s activity.</p>
                </div>
            </main>
        </div>
    );
}

export default Home;
