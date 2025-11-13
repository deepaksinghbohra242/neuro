import { MdOutlineEdit } from "react-icons/md";

const MedicalOverviewCard = () => {
  return (
    <div className="bg-gray-50 rounded-lg shadow-sm p-4 sm:p-6 border border-gray-100">
      <div className="flex justify-between items-center mb-4">
        <h3 className="border-l-4 pl-3 text-lg font-bold text-gray-900" style={{ borderColor: "#5B3CA1" }}>
          MEDICAL OVERVIEW <span className="text-gray-500 text-sm font-normal">・ Last Updated: 12 Dec, 2023</span>
        </h3>
        <button className="flex items-center text-indigo-400 text-sm font-medium hover:text-indigo-700 transition-colors duration-200 border border-gray-300 px-3 py-1 rounded-lg gap-1 bg-white">
          <MdOutlineEdit/>
          Edit
        </button>
      </div>

      {/* Metric Charts Section */}
      <div className="grid grid-cols-1 lg:grid-cols-3 gap-6 mb-8">
        {/* CREYOS Score */}
        <div className="p-4 bg-white rounded-2xl border border-gray-200">
          <div className="flex justify-between items-center mb-2">
            <p className="text-sm font-medium text-gray-700">CREYOS SCORE</p>
            <span className="text-green-500 font-bold text-xs">●</span> {/* Using a circle for neutral/good */}
          </div>
          {/* Placeholder SVG for CREYOS Score Chart */}
          <svg viewBox="0 0 200 60" className="w-full" fill="none" xmlns="http://www.w3.org/2000/svg">
            <path d="M0 40 C 20 50, 40 30, 60 40 C 80 55, 100 35, 120 45 C 140 30, 160 50, 180 40" stroke="#34D399" strokeWidth="2" fill="none"/>
            <path d="M0 45 C 20 55, 40 35, 60 45 C 80 60, 100 40, 120 50 C 140 35, 160 55, 180 45" stroke="#6EE7B7" strokeWidth="2" fill="none" opacity="0.6"/>
            <text x="0" y="58" fontSize="6" fill="#6B7280">08</text>
            <text x="30" y="58" fontSize="6" fill="#6B7280">09</text>
            <text x="60" y="58" fontSize="6" fill="#6B7280">10</text>
            <text x="90" y="58" fontSize="6" fill="#6B7280">11</text>
            <text x="120" y="58" fontSize="6" fill="#6B7280">12</text>
            <text x="150" y="58" fontSize="6" fill="#6B7280">Jan</text>
          </svg>
          <div className="flex items-baseline mt-2">
            <span className="text-2xl font-bold text-gray-900">78.9</span>
            <span className="text-sm text-gray-500 ml-1">Percentile</span>
            <span className="ml-auto text-green-600 font-medium text-sm">GOOD</span>
          </div>
        </div>

        {/* Blood Pressure */}
        <div className="p-4 bg-white rounded-2xl border border-gray-200">
          <div className="flex justify-between items-center mb-2">
            <p className="text-sm font-medium text-gray-700">BLOOD PRESSURE</p>
            <span className="text-red-500 font-bold text-xs">▲</span>
          </div>
          {/* Placeholder SVG for Blood Pressure Chart */}
          <svg viewBox="0 0 200 60" className="w-full" fill="none" xmlns="http://www.w3.org/2000/svg">
            <path d="M0 50 C 20 20, 40 40, 60 10 C 80 0, 100 30, 120 20 C 140 35, 160 15, 180 5" stroke="#F87171" strokeWidth="2" fill="none"/>
            <path d="M0 55 C 20 25, 40 45, 60 15 C 80 5, 100 35, 120 25 C 140 40, 160 20, 180 10" stroke="#FCA5A5" strokeWidth="2" fill="none" opacity="0.6"/>
            <text x="0" y="58" fontSize="6" fill="#6B7280">08</text>
            <text x="30" y="58" fontSize="6" fill="#6B7280">09</text>
            <text x="60" y="58" fontSize="6" fill="#6B7280">10</text>
            <text x="90" y="58" fontSize="6" fill="#6B7280">11</text>
            <text x="120" y="58" fontSize="6" fill="#6B7280">12</text>
            <text x="150" y="58" fontSize="6" fill="#6B7280">Jan</text>
          </svg>
          <div className="flex items-baseline mt-2">
            <span className="text-2xl font-bold text-gray-900">139</span>
            <span className="text-sm text-gray-500 ml-1">mmHg</span>
            <span className="ml-auto text-red-500 font-medium text-sm">HIGH</span>
          </div>
        </div>

        {/* Heart Rate */}
        <div className="p-4 bg-white rounded-2xl border border-gray-200">
          <div className="flex justify-between items-center mb-2">
            <p className="text-sm font-medium text-gray-700">HEART RATE</p>
            <span className="text-orange-500 font-bold text-xs">●</span> {/* Using a circle for neutral/warning */}
          </div>
          {/* Placeholder SVG for Heart Rate Chart */}
          <svg viewBox="0 0 200 60" className="w-full" fill="none" xmlns="http://www.w3.org/2000/svg">
            <path d="M0 40 C 20 30, 40 45, 60 35 C 80 20, 100 40, 120 30 C 140 45, 160 25, 180 30" stroke="#FBBF24" strokeWidth="2" fill="none"/>
            <path d="M0 45 C 20 35, 40 50, 60 40 C 80 25, 100 45, 120 35 C 140 50, 160 30, 180 35" stroke="#FCD34D" strokeWidth="2" fill="none" opacity="0.6"/>
            <text x="0" y="58" fontSize="6" fill="#6B7280">08</text>
            <text x="30" y="58" fontSize="6" fill="#6B7280">09</text>
            <text x="60" y="58" fontSize="6" fill="#6B7280">10</text>
            <text x="90" y="58" fontSize="6" fill="#6B7280">11</text>
            <text x="120" y="58" fontSize="6" fill="#6B7280">12</text>
            <text x="150" y="58" fontSize="6" fill="#6B7280">Jan</text>
          </svg>
          <div className="flex items-baseline mt-2">
            <span className="text-2xl font-bold text-gray-900">110</span>
            <span className="text-sm text-gray-500 ml-1">bpm</span>
            <span className="ml-auto text-orange-500 font-medium text-sm">AVERAGE</span>
          </div>
        </div>
      </div>

      {/* Medical Details Grid */}
      <div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-4 gap-x-8 gap-y-4 text-sm">
        <div>
          <p className="text-gray-500">Physically Challenged</p>
          <p className="font-semibold text-gray-800">No</p>
        </div>
        <div>
          <p className="text-gray-500">Height</p>
          <p className="font-semibold text-gray-800">6'11 Feet</p>
        </div>
        <div>
          <p className="text-gray-500">Weight</p>
          <p className="font-semibold text-gray-800">72 KG</p>
        </div>
        <div>
          <p className="text-gray-500">Other Medical Condition(s)</p>
          <p className="font-semibold text-gray-800">Heart Disease</p>
        </div>
        <div>
          <p className="text-gray-500">Allergies (If Any)</p>
          <p className="font-semibold text-gray-800">None</p>
        </div>
        <div>
          <p className="text-gray-500">Smoking</p>
          <p className="font-semibold text-gray-800">No</p>
        </div>
        <div>
          <p className="text-gray-500">Drinking</p>
          <p className="font-semibold text-gray-800">Occasionally</p>
        </div>
      </div>
    </div>
  );
};

export default MedicalOverviewCard;