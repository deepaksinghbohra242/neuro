import React from 'react';

const PrescriptionRejected = () => {
  // Mock Data based on the screenshot
  const requestData = {
    requestID: 'RQ356356',
    date: '06/06/2025',
    prescribedBy: 'Dr. Chloe Gallagher',
    patientName: 'Connor Walsh',
    prescriptionDuration: '07 Days',
    rejectionReason: 'Borem ipsum dolor sit amet, consectetur adipiscing elit. Nunc vulputate libero et velit interdum, ac aliquet odio mattis.',
  };

  const medicines = [
    { id: 1, medID: 'ME938473', name: 'Paracitamol', dose: '500 mg', frequency: 'Twice Daily', timesOrdered: '01' },
    { id: 2, medID: 'ME635626', name: 'Anti Depressants', dose: '05 mg', frequency: 'Once at Night', timesOrdered: '01' },
  ];

  const orderHistory = [
    { id: 1, orderID: 'OR2450354', orderDate: '21/12/2025', prescribedBy: 'Dr. Chloe Gallagher', deliveryDate: '22/12/2025', status: 'REJECTED', pharmacyName: 'Medicure Meds' },
  ];

  return (
    // Main background and padding
    <div className="min-h-screen bg-gray-50 p-6">
      {/* Breadcrumb/Navigation Path */}
      <div className="flex items-center text-sm text-gray-500 mb-6 mx-auto">
        <span className="cursor-pointer hover:text-gray-700">Requests</span>
        <span className="mx-1">&gt;</span>
        <span className="cursor-pointer hover:text-gray-700">Prescriptions</span>
        <span className="mx-1">&gt;</span>
        <span className="text-purple-600 font-semibold">Rejected</span>
      </div>

      {/* Main Content Card/Container */}
      <div className="bg-white p-4 md:p-6 rounded-lg shadow-md mx-auto">
        
        {/* Request ID Header */}
        <div className="flex items-center text-xl font-semibold mb-6">
          {/* Back Button and REQUESTS link */}
          <div className="flex items-center text-gray-700 mr-4 cursor-pointer hover:text-gray-900 transition duration-150">
            <svg className="w-5 h-5" fill="none" stroke="currentColor" viewBox="0 0 24 24" xmlns="http://www.w3.org/2000/svg"><path strokeLinecap="round" strokeLinejoin="round" strokeWidth="2" d="M15 19l-7-7 7-7"></path></svg>
          </div>
          <div className="text-gray-500 mr-2 uppercase text-base">REQUESTS</div>
          <div className="text-black font-semibold text-lg">{requestData.requestID}</div>
        </div>
        
        {/* --- OVERVIEW Section --- */}
        <div className="mb-8 p-4 border border-gray-200 rounded-md">
          <h2 className="text-lg font-semibold mb-4 text-gray-800 border-l-4 border-purple-600 pl-3">OVERVIEW</h2>
          
          {/* Overview Details Grid */}
          <div className="grid grid-cols-2 md:grid-cols-5 gap-y-4 gap-x-6 text-sm">
            
            {/* Detail Blocks */}
            {[
              { label: 'Request ID', value: requestData.requestID, bold: true },
              { label: 'Date', value: requestData.date },
              { label: 'Prescribed By', value: requestData.prescribedBy, color: 'text-red-500', bold: true },
              { label: 'Patient Name', value: requestData.patientName },
              { label: 'Prescription Duration', value: requestData.prescriptionDuration, bold: true },
            ].map((item) => (
              <div key={item.label}>
                <div className="text-gray-500 mb-1">{item.label}</div>
                <div className={`${item.color || 'text-gray-800'} ${item.bold ? 'font-semibold' : ''}`}>{item.value}</div>
              </div>
            ))}
          </div>
          
          {/* Reason For Rejection */}
          <div className="mt-6 pt-4 border-t border-gray-200">
            <div className="text-gray-500 text-sm mb-1">Reason For Rejection</div>
            <div className="text-gray-800 text-sm">{requestData.rejectionReason}</div>
          </div>
        </div>

        {/* --- Total Medicines Section --- */}
        <div className="mb-8">
          <h2 className="text-lg font-semibold text-gray-800 border-l-4 border-purple-600 pl-3 mb-4">Total Medicines ({medicines.length.toString().padStart(2, '0')})</h2>
          
          {/* Medicines Table */}
          <div className="overflow-x-auto border border-gray-200 rounded-md">
            <table className="min-w-full table-auto text-sm">
              <thead className="bg-gray-50">
                <tr className="border-b text-left text-gray-500 uppercase tracking-wider">
                  <th className="py-3 px-4 font-medium">#</th>
                  <th className="py-3 px-4 font-medium">ID</th>
                  <th className="py-3 px-4 font-medium">Medicine Name</th>
                  <th className="py-3 px-4 font-medium">Prescribed Dose</th>
                  <th className="py-3 px-4 font-medium">Frequency</th>
                  <th className="py-3 px-4 font-medium">Times Ordered</th>
                </tr>
              </thead>
              <tbody>
                {medicines.map((med) => (
                  <tr key={med.id} className="border-b hover:bg-gray-50">
                    <td className="py-3 px-4 text-gray-800">{med.id}</td>
                    <td className="py-3 px-4 text-purple-600 font-medium">{med.medID}</td>
                    <td className="py-3 px-4 text-gray-800">{med.name}</td>
                    <td className="py-3 px-4 text-gray-800">{med.dose}</td>
                    <td className="py-3 px-4 text-gray-800">{med.frequency}</td>
                    <td className="py-3 px-4 text-gray-800">{med.timesOrdered}</td>
                  </tr>
                ))}
              </tbody>
            </table>
          </div>
        </div>

        {/* --- Order History Section --- */}
        <div>
          <h2 className="text-lg font-semibold text-gray-800 border-l-4 border-purple-600 pl-3 mb-4">Order History ({orderHistory.length.toString().padStart(2, '0')})</h2>
          
          {/* Order History Table */}
          <div className="overflow-x-auto border border-gray-200 rounded-md">
            <table className="min-w-full table-auto text-sm">
              <thead className="bg-gray-50">
                <tr className="border-b text-left text-gray-500 uppercase tracking-wider">
                  <th className="py-3 px-4 font-medium">#</th>
                  <th className="py-3 px-4 font-medium">ID</th>
                  <th className="py-3 px-4 font-medium">Order Date</th>
                  <th className="py-3 px-4 font-medium">Prescribed By</th>
                  <th className="py-3 px-4 font-medium">Delivery Date</th>
                  <th className="py-3 px-4 font-medium">Status</th>
                  <th className="py-3 px-4 font-medium">Pharmacy Name</th>
                </tr>
              </thead>
              <tbody>
                {orderHistory.map((order) => (
                  <tr key={order.id} className="hover:bg-gray-50">
                    <td className="py-3 px-4 text-gray-800">{order.id}</td>
                    <td className="py-3 px-4 text-gray-800 font-medium">{order.orderID}</td>
                    <td className="py-3 px-4 text-gray-800">{order.orderDate}</td>
                    <td className="py-3 px-4 text-red-500 font-semibold">{order.prescribedBy}</td>
                    <td className="py-3 px-4 text-gray-800">{order.deliveryDate}</td>
                    <td className="py-3 px-4">
                      {/* Status Pill */}
                      <span className="bg-red-100 text-red-600 font-semibold px-3 py-1 rounded text-xs tracking-wider">
                        {order.status}
                      </span>
                    </td>
                    <td className="py-3 px-4 text-gray-800">{order.pharmacyName}</td>
                  </tr>
                ))}
              </tbody>
            </table>
          </div>
        </div>
        
      </div>
    </div>
  );
};

export default PrescriptionRejected;