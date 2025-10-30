import React from 'react';

const TransferDetailsCard = () => {
  return (
    <div className="bg-white rounded-lg shadow-sm p-4 sm:p-6 border border-gray-100">
      <h3 className="text-lg font-bold text-gray-900 mb-4">TRANSFER DETAILS</h3>
      <div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-4 gap-x-8 gap-y-4 mb-6 text-sm">
        <div>
          <p className="text-gray-500">Transfer ID</p>
          <p className="font-semibold text-gray-800">RQ356356</p>
        </div>
        <div>
          <p className="text-gray-500">Date</p>
          <p className="font-semibold text-gray-800">06/06/2025</p>
        </div>
        <div>
          <p className="text-gray-500">Transferred By</p>
          <p className="font-semibold text-gray-800">Dr. Chloe Gallagher</p>
        </div>
        <div>
          <p className="text-gray-500">Transferred To</p>
          <p className="font-semibold text-gray-800">Dr. Claire Hennessey</p>
        </div>
      </div>
      <div className="text-sm">
        <p className="text-gray-500 mb-1">Reason for Transfer</p>
        <p className="text-gray-700 leading-relaxed">
          Worem ipsum dolor sit amet, consectetur adipiscing elit. Nunc vulputate libero et vel interdum, ac aliquet odio mattis. Class aptent taciti sociosqu ad litora torquent per conubia nostra, per inceptos himenaeos.
        </p>
      </div>
    </div>
  );
};

export default TransferDetailsCard;