import React, { useState } from 'react';
import RequestApprovedPopup from './RequestApprovedPopup'; // Adjust path

const ApproveRequestPopup = ({ title, message, onConfirm, onDiscard, isOpen }) => {
  const [isSuccessPopupOpen, setIsSuccessPopupOpen] = useState(false);

  const handleApproveAction = () => {
    // ... (Your approval logic, e.g., API call)
    // Once the approval is successful:
    setIsSuccessPopupOpen(true); // Open the success popup
  };

  const handleCloseSuccessPopup = () => {
    setIsSuccessPopupOpen(false);
    // Maybe redirect the user or refresh a list here
    console.log("Success popup closed. User might be redirected or state refreshed.");
  };

  const handleContinueFromSuccess = () => {
    setIsSuccessPopupOpen(false);
    // This is where you might navigate to another page or dismiss the entire context
    console.log("User clicked 'Continue' from success popup. Performing follow-up action.");
    // Example: navigate('/dashboard'); or refreshData();
  };

  if (!isOpen) {
    return null; // Don't render anything if the popup is not open
  }

  return (
    <div className="fixed inset-0 bg-gray-600 bg-opacity-50 flex items-center justify-center p-4 z-50">
      <RequestApprovedPopup
        isOpen={isSuccessPopupOpen}
        title="Request Approved"
        message="Your request has been approved successfully!"
        onClose={handleCloseSuccessPopup}
        onContinue={handleContinueFromSuccess} // Optional, defaults to onClose if not provided
      />

      <div className="bg-white rounded-lg shadow-xl w-full max-w-sm">
        {/* Header/Title Area */}
        <div className="p-6">
          <h2 className="text-xl font-semibold text-gray-800 mb-2">{title}</h2>
          <p className="text-gray-600">{message}</p>
        </div>

        {/* Action Buttons */}
        <div className="px-6 py-4 bg-gray-50 border-t border-gray-200 flex justify-end space-x-3 rounded-b-lg">
          <button
            type="button"
            className="px-6 py-2 border border-gray-300 rounded-md text-sm font-medium text-gray-700 hover:bg-gray-100 focus:outline-none focus:ring-2 focus:ring-indigo-500 focus:ring-offset-2"
            onClick={onDiscard}
          >
            Discard
          </button>
          <button
            type="button"
            className="px-6 py-2 border border-transparent rounded-md shadow-sm text-sm font-medium text-white bg-indigo-600 hover:bg-indigo-700 focus:outline-none focus:ring-2 focus:ring-indigo-500 focus:ring-offset-2"
            onClick={handleApproveAction}
          >
            Confirm
          </button>
        </div>
      </div>
    </div>
  );
};

export default ApproveRequestPopup;