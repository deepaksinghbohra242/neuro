import React from 'react';
import { CgArrowsExchangeAlt } from "react-icons/cg";

const PatientHeader = () => {
  return (
    <div className="bg-white rounded-lg shadow-sm p-4 sm:p-6 mb-6 flex flex-col md:flex-row items-start md:items-center justify-between border border-gray-100">
      <div className="flex items-center space-x-4 mb-4 md:mb-0">
        <img
          src="https://images.unsplash.com/photo-1506794778202-cad84cf45f1d?q=80&w=1974&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D" // Placeholder image
          alt="Leo Wildheart"
          className="w-38 h-38 rounded-lg object-cover border-2 border-indigo-200"
        />
        <div>
          <h2 className="text-xl font-bold text-gray-900 py-1">Leo Wildheart</h2>
          <p className="text-sm text-gray-500 py-1">Gender: <span className="font-medium text-gray-700">Male</span></p>
          <p className="text-sm text-gray-500 py-1">Phone Number: <a href="tel:+353824245245" className="text-blue-600 underline">+353 824 245 245</a></p>
          <p className="text-sm text-gray-500 py-1">Email ID: <a href="mailto:leo@wildheart37@gmail.com" className="text-blue-600 underline">leo@wildheart37@gmail.com</a></p>
          <p className="text-sm text-gray-500 py-1">Address: <span className="font-bold text-gray-900">Mollana Abbey, L2064, County Waterford, P36 W276, Ireland</span></p>
        </div>
      </div>

      <div className="flex flex-col sm:flex-row space-y-4 sm:space-y-0 sm:space-x-8 w-full md:w-auto mt-4 md:mt-0">
        {/* Transfer Patient Button */}
        <button className="flex items-center justify-center gap-2 px-3 py-1.5 border border-indigo-200 text-indigo-600 rounded-md bg-indigo-50 hover:bg-indigo-100 transition-colors duration-200 text-sm font-medium self-start">
          <CgArrowsExchangeAlt size={20} />
          Transfer Patient
        </button>

        {/* Visit History Dropdown & Metrics */}
        <div className="flex flex-col space-y-4 sm:space-y-6 border border-gray-200 p-4 rounded-lg bg-gray-50">
          {/* Visit History Row */}
          <div className="flex items-center space-x-2 text-sm">
            <label htmlFor="visit-history" className="text-gray-500 font-medium">
              VISIT HISTORY:
            </label>
            <div className="relative">
              <select
                id="visit-history"
                className="appearance-none bg-white border border-gray-300 rounded-md py-1.5 pl-3 pr-4 text-gray-800 text-sm focus:outline-none focus:ring-2 focus:ring-indigo-500"
              >
                <option>30 days</option>
                <option>90 days</option>
                <option>180 days</option>
              </select>
              <div className="pointer-events-none absolute inset-y-0 right-0 flex items-center pr-2 text-gray-700">
                <svg
                  className="fill-current h-4 w-4"
                  xmlns="http://www.w3.org/2000/svg"
                  viewBox="0 0 20 20"
                >
                  <path d="M9.293 12.95l.707.707L15.657 8l-1.414-1.414L10 10.828 5.757 6.586 4.343 8z" />
                </svg>
              </div>
            </div>
          </div>

          {/* Stats Row */}
          <div className="flex space-x-6 sm:space-x-10">
            <div className="text-center">
              <div className="text-3xl font-bold text-gray-900">01</div>
              <div className="text-xs text-gray-500 uppercase tracking-wider">Scheduled</div>
            </div>
            <div className="text-center">
              <div className="text-3xl font-bold text-red-600">00</div>
              <div className="text-xs text-gray-500 uppercase tracking-wider">Emergency</div>
            </div>
            <div className="text-center">
              <div className="text-3xl font-bold text-gray-900">00</div>
              <div className="text-xs text-gray-500 uppercase tracking-wider">No Show</div>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
};

export default PatientHeader;