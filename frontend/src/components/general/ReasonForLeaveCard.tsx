import React from 'react';

const ReasonForLeaveCard = () => {
  return (
    <div className="bg-white rounded-lg shadow-sm p-4 sm:p-6 border border-gray-100">
      <label htmlFor="reason-for-leave" className="block text-lg font-bold text-gray-900 mb-2">
        Reason For Leave <span className="text-red-500">*</span>
      </label>
      <textarea
        id="reason-for-leave"
        rows={4}
        placeholder="Enter reason here..."
        className="w-full border border-gray-300 rounded-md py-2 px-3 text-gray-800 focus:outline-none focus:ring-2 focus:ring-indigo-500 resize-y"
      ></textarea>
    </div>
  );
};

export default ReasonForLeaveCard;