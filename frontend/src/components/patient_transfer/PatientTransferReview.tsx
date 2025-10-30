import React, { useState } from 'react';
import PatientHeader from './PatientHeader';
import TransferDetailsCard from './TransferDetailsCard';
import GeneralOverviewCard from './GeneralOverviewCard';
import MedicalOverviewCard from './MedicalOverviewCard';
import EmergencyContactCard from './EmergencyContactCard';

const PatientTransferReview = () => {
  // 1. Add state to track the active tab, defaulting to 'Overview'
  const [activeTab, setActiveTab] = useState('Overview');

  const tabs = [
    'Overview', 
    'Prescriptions', 
    'Tests / Documents', 
    'Consultation History', 
    'Appointment History', 
    'Payment History', 
    'GP Details'
  ];

  // Function to render content based on the active tab
  const renderContent = () => {
    switch (activeTab) {
      case 'Overview':
        // Renders the four cards when 'Overview' is active
        return (
          <div className="space-y-6">
            <TransferDetailsCard />
            <GeneralOverviewCard />
            <MedicalOverviewCard />
            <EmergencyContactCard />
          </div>
        );
      case 'Prescriptions':
        return <div className="p-6 bg-white rounded-lg shadow-sm border border-gray-100 text-center text-gray-500">Prescriptions content goes here.</div>;
      case 'Tests / Documents':
        return <div className="p-6 bg-white rounded-lg shadow-sm border border-gray-100 text-center text-gray-500">Tests / Documents content goes here.</div>;
      default:
        return <div className="p-6 bg-white rounded-lg shadow-sm border border-gray-100 text-center text-gray-500">Content for the {activeTab} tab is not available yet.</div>;
    }
  };

  return (
    <div className="min-h-screen bg-gray-50 p-4 sm:p-6 md:p-8 font-sans">
      {/* Top Navigation / Breadcrumbs */}
      <div className="flex items-center text-sm mb-6 text-gray-500">
        <span className="text-indigo-600 font-medium">REQUESTS</span>
        <span className="mx-1">›</span>
        <span className="text-indigo-600 font-medium">PATIENT TRANSFER</span>
        <span className="mx-1">›</span>
        <span className="text-gray-900 font-medium">Review Request</span>
      </div>

      {/* Main Header Section (Approve/Reject buttons) */}
      <div className="flex flex-col sm:flex-row justify-between items-start sm:items-center mb-6">
        <h1 className="text-2xl sm:text-3xl font-bold text-gray-900 mb-4 sm:mb-0">
          RQ356356
        </h1>
        <div className="flex space-x-3">
          <button className="px-5 py-2.5 border border-red-300 text-red-600 rounded-lg hover:bg-red-50 transition-colors duration-200">
            Reject
          </button>
          <button className="px-5 py-2.5 bg-indigo-600 text-white rounded-lg shadow-md hover:bg-indigo-700 transition-colors duration-200">
            Approve
          </button>
        </div>
      </div>

      {/* Patient Header Section */}
      <PatientHeader />

      {/* Tabs for Navigation (Updated with functionality) */}
      <div className="bg-white rounded-lg shadow-sm overflow-x-auto mb-6 border border-gray-100">
        <nav className="flex text-sm font-medium text-gray-600 border-b border-gray-200">
          {tabs.map((tab) => (
            <button
              key={tab}
              // 2. Add onClick handler to update state
              onClick={() => setActiveTab(tab)}
              className={`px-4 py-3 whitespace-nowrap transition-colors duration-150 ${
                // Check if this tab is the active one
                tab === activeTab
                  ? 'border-b-2 border-indigo-600 text-indigo-600 font-semibold'
                  : 'hover:text-gray-900 hover:bg-gray-50'
              }`}
            >
              {tab}
            </button>
          ))}
        </nav>
      </div>

      {/* Content Area - Conditionally rendered based on the active tab */}
      {renderContent()}

    </div>
  );
};

export default PatientTransferReview;