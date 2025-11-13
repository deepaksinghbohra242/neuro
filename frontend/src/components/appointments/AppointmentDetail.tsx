import React, { useState } from 'react';
import RescheduleAppointment from './RescheduleAppointment'; // Adjust the path as necessary
import ApproveRequestPopup from './ApproveRequestPopup';

const appointmentData = {
  requestID: 'RP124532',
  date: '06/06/2025',
  patientName: 'Connor Walsh',
  attendingConsultant: 'Dr. Chloe Gallagher',
  visitType: 'Follow - Up Consultation',
  duration: '45 min',
  testsDocuments: [
    { id: 1, docID: 'TS834934', date: '06/06/2025', name: 'GP Letter' },
    { id: 2, docID: 'TS245656', date: '06/06/2025', name: 'Transfer Letter' },
    { id: 3, docID: 'TS824452', date: '06/06/2025', name: 'Creyos Assessment' },
    { id: 4, docID: 'TS839453', date: '06/06/2025', name: 'General Mental Health Assessment' },
  ],
};

const AppointmentDetailView = () => {
  const [isRescheduleOpen, setIsRescheduleOpen] = useState(false);
  const [isPopupOpen, setIsPopupOpen] = useState(false);

  const handleOpenReschedule = () => {
    setIsRescheduleOpen(true);
  };
  const handleOpenPopup = () => {
    setIsPopupOpen(true);
  };

  const handleCloseReschedule = () => {
    setIsRescheduleOpen(false);
  };
  const handleClosePopup = () => {
    setIsPopupOpen(false);
  };

  const handleSaveReschedule = (rescheduleData) => {
    console.log("Appointment Rescheduled Successfully:", rescheduleData);
  };

  const handleConfirm = () => {
    // 🚨 Perform the action (e.g., API call to approve the request)
    console.log(`CONFIRMED: Approving request ID: `);
    // onApproval(); // Call a function passed from the list container
    handleClosePopup();    // Close the popup
  };

  const handleDiscard = () => {
    console.log(`DISCARDED: Approval process cancelled for ID: `);
    handleClosePopup();
  };

  return (
    <div className="min-h-screen bg-gray-100 p-8">
      {/* Top Header/Navigation Bar */}
      {isRescheduleOpen && (
        <RescheduleAppointment
          onClose={handleCloseReschedule} // Pass the close function
          onSave={handleSaveReschedule}   // Pass the save function
        />
      )}

      <ApproveRequestPopup
        isOpen={isPopupOpen}
        title="Approve Request"
        message={`Are you sure you want to approve this request (ID: )? This action cannot be undone.`}
        onConfirm={handleConfirm}
        onDiscard={handleDiscard}
      />

      <div className="bg-white rounded-lg shadow-md overflow-hidden">
        <div className="flex items-center justify-between p-3 border-b border-gray-200">
          <div className="flex items-center space-x-4 text-sm font-medium">
            <button className="text-gray-500 hover:text-gray-700">
              {'<'}
            </button>
            <span className="text-gray-500 uppercase">APPOINTMENTS</span>
            <span className="text-purple-700 ml-1 font-semibold">RP124532</span>
          </div>
          <div className="flex space-x-2">
            <button className="px-4 py-2 text-purple-700 border border-purple-200 rounded-lg hover:bg-purple-50 transition" 
            onClick={handleOpenReschedule}>
              Reschedule
            </button>
            <button className="px-4 py-2 bg-purple-700 text-white font-medium rounded-lg hover:bg-purple-800 transition shadow-md" 
            onClick={handleOpenPopup}>
              Approve
            </button>
          </div>
        </div>

        <div className="p-6 space-y-8">
          {/* OVERVIEW Section */}
          <div>
            <h2 className="text-lg font-semibold text-gray-700 mb-4 tracking-wider border-l-4 border-purple-500 pl-3">OVERVIEW</h2>
            <div className="grid grid-cols-4 gap-4 text-sm">
              {/* Request ID */}
              <div>
                <p className="text-gray-500">Request ID</p>
                <p className="font-semibold text-gray-800">{appointmentData.requestID}</p>
              </div>
              {/* Date */}
              <div>
                <p className="text-gray-500">Date</p>
                <p className="font-semibold text-gray-800">{appointmentData.date}</p>
              </div>
              {/* Patient Name */}
              <div>
                <p className="text-gray-500">Patient Name</p>
                <p className="font-semibold text-gray-800">{appointmentData.patientName}</p>
              </div>
              {/* Attending Consultant */}
              <div>
                <p className="text-gray-500">Attending Consultant</p>
                <p className="font-semibold text-gray-800">{appointmentData.attendingConsultant}</p>
              </div>
            </div>
            <div className="border-b border-gray-200 mt-6 mb-8"></div> {/* Separator Line */}
          </div>

          {/* APPOINTMENT DETAILS Section */}
          <div>
            <h2 className="text-lg font-semibold text-gray-700 mb-4 tracking-wider border-l-4 border-purple-500 pl-3">APPOINTMENT DETAILS</h2>
            <div className="flex space-x-12 text-sm">
              {/* Visit Type */}
              <div>
                <p className="text-gray-500">Visit Type</p>
                <p className="font-semibold text-gray-800">{appointmentData.visitType}</p>
              </div>
              {/* Duration */}
              <div>
                <p className="text-gray-500">Duration</p>
                <p className="font-semibold text-gray-800">{appointmentData.duration}</p>
              </div>
            </div>
            <div className="border-b border-gray-200 mt-6 mb-8"></div> {/* Separator Line */}
          </div>

          {/* ASSIGN TESTS / DOCUMENTS Section */}
          <div>
            <h2 className="text-lg font-semibold text-gray-700 mb-4 tracking-wider border-l-4 border-purple-500 pl-3">ASSIGN TESTS / DOCUMENTS</h2>
            
            <div className="overflow-x-auto">
              <table className="min-w-full divide-y divide-gray-200">
                <thead>
                  <tr className="text-left text-xs font-medium text-gray-500 uppercase tracking-wider">
                    <th className="py-3 pr-6">#</th>
                    <th className="py-3 pr-6">ID</th>
                    <th className="py-3 pr-6 flex items-center">
                        DATE
                        <span className="text-gray-400 ml-1">^</span>
                    </th>
                    <th className="py-3 pr-6">NAME</th>
                    <th className="py-3 pr-6"></th> {/* Action column */}
                  </tr>
                </thead>
                <tbody className="bg-white divide-y divide-gray-200 text-sm">
                  {appointmentData.testsDocuments.map((doc) => (
                    <tr key={doc.id}>
                      <td className="py-3 pr-6 font-medium text-gray-900">{doc.id}</td>
                      <td className="py-3 pr-6 text-gray-500">{doc.docID}</td>
                      <td className="py-3 pr-6 text-gray-500">{doc.date}</td>
                      <td className="py-3 pr-6 text-gray-700">{doc.name}</td>
                      <td className="py-3 pr-6 text-right">
                        <button className="inline-flex items-center px-3 py-1.5 text-sm font-medium rounded-lg text-purple-700 bg-purple-100 hover:bg-purple-200 transition ease-in-out duration-150">
                          View Attachment
                        </button>
                      </td>
                    </tr>
                  ))}
                </tbody>
              </table>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
};

export default AppointmentDetailView;