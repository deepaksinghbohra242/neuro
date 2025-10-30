import React from 'react';
// import { ChevronLeftIcon } from '@heroicons/react/24/outline'; // Requires @heroicons/react

const RequestReviewHeader = () => {
  return (
    <div className="flex flex-col sm:flex-row justify-between items-start sm:items-center mb-6">
      {/* Left section: Back button and Request ID */}
      <div className="flex items-center mb-4 sm:mb-0">
        <button className="flex items-center text-gray-600 hover:text-gray-900 mr-4 transition-colors duration-200">
          {/* <ChevronLeftIcon className="h-5 w-5 mr-1" /> Heroicon */}
          <span className="sr-only">Back</span>
        </button>
        <div className="flex items-center text-gray-900 text-lg font-bold">
          <span className="text-gray-500 font-medium text-base mr-2">REQUESTS</span>
          <span className="text-gray-500 mr-2">›</span>
          RQ356356
        </div>
      </div>

      {/* Right section: Action buttons */}
      <div className="flex space-x-3">
        <button className="px-5 py-2.5 border border-red-300 text-red-600 rounded-lg hover:bg-red-50 transition-colors duration-200">
          Reject
        </button>
        <button className="px-5 py-2.5 bg-indigo-600 text-white rounded-lg shadow-md hover:bg-indigo-700 transition-colors duration-200">
          Approve & Share
        </button>
      </div>
    </div>
  );
};

export default RequestReviewHeader;