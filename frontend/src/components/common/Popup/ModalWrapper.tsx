// ModalWrapper.jsx
import React, { useEffect, useRef } from 'react';

const ModalWrapper = ({ children, onClose }) => {
  const modalRef = useRef(null);

  // Effect to close on Escape key press
  useEffect(() => {
    const handleKeyDown = (event) => {
      if (event.key === 'Escape') {
        onClose();
      }
    };
    document.addEventListener('keydown', handleKeyDown);
    return () => document.removeEventListener('keydown', handleKeyDown);
  }, [onClose]);

  // Function to close when clicking the backdrop
  const handleBackdropClick = (event) => {
    if (modalRef.current && event.target === modalRef.current) {
      onClose();
    }
  };

  return (
    // Backdrop overlay
    <div
      ref={modalRef}
      className="fixed inset-0 z-50 bg-gray-900 bg-opacity-50 overflow-y-auto  w-full flex justify-center items-center. "
      onClick={handleBackdropClick}
    >
      {/* Modal content container - ensures content is centered */}
      <div className="relative w-full max-w-4xl mx-auto my-6">
        {/* The children (the form) are rendered here */}
        {children}
      </div>
    </div>
  );
};

export default ModalWrapper;