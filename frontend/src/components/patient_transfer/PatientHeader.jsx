import React from 'react';

const PatientHeader = () => {
  return (
    <div className="bg-white rounded-lg shadow-sm p-4 sm:p-6 mb-6 flex flex-col md:flex-row items-start md:items-center justify-between border border-gray-100">
      <div className="flex items-center space-x-4 mb-4 md:mb-0">
        <img
          src="https://images.unsplash.com/photo-1506794778202-cad84cf45f1d?q=80&w=1974&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D" // Placeholder image
          alt="Leo Wildheart"
          className="w-16 h-16 rounded-full object-cover border-2 border-indigo-200"
        />
        <div>
          <h2 className="text-xl font-bold text-gray-900">Leo Wildheart t</h2>
          <p className="text-sm text-gray-500">Gender: <span className="font-medium text-gray-700">Male</span></p>
          <p className="text-sm text-gray-500">Phone Number: <a href="tel:+353824245245" className="text-indigo-600 hover:underline">+353 824 245 245</a></p>
          <p className="text-sm text-gray-500">Email ID: <a href="mailto:leo@wildheart37@gmail.com" className="text-indigo-600 hover:underline">leo@wildheart37@gmail.com</a></p>
          <p className="text-sm text-gray-500">Address: <span className="text-gray-700">Mollana Abbey, L2064, County Waterford, P36 W276, Ireland</span></p>
        </div>
      </div>

      <div className="flex flex-col sm:flex-row space-y-4 sm:space-y-0 sm:space-x-8 w-full md:w-auto mt-4 md:mt-0">
        {/* Transfer Patient Button */}
        <button className="flex items-center justify-center px-4 py-2 border border-indigo-200 text-indigo-600 rounded-lg hover:bg-indigo-50 transition-colors duration-200 text-sm font-medium w-full sm:w-auto">
          <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" strokeWidth={1.5} stroke="currentColor" className="w-4 h-4 mr-2">
            <path strokeLinecap="round" strokeLinejoin="round" d="M7.5 21 3 16.5m0 0L7.5 12M3 16.5h18" />
          </svg>
          Transfer Patient
        </button>

        {/* Visit History Dropdown & Metrics */}
        <div className="flex flex-col sm:flex-row items-start sm:items-center space-y-4 sm:space-y-0 sm:space-x-6">
          <div className="relative text-sm">
            <label htmlFor="visit-history" className="block text-gray-500 mb-1">VISIT HISTORY</label>
            <select
              id="visit-history"
              className="appearance-none bg-gray-100 border border-gray-300 rounded-md py-1.5 pl-3 pr-8 text-gray-800 text-sm focus:outline-none focus:ring-2 focus:ring-indigo-500"
            >
              <option>30 days</option>
              <option>90 days</option>
              <option>180 days</option>
            </select>
            <div className="pointer-events-none absolute inset-y-0 right-0 top-6 flex items-center px-2 text-gray-700">
              <svg className="fill-current h-4 w-4" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 20 20"><path d="M9.293 12.95l.707.707L15.657 8l-1.414-1.414L10 10.828 5.757 6.586 4.343 8z"/></svg>
            </div>
          </div>

          <div className="flex space-x-4">
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