import React, { useState } from 'react';

const RescheduleAppointment = ({ onClose, onSave }) => {
  const [appointmentId, setAppointmentId] = useState('CA356254');
  const [attendingDoctor, setAttendingDoctor] = useState('Dr. Chloe Gallagher');
  const [date, setDate] = useState('21/01/2026');
  const [duration, setDuration] = useState('45 min');
  const [selectedTimeSlot, setSelectedTimeSlot] = useState('10:15'); // Pre-select based on image

  const timeSlots = [
    '09:00', '10:15', '11:15', '01:45', '02:00',
    '03:00', '04:00', '05:15', '06:00', '07:00'
  ];

  const handleSave = () => {
    // In a real app, you'd send this data to an API
    const dataToSave = {
      appointmentId,
      attendingDoctor,
      date,
      duration,
      selectedTimeSlot,
    };
    console.log("Saving appointment:", dataToSave);
    onSave(dataToSave); // Call the parent's save handler
    onClose(); // Close the popup
  };

  return (
    <div className="fixed inset-0 bg-gray-600 bg-opacity-50 flex items-center justify-center p-4 z-50">
      <div className="bg-white rounded-lg shadow-xl w-full max-w-2xl">
        {/* Header */}
        <div className="p-6 border-b border-gray-200">
          <h2 className="text-xl font-semibold text-gray-800">Reschedule Appointment</h2>
        </div>

        {/* Form Body */}
        <div className="p-6 grid grid-cols-1 md:grid-cols-2 gap-x-6 gap-y-4">
          {/* Appointment ID */}
          <div>
            <label htmlFor="appointmentId" className="block text-sm font-medium text-gray-700 mb-1">
              Appointment / Consultation ID
            </label>
            <input
              type="text"
              id="appointmentId"
              className="mt-1 block w-full px-3 py-2 border border-gray-300 rounded-md shadow-sm focus:outline-none focus:ring-indigo-500 focus:border-indigo-500 sm:text-sm bg-gray-100"
              value={appointmentId}
              onChange={(e) => setAppointmentId(e.target.value)}
              readOnly // Assuming this field is not editable
            />
          </div>

          {/* Attending Doctor */}
          <div>
            <label htmlFor="attendingDoctor" className="block text-sm font-medium text-gray-700 mb-1">
              Attending Doctor <span className="text-red-500">*</span>
            </label>
            <div className="relative">
              <select
                id="attendingDoctor"
                className="mt-1 block w-full pl-3 pr-10 py-2 text-base border border-gray-300 focus:outline-none focus:ring-indigo-500 focus:border-indigo-500 sm:text-sm rounded-md appearance-none"
                value={attendingDoctor}
                onChange={(e) => setAttendingDoctor(e.target.value)}
              >
                <option>Dr. Chloe Gallagher</option>
                <option>Dr. Alex Smith</option>
                <option>Dr. Emily White</option>
              </select>
              <div className="pointer-events-none absolute inset-y-0 right-0 flex items-center px-2 text-gray-700">
                <svg className="h-4 w-4" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 20 20" fill="currentColor">
                  <path fillRule="evenodd" d="M5.293 7.293a1 1 0 011.414 0L10 10.586l3.293-3.293a1 1 0 111.414 1.414l-4 4a1 1 0 01-1.414 0l-4-4a1 1 0 010-1.414z" clipRule="evenodd" />
                </svg>
              </div>
            </div>
          </div>

          {/* Date */}
          <div>
            <label htmlFor="date" className="block text-sm font-medium text-gray-700 mb-1">
              Date <span className="text-red-500">*</span>
            </label>
            <div className="relative">
              <input
                type="text" // Or type="date" for native date picker
                id="date"
                className="mt-1 block w-full px-3 py-2 border border-gray-300 rounded-md shadow-sm focus:outline-none focus:ring-indigo-500 focus:border-indigo-500 sm:text-sm"
                value={date}
                onChange={(e) => setDate(e.target.value)}
              />
              <div className="pointer-events-none absolute inset-y-0 right-0 flex items-center px-3 text-gray-700">
                <svg className="h-5 w-5" fill="currentColor" viewBox="0 0 20 20">
                  <path fillRule="evenodd" d="M6 2a1 1 0 00-1 1v1H4a2 2 0 00-2 2v10a2 2 0 002 2h12a2 2 0 002-2V6a2 2 0 00-2-2h-1V3a1 1 0 10-2 0v1H7V3a1 1 0 00-1-1zm0 5a1 1 0 000 2h8a1 1 0 100-2H6z" clipRule="evenodd"></path>
                </svg>
              </div>
            </div>
          </div>

          {/* Duration */}
          <div>
            <label htmlFor="duration" className="block text-sm font-medium text-gray-700 mb-1">
              Duration <span className="text-red-500">*</span>
            </label>
            <div className="relative">
              <select
                id="duration"
                className="mt-1 block w-full pl-3 pr-10 py-2 text-base border border-gray-300 focus:outline-none focus:ring-indigo-500 focus:border-indigo-500 sm:text-sm rounded-md appearance-none"
                value={duration}
                onChange={(e) => setDuration(e.target.value)}
              >
                <option>30 min</option>
                <option>45 min</option>
                <option>60 min</option>
                <option>90 min</option>
              </select>
              <div className="pointer-events-none absolute inset-y-0 right-0 flex items-center px-2 text-gray-700">
                <svg className="h-4 w-4" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 20 20" fill="currentColor">
                  <path fillRule="evenodd" d="M5.293 7.293a1 1 0 011.414 0L10 10.586l3.293-3.293a1 1 0 111.414 1.414l-4 4a1 1 0 01-1.414 0l-4-4a1 1 0 010-1.414z" clipRule="evenodd" />
                </svg>
              </div>
            </div>
          </div>

          {/* Available Time Slots */}
          <div className="md:col-span-2 mt-4">
            <div className="flex items-center justify-between mb-2">
              <span className="block text-sm font-medium text-gray-700">Available Time Slots</span>
              <span className="text-sm text-red-500">*Select your preferred time slot</span>
            </div>
            <div className="grid grid-cols-3 sm:grid-cols-5 gap-2">
              {timeSlots.map((slot) => (
                <button
                  key={slot}
                  type="button"
                  className={`px-4 py-2 text-sm rounded-md border 
                              ${selectedTimeSlot === slot
                                ? 'bg-indigo-600 text-white border-indigo-600'
                                : 'bg-white text-gray-700 border-gray-300 hover:bg-gray-50'
                              } focus:outline-none focus:ring-2 focus:ring-indigo-500 focus:ring-offset-2`}
                  onClick={() => setSelectedTimeSlot(slot)}
                >
                  {slot}
                </button>
              ))}
            </div>
          </div>
        </div>

        {/* Footer Buttons */}
        <div className="px-6 py-4 bg-gray-50 border-t border-gray-200 flex justify-end space-x-3">
          <button
            type="button"
            className="px-6 py-2 border border-gray-300 rounded-md text-sm font-medium text-gray-700 hover:bg-gray-100 focus:outline-none focus:ring-2 focus:ring-indigo-500 focus:ring-offset-2"
            onClick={onClose}
          >
            Discard
          </button>
          <button
            type="button"
            className="px-6 py-2 border border-transparent rounded-md shadow-sm text-sm font-medium text-white bg-indigo-600 hover:bg-indigo-700 focus:outline-none focus:ring-2 focus:ring-indigo-500 focus:ring-offset-2"
            onClick={handleSave}
          >
            Save
          </button>
        </div>
      </div>
    </div>
  );
};

export default RescheduleAppointment;