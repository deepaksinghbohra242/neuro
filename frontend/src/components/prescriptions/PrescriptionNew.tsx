import React from "react";
import PrescriptionDetails from "./PrescriptionDetails";

const PrescriptionNew = () => {
  return (
    <div className="bg-gray-50 min-h-screen p-4 font-sans">
      {/* HEADER: Back Button, Breadcrumb, and Action Buttons */}
      <header className="flex justify-between items-center mb-6 mx-auto">
        <div className="flex items-center space-x-2 text-gray-600">
          <div className="p-2 mr-2 bg-white rounded-full shadow-sm cursor-pointer border border-gray-200">
            <svg
              className="w-4 h-4"
              fill="none"
              stroke="currentColor"
              viewBox="0 0 24 24"
              xmlns="http://www.w3.org/2000/svg"
            >
              <path
                strokeLinecap="round"
                strokeLinejoin="round"
                strokeWidth="2"
                d="M15 19l-7-7 7-7"
              ></path>
            </svg>
          </div>
          <span className="text-sm">
            Requests &gt; Prescriptions &gt;{" "}
            <span className="font-semibold text-gray-800">New</span>
          </span>
        </div>

        <div className="space-x-4 flex items-center">
          <button className="px-6 py-2 text-indigo-500 rounded-lg font-medium hover:bg-gray-100 transition duration-150">
            Reject
          </button>
          <button className="px-6 py-2 bg-indigo-500 text-white rounded-lg shadow-md hover:bg-indigo-600 transition duration-150">
            Approve
          </button>
        </div>
      </header>

      {/* MAIN CONTENT CARD */}
      <PrescriptionDetails />
     
    </div>
  );
};

export default PrescriptionNew;
