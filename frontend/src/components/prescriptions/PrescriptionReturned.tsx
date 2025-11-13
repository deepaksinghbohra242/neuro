import React from 'react';

// --- MOCK DATA (Matches the Second Screenshot) ---
const overviewData = {
  requestID: 'RQ356356',
  date: '06/06/2025',
  prescribedBy: 'Dr. Chloe Gallagher',
  patientName: 'Connor Walsh',
  duration: '07 Days',
  reasonForReturn: 'Borem ipsum dolor sit amet, consectetur adipiscing elit. Nunc vulputate libero et velit interdum, ac aliquet odio mattis.',
};

const prescribedMedicinesData = [
  { id: 1, medID: 'ME938473', name: 'Paracitamol', dose: '500 mg', frequency: 'Twice Daily', timesOrdered: '01' },
  { id: 2, medID: 'ME65626', name: 'Anti Depressants', dose: '05 mg', frequency: 'Once at Night', timesOrdered: '01' },
];

const alternativeMedicinesData = [
  { id: 1, medID: 'ME938473', name: 'Paracitamol', dose: '100 mg * 5', frequency: 'Twice Daily', timesOrdered: '01' },
  { id: 2, medID: 'ME635626', name: 'Anti Depressants', dose: '02 mg', frequency: 'Once at Night', timesOrdered: '01' },
];

const orderHistoryData = [
  { id: 1, orderID: 'OR2450354', orderDate: '21/12/2025', prescribedBy: 'Dr. Chloe Gallagher', deliveryDate: '22/12/2025', status: 'RETURN', pharmacy: 'Medicure Meds' },
];

// --- UTILITY COMPONENTS ---

/**
 * Section: Applies the main section title styling (blue vertical line)
 */
const Section = ({ title, children, showEdit = false }) => (
  <div className="mb-8">
    <div className="flex justify-between items-center mb-4">
      <div className="border-l-4 border-indigo-500 pl-3">
        <h2 className="text-lg font-semibold text-gray-700 tracking-wider">{title}</h2>
      </div>
      {showEdit && (
        <button className="flex items-center text-indigo-500 text-sm font-medium hover:text-indigo-600 transition duration-150">
          <svg className="w-4 h-4 mr-1" fill="none" stroke="currentColor" viewBox="0 0 24 24" xmlns="http://www.w3.org/2000/svg"><path strokeLinecap="round" strokeLinejoin="round" strokeWidth="2" d="M15.232 5.232l3.536 3.536m-2.036-5.036a2.5 2.5 0 113.536 3.536L6.5 21.036H3v-3.572L16.732 3.732z"></path></svg>
          Edit
        </button>
      )}
    </div>
    {children}
  </div>
);

/**
 * OverviewItem: Renders a single detail item in the Overview section
 */
const OverviewItem = ({ label, value }) => (
  <div className="p-2">
    <p className="text-gray-500 font-medium mb-1">{label}</p>
    <p className="text-gray-800 font-semibold">{value}</p>
  </div>
);

/**
 * OverviewCard: Renders the summary with the new 'Reason For Return' field
 */
const OverviewCard = ({ data }) => (
  <div className="bg-white p-4 rounded-lg shadow-sm border border-gray-200 text-sm">
    {/* Main Details Grid */}
    <div className="grid grid-cols-2 md:grid-cols-5 gap-y-2 mb-4 border-b pb-4 border-gray-100">
      <OverviewItem label="Request ID" value={data.requestID} />
      <OverviewItem label="Date" value={data.date} />
      <OverviewItem label="Prescribed By" value={data.prescribedBy} />
      <OverviewItem label="Patient Name" value={data.patientName} />
      <OverviewItem label="Prescription Duration" value={data.duration} />
    </div>

    {/* Reason For Return */}
    <div className="p-2">
      <p className="text-gray-500 font-medium mb-1">Reason For Return</p>
      <p className="text-gray-700 italic">{data.reasonForReturn}</p>
    </div>
  </div>
);

/**
 * MedicinesTable: Reusable component for both prescribed and alternative lists
 */
const MedicinesTable = ({ data }) => (
  <div className="overflow-x-auto border border-gray-200 rounded-lg">
    <table className="min-w-full bg-white">
      <thead className="text-left bg-gray-50 text-sm">
        <tr>
          <th className="py-3 px-4 font-medium text-gray-600 w-12">#</th>
          <th className="py-3 px-4 font-medium text-gray-600">ID</th>
          <th className="py-3 px-4 font-medium text-gray-600">MEDICINE NAME</th>
          <th className="py-3 px-4 font-medium text-gray-600">PRESCRIBED DOSE</th>
          <th className="py-3 px-4 font-medium text-gray-600">FREQUENCY</th>
          <th className="py-3 px-4 font-medium text-gray-600 text-right">TIMES ORDERED</th>
        </tr>
      </thead>
      <tbody className="divide-y divide-gray-200 text-sm">
        {data.map((item) => (
          <tr key={item.id} className="hover:bg-gray-50 transition duration-100">
            <td className="py-3 px-4 text-gray-600 font-semibold">{item.id}</td>
            <td className="py-3 px-4 text-gray-600">{item.medID}</td>
            <td className="py-3 px-4 text-gray-800">{item.name}</td>
            <td className="py-3 px-4 text-gray-800">{item.dose}</td>
            <td className="py-3 px-4 text-gray-800">{item.frequency}</td>
            <td className="py-3 px-4 text-gray-800 text-right">{item.timesOrdered}</td>
          </tr>
        ))}
      </tbody>
    </table>
  </div>
);

/**
 * OrderHistoryTable: Renders the list of previous orders
 */
const OrderHistoryTable = ({ data }) => (
  <div className="overflow-x-auto border border-gray-200 rounded-lg">
    <table className="min-w-full bg-white">
      <thead className="text-left bg-gray-50 text-sm">
        <tr>
          <th className="py-3 px-4 font-medium text-gray-600 w-12">#</th>
          <th className="py-3 px-4 font-medium text-gray-600">ID</th>
          <th className="py-3 px-4 font-medium text-gray-600">ORDER DATE</th>
          <th className="py-3 px-4 font-medium text-gray-600">PRESCRIBED BY</th>
          <th className="py-3 px-4 font-medium text-gray-600">DELIVERY DATE</th>
          <th className="py-3 px-4 font-medium text-gray-600">STATUS</th>
          <th className="py-3 px-4 font-medium text-gray-600">PHARMACY NAME</th>
        </tr>
      </thead>
      <tbody className="divide-y divide-gray-200 text-sm">
        {data.map((item) => (
          <tr key={item.id} className="hover:bg-gray-50 transition duration-100">
            <td className="py-3 px-4 text-gray-600 font-semibold">{item.id}</td>
            <td className="py-3 px-4 text-gray-600">{item.orderID}</td>
            <td className="py-3 px-4 text-gray-800">{item.orderDate}</td>
            <td className="py-3 px-4 text-gray-800">{item.prescribedBy}</td>
            <td className="py-3 px-4 text-gray-800">{item.deliveryDate}</td>
            <td className="py-3 px-4">
              {/* Status Badge Styling (RETURN is orange/amber) */}
              <span className={`inline-block px-3 py-1 text-xs font-semibold rounded ${
                item.status === 'RETURN' ? 'bg-orange-100 text-orange-700' : 'bg-green-100 text-green-700'
              }`}>
                {item.status}
              </span>
            </td>
            <td className="py-3 px-4 text-gray-800">{item.pharmacy}</td>
          </tr>
        ))}
      </tbody>
    </table>
  </div>
);

// --- MAIN COMPONENT ---

const PrescriptionReturned = () => {
  return (
    // Outer container matching the background color of the screenshot
    <div className="bg-gray-50 min-h-screen p-4 md:p-8 font-sans">
      
      {/* HEADER: Back Button, Breadcrumb, and Action Buttons */}
      <header className="flex justify-between items-center mb-6  mx-auto">
        {/* Left Side: Back button and Breadcrumb */}
        <div className="flex items-center space-x-2 text-gray-600">
          {/* Back Icon (Exact match from screenshot) */}
          <div className="p-2 mr-2 bg-white rounded-full shadow-sm cursor-pointer border border-gray-200">
            <svg className="w-4 h-4" fill="none" stroke="currentColor" viewBox="0 0 24 24" xmlns="http://www.w3.org/2000/svg">
              <path strokeLinecap="round" strokeLinejoin="round" strokeWidth="2" d="M15 19l-7-7 7-7"></path>
            </svg>
          </div>
          {/* Breadcrumb Text (Now ends with 'Returned') */}
          <span className="text-sm">Requests &gt; Prescriptions &gt; <span className="font-semibold text-gray-800">Returned</span></span>
        </div>

        {/* Right Side: Action Buttons */}
        <div className="space-x-4 flex items-center">
          <button className="px-6 py-2 text-indigo-500 rounded-lg font-medium hover:bg-gray-100 transition duration-150 border border-gray-300">
            Reject
          </button>
          <button className="px-6 py-2 bg-indigo-500 text-white rounded-lg shadow-md hover:bg-indigo-600 transition duration-150">
            Approve
          </button>
        </div>
      </header>

      {/* MAIN CONTENT CARD */}
      <main className=" mx-auto bg-white p-6 rounded-xl shadow-lg">

        {/* Request ID Title - NOTE: This element seems to be absent in the final screenshot, but implied in the URL/Header */}
        {/* We'll start directly with the Overview Section as it appears in the primary panel. */}
        <h1 className="text-xl font-bold text-gray-800 mb-6 border-b pb-4">RQ356356</h1>

        {/* 1. Overview Section (Includes Reason For Return) */}
        <Section title="OVERVIEW">
          <OverviewCard data={overviewData} />
        </Section>

        {/* 2. Total Medicines Section (Original Prescription) */}
        <Section 
          title={`Total Medicines (${prescribedMedicinesData.length < 10 ? '0' + prescribedMedicinesData.length : prescribedMedicinesData.length})`}
          showEdit={true}
        >
          <MedicinesTable data={prescribedMedicinesData} />
        </Section>

        {/* 3. Alternative Suggested By Pharmacy Section (New) */}
        <Section 
          title={`Alternative Suggested By Pharmacy`}
          showEdit={true}
        >
          <MedicinesTable data={alternativeMedicinesData} />
        </Section>

        {/* 4. Order History Section */}
        <Section title={`Order History (${orderHistoryData.length < 10 ? '0' + orderHistoryData.length : orderHistoryData.length})`}>
          <OrderHistoryTable data={orderHistoryData} />
        </Section>

      </main>
    </div>
  );
};

export default PrescriptionReturned;