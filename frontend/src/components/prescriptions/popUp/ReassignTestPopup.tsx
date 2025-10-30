import React from "react";
import ModalWrapper from "../../common/Popup/ModalWrapper";

interface ReassignTestPopupProps {
  onConfirm: () => void;
  onCancel: () => void;
}

const ReassignTestPopup: React.FC<ReassignTestPopupProps> = ({
  onConfirm,
  onCancel,
}) => {
  return (
    <ModalWrapper onClose={onCancel}>
      <div className="bg-gray-100 p-6 rounded-xl shadow-lg max-w-md w-full">
        <h2 className="text-xl font-semibold text-gray-900 mb-4">
          Reassign Test
        </h2>
        <p className="text-gray-600 mb-6">
          Are you sure you want to reassign this test?
        </p>
        <div className="flex justify-end space-x-3">
          <button
            type="button"
            onClick={onCancel}
            className="px-6 py-2 border border-gray-300 rounded-lg text-gray-700 bg-white 
                       hover:bg-gray-50 font-medium transition duration-150 ease-in-out"
          >
            Discard
          </button>
          <button
            onClick={onConfirm}
            className="px-6 py-2 bg-[#917BD2] rounded-lg text-white hover:bg-purple-500 
                       shadow-md font-medium transition duration-150 ease-in-out"
          >
            Confirm
          </button>
        </div>
      </div>
    </ModalWrapper>
  );
};

export default ReassignTestPopup;
