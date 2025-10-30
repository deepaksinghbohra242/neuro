import React, { useState } from "react";
import { useLocation, useParams } from "react-router-dom";
import ChevronLeft from "../assets/icons/arrow-chevron-left.svg";
import ChevronRight from "../assets/icons/arrow-chevron-right.svg";
import plusIcon from "../assets/icons/plus.svg";
import chatIcon from "../assets/icons/chat-message.svg";

const ConsultationHeader = () => {
  const [currentPatientId, setPatientId] = useState<string | undefined>(
    undefined
  );
  const [currentPrescriptionId, setPrescriptionId] = useState<
    string | undefined
  >(undefined);

  const location = useLocation();
  const customPurple = "rgb(109, 83, 178)";

  const pathname = location.pathname;
  const parts = pathname.split("/").filter((part) => part.length > 0);

  const partCount = parts.length;
  let patientId = null;
  let prescriptionId = null;

  if (partCount >= 1) {
    const lastPart = parts[partCount - 1];

    if (partCount >= 2 && lastPart.startsWith("PR")) {
      prescriptionId = lastPart;
      patientId = parts[partCount - 2];
    } else if (lastPart.startsWith("PA")) {
      patientId = lastPart;
      prescriptionId = null; // Ensure it's explicitly null
    }
  }

  console.log(`Pathname: ${pathname}`);
  console.log(`Patient ID: ${patientId}`);
  console.log(`Prescription ID: ${prescriptionId}`);

  React.useEffect(() => {
    if (patientId) {
      setPatientId(patientId);
    }
  }, [patientId]);

  React.useEffect(() => {
    if (prescriptionId) {
      setPrescriptionId(prescriptionId);
    }
  }, [prescriptionId]);

  return (
    <div className="flex items-center justify-between px-6 py-3 bg-white border-b border-gray-100 shadow-sm">
      <div className="flex items-center space-x-4">
        <button
          className="p-3 text-gray-500 duration-150 ease-in-out bg-gray-50 rounded-lg bg-gray-100 focus:outline-none focus:ring-2 focus:bg-gray-100"
          style={{ width: "40px", height: "40px" }} // Ensure it's a square button
        >
          <img src={ChevronLeft} alt="user avatar" className="w-4 h-4" />
        </button>

        <div className="flex items-center text-sm font-medium">
          <span className="text-gray-500 uppercase tracking-wider">
            SCHEDULE
          </span>
          <span className="mx-2 text-gray-300 font-normal">
            <img src={ChevronRight} alt="user avatar" className="w-4 h-4" />
          </span>{" "}
          <span
            className="text-purple-700 font-semibold"
            style={{ color: customPurple }}
          >
            {currentPatientId}
          </span>
          {currentPrescriptionId && (
            <>
              <span className="mx-2 text-gray-300 font-normal">
                <img src={ChevronRight} alt="user avatar" className="w-4 h-4" />
              </span>
              <span
                className="text-purple-700 font-semibold"
                style={{ color: customPurple }}
              >
                {currentPrescriptionId}
              </span>
            </>
          )}
        </div>
      </div>

      <div className="flex items-center space-x-3">
        <button
          className="flex items-center px-4 py-2 text-sm font-semibold text-white transition duration-150 ease-in-out rounded-lg shadow-md hover:shadow-lg focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-purple-400"
          style={{ backgroundColor: customPurple }}
        >
          <img
            src={plusIcon}
            alt="Add Consultation"
            className="w-5 h-5 mr-1 -ml-1"
          />
          Add Consultation
        </button>

        {/* Notification Bell with Badge */}
        <button className="relative p-2 text-gray-600 transition duration-150 ease-in-out bg-gray-50 rounded-lg hover:bg-gray-100 focus:outline-none focus:ring-2 focus:bg-gray-100">
          <img src={chatIcon} alt="user avatar" className="w-5 h-5" />
          {/* Notification Badge */}
          <span
            className="absolute top-0 right-0 inline-flex items-center justify-center h-5 px-1 text-xs font-bold leading-none text-white transform translate-x-1/2 -translate-y-1/2 bg-red-500 rounded-full min-w-5"
            style={{ fontSize: "10px", height: "18px" }}
          >
            42
          </span>
        </button>
      </div>
    </div>
  );
};

export default ConsultationHeader;
