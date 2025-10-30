import React from 'react';

const ClinicAddressCard = () => {
  return (
    <div className="bg-white rounded-lg shadow-sm p-4 sm:p-6 border border-gray-100">
      <h3 className="text-lg font-bold text-gray-900 mb-4">Clinic Address</h3>
      <input
        type="text"
        className="w-full border border-gray-300 rounded-md py-2 px-3 text-gray-800 focus:outline-none focus:ring-2 focus:ring-indigo-500"
        defaultValue="17 Oakfield Drive, Dublin, County Dublin, D14 K2P1, Ireland"
      />
    </div>
  );
};

export default ClinicAddressCard;