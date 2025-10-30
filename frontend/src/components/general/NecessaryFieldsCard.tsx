import React from 'react';
// import { CalendarIcon } from '@heroicons/react/24/outline'; // Requires @heroicons/react

const NecessaryFieldsCard = () => {
  return (
    <div className="bg-white rounded-lg shadow-sm p-4 sm:p-6 border border-gray-100">
      <div className="flex justify-between items-center mb-4">
        <h3 className="text-lg font-bold text-gray-900">Necessary Fields</h3>
        <button className="flex items-center px-4 py-2 bg-indigo-50 text-indigo-700 rounded-md text-sm font-medium hover:bg-indigo-100 transition-colors duration-200">
          <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" strokeWidth={1.5} stroke="currentColor" className="w-5 h-5 mr-2">
            <path strokeLinecap="round" strokeLinejoin="round" d="M19.5 14.25v-2.625a3.375 3.375 0 0 0-3.375-3.375h-1.5A1.125 1.125 0 0 1 13.5 7.125v-1.5a3.375 3.375 0 0 0-3.375-3.375H8.25m5.885 17.985a2.25 2.25 0 0 1-2.25 2.25H5.25a2.25 2.25 0 0 1-2.25-2.25V10.72L4.5 9.165m12.375 10.735a2.25 2.25 0 0 1-2.25 2.25H5.25a2.25 2.25 0 0 1-2.25-2.25V10.72L4.5 9.165m12.375 10.735h-7.5" />
          </svg>
          Preview Letter
        </button>
      </div>

      <div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-4 gap-6 text-sm">
        {/* Clinic Name */}
        <div>
          <label htmlFor="clinic-name" className="block text-gray-500 mb-1">Clinic Name</label>
          <input
            type="text"
            id="clinic-name"
            className="w-full border border-gray-300 rounded-md py-2 px-3 text-gray-800 focus:outline-none focus:ring-2 focus:ring-indigo-500"
            defaultValue="Neuromed Clinic"
          />
        </div>
        {/* Clinic Phone Number */}
        <div>
          <label htmlFor="clinic-phone" className="block text-gray-500 mb-1">Clinic Phone Number</label>
          <input
            type="text"
            id="clinic-phone"
            className="w-full border border-gray-300 rounded-md py-2 px-3 text-gray-800 focus:outline-none focus:ring-2 focus:ring-indigo-500"
            defaultValue="+353 346 465 346"
          />
        </div>
        {/* Clinic Email ID */}
        <div>
          <label htmlFor="clinic-email" className="block text-gray-500 mb-1">Clinic Email ID</label>
          <input
            type="email"
            id="clinic-email"
            className="w-full border border-gray-300 rounded-md py-2 px-3 text-gray-800 focus:outline-none focus:ring-2 focus:ring-indigo-500"
            defaultValue="neuromedclinic@gmail.com"
          />
        </div>
        {/* Attending Doctor */}
        <div>
          <label htmlFor="attending-doctor" className="block text-gray-500 mb-1">Attending Doctor</label>
          <input
            type="text"
            id="attending-doctor"
            className="w-full border border-gray-300 rounded-md py-2 px-3 text-gray-800 focus:outline-none focus:ring-2 focus:ring-indigo-500"
            defaultValue="Dr. Chloe Gallagher"
          />
        </div>
        {/* Patient Name */}
        <div>
          <label htmlFor="patient-name" className="block text-gray-500 mb-1">Patient Name</label>
          <input
            type="text"
            id="patient-name"
            className="w-full border border-gray-300 rounded-md py-2 px-3 text-gray-800 focus:outline-none focus:ring-2 focus:ring-indigo-500"
            defaultValue="Connor Walsh"
          />
        </div>
        {/* Start Date */}
        <div className="relative">
          <label htmlFor="start-date" className="block text-gray-500 mb-1">Start Date <span className="text-red-500">*</span></label>
          <input
            type="text" // Use "date" type for actual date picker or "text" with a custom date picker
            id="start-date"
            placeholder="dd/mm/yyyy"
            className="w-full border border-gray-300 rounded-md py-2 pl-3 pr-10 text-gray-800 focus:outline-none focus:ring-2 focus:ring-indigo-500"
          />
          {/* <CalendarIcon className="absolute right-3 top-8 h-5 w-5 text-gray-400 pointer-events-none" /> */}
        </div>
        {/* End Date */}
        <div className="relative">
          <label htmlFor="end-date" className="block text-gray-500 mb-1">End Date <span className="text-red-500">*</span></label>
          <input
            type="text" // Use "date" type for actual date picker or "text" with a custom date picker
            id="end-date"
            placeholder="dd/mm/yyyy"
            className="w-full border border-gray-300 rounded-md py-2 pl-3 pr-10 text-gray-800 focus:outline-none focus:ring-2 focus:ring-indigo-500"
          />
          {/* <CalendarIcon className="absolute right-3 top-8 h-5 w-5 text-gray-400 pointer-events-none" /> */}
        </div>
      </div>
    </div>
  );
};

export default NecessaryFieldsCard;