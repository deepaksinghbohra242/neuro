import React from 'react';

const GeneralOverviewCard = () => {
  return (
    <div className="bg-white rounded-lg shadow-sm p-4 sm:p-6 border border-gray-100">
      <div className="flex justify-between items-center mb-4">
        <h3 className="text-lg font-bold text-gray-900">
          GENERAL OVERVIEW <span className="text-gray-500 text-sm font-normal">・ Last Updated: 12 Dec, 2023</span>
        </h3>
        <button className="flex items-center text-indigo-600 text-sm font-medium hover:text-indigo-700 transition-colors duration-200">
          <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" strokeWidth={1.5} stroke="currentColor" className="w-4 h-4 mr-1">
            <path strokeLinecap="round" strokeLinejoin="round" d="m16.862 4.487 1.687-1.688a1.875 1.875 0 1 1 2.652 2.652L6.832 19.82a4.5 4.5 0 0 1-1.897 1.13l-2.685.8.8-2.685a4.5 4.5 0 0 1 1.13-1.897L16.863 4.487Zm0 0L19.5 7.125" />
          </svg>
          Edit
        </button>
      </div>

      <div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-x-8 gap-y-4 text-sm">
        <div>
          <p className="text-gray-500">Patient ID</p>
          <p className="font-semibold text-gray-800">PA035829</p>
        </div>
        <div>
          <p className="text-gray-500">Date of Birth</p>
          <p className="font-semibold text-gray-800">19 Sep, 1997 <span className="text-gray-500 ml-1">28 Years</span></p>
        </div>
        <div>
          <p className="text-gray-500">Occupation</p>
          <p className="font-semibold text-gray-800">Commercial Manager</p>
        </div>
        <div>
          <p className="text-gray-500">PPSN</p>
          <p className="font-semibold text-gray-800">XXXXXXXX124</p>
        </div>
        {/* Placeholder for Nationality, moved here to match grid */}
        <div>
          <p className="text-gray-500">Nationality</p>
          <p className="font-semibold text-gray-800">Ireland</p>
        </div>
      </div>
    </div>
  );
};

export default GeneralOverviewCard;