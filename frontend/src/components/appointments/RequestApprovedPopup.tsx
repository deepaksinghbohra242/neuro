import React from 'react';

const RequestApprovedPopup = ({ title, message, onClose, onContinue, isOpen }) => {
  if (!isOpen) {
    return null; // Don't render anything if the popup is not open
  }

  return (
    <div className="fixed inset-0 bg-gray-600 bg-opacity-50 flex items-center justify-center p-4 z-50">
      <div className="bg-white rounded-lg shadow-xl w-full max-w-sm text-center relative">
        {/* Close Button */}
        <button
          onClick={onClose}
          className="absolute top-3 right-3 p-2 rounded-full text-gray-400 hover:bg-gray-100 hover:text-gray-600 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-indigo-500"
          aria-label="Close popup"
        >
          <svg className="h-6 w-6" fill="none" stroke="currentColor" viewBox="0 0 24 24" xmlns="http://www.w3.org/2000/svg">
            <path strokeLinecap="round" strokeLinejoin="round" strokeWidth="2" d="M6 18L18 6M6 6l12 12"></path>
          </svg>
        </button>

        <div className="p-6 pt-10"> {/* Added pt-10 to make space for the close button */}
          {/* Success Icon */}
          <div className="mx-auto flex items-center justify-center h-24 w-24 rounded-full bg-purple-100 mb-6">
            {/* The icon can be an SVG. This is a simplified version mimicking the image. */}
            {/* For a more precise icon, you might use a library like Heroicons or create your own SVG. */}
            <svg
              className="h-16 w-16 text-purple-600"
              fill="currentColor"
              viewBox="0 0 24 24"
              xmlns="http://www.w3.org/2000/svg"
            >
              <path fillRule="evenodd" d="M12 2.25c-5.385 0-9.75 4.365-9.75 9.75s4.365 9.75 9.75 9.75 9.75-4.365 9.75-9.75S17.385 2.25 12 2.25zm.53 7.828a.75.75 0 00-1.06 0L8.22 11.278a.75.75 0 000 1.06l2.094 2.093a.75.75 0 001.06 0l4.318-4.317a.75.75 0 00-1.06-1.06l-3.788 3.787-1.56-1.56z" clipRule="evenodd" />
            </svg>
             {/* The image's icon is more stylized; replicating that exactly requires a custom SVG or image asset.
                 Here's a simpler checkmark. If you have an SVG for the exact icon, replace the SVG above with it.
                 For example, for the exact badge shape:
             <svg width="86" height="86" viewBox="0 0 86 86" fill="none" xmlns="http://www.w3.org/2000/svg">
               <path d="M43 0C36.9839 0 31.0592 1.20003 25.5678 3.50428C20.0763 5.80854 15.0863 9.17647 10.7937 13.4691C6.50106 17.7617 3.13313 22.7517 0.828867 28.2432C-1.47539 33.7347 -2.67543 39.6094 2.67543e-06 45.625C2.67543e-06 51.6406 1.20003 57.5153 3.50428 63.0068C5.80854 68.4983 9.17647 73.4883 13.4691 77.7809C17.7617 82.0736 22.7517 85.4415 28.2432 87.7457C33.7347 90.05 39.6094 91.25 45.625 91.25C51.6406 91.25 57.5153 90.05 63.0068 87.7457C68.4983 85.4415 73.4883 82.0736 77.7809 77.7809C82.0736 73.4883 85.4415 68.4983 87.7457 63.0068C90.05 57.5153 91.25 51.6406 91.25 45.625C91.25 39.6094 90.05 33.7347 87.7457 28.2432C85.4415 22.7517 82.0736 17.7617 77.7809 13.4691C73.4883 9.17647 68.4983 5.80854 63.0068 3.50428C57.5153 1.20003 51.6406 2.67543e-06 45.625 2.67543e-06C44.7554 2.67543e-06 43.8857 0.0270833 43 0Z" fill="url(#paint0_linear_2_231)"/>
               <defs>
                 <linearGradient id="paint0_linear_2_231" x1="45.625" y1="0" x2="45.625" y2="91.25" gradientUnits="userSpaceOnUse">
                   <stop stopColor="#A88BEE"/>
                   <stop offset="1" stopColor="#824EF7"/>
                 </linearGradient>
               </defs>
             </svg>
             */}
          </div>

          <h3 className="text-xl font-semibold text-gray-800 mb-2">{title}</h3>
          <p className="text-gray-600 mb-6">{message}</p>

          {/* Continue Button */}
          <button
            type="button"
            className="w-full px-6 py-3 border border-transparent rounded-md shadow-sm text-base font-medium text-white bg-indigo-600 hover:bg-indigo-700 focus:outline-none focus:ring-2 focus:ring-indigo-500 focus:ring-offset-2"
            onClick={onContinue || onClose} // Default to onClose if onContinue is not provided
          >
            Continue
          </button>
        </div>
      </div>
    </div>
  );
};

export default RequestApprovedPopup;