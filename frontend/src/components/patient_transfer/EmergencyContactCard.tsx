import React from 'react';

const EmergencyContactCard = () => {
  return (
    <div className="bg-white rounded-lg shadow-sm p-4 sm:p-6 border border-gray-100">
      <div className="flex justify-between items-center mb-4">
        <h3 className="text-lg font-bold text-gray-900">
          EMERGENCY CONTACT DETAILS <span className="text-gray-500 text-sm font-normal">・ Last Updated: 12 Dec, 2023</span>
        </h3>
        <button className="flex items-center text-indigo-600 text-sm font-medium hover:text-indigo-700 transition-colors duration-200">
          <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" strokeWidth={1.5} stroke="currentColor" className="w-4 h-4 mr-1">
            <path strokeLinecap="round" strokeLinejoin="round" d="m16.862 4.487 1.687-1.688a1.875 1.875 0 1 1 2.652 2.652L6.832 19.82a4.5 4.5 0 0 1-1.897 1.13l-2.685.8.8-2.685a4.5 4.5 0 0 1 1.13-1.897L16.863 4.487Zm0 0L19.5 7.125" />
          </svg>
          Edit
        </button>
      </div>

      <div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-4 gap-x-8 gap-y-4 text-sm">
        <div>
          <p className="text-gray-500">Name</p>
          <p className="font-semibold text-gray-800">Tara Quinn</p>
        </div>
        <div>
          <p className="text-gray-500">Phone Number</p>
          <p className="font-semibold text-indigo-600 hover:underline cursor-pointer">+353 357 345 245</p>
        </div>
        <div>
          <p className="text-gray-500">Email Address</p>
          <p className="font-semibold text-indigo-600 hover:underline cursor-pointer">taraquinnwildheart@gmail.com</p>
        </div>
        <div>
          <p className="text-gray-500">Relation</p>
          <p className="font-semibold text-gray-800">Spouse</p>
        </div>
      </div>
    </div>
  );
};

export default EmergencyContactCard;