import React, { useState } from "react";
import Table from "../common/Table/index";
import actionsIcon from "../assets/icons/Actions.svg";
import downloadIcon from "../assets/icons/Download.svg";
import AddNewPrescriptions from "./AddNewPrescription";
import ModalWrapper from "../common/Popup/ModalWrapper";
import { useNavigate, useParams } from "react-router-dom";

interface PrescriptionData {
  id: string;
  date: string;
  prescribedBy: string;
  numMedicines: number;
  duration: number;
}

console.log("actionsIcon path:", actionsIcon);
// Define the columns array for the prescription data
const ActionButton = ({
  children,
  className = "",
  isPrimary = false,
  ...rest
}) => (
  <button
    {...rest}
    className={`
      px-4 py-2 rounded-lg font-medium transition-colors 
      ${
        isPrimary
          ? "bg-purple-600 text-white hover:bg-purple-700"
          : "bg-white text-gray-700 border border-gray-300 hover:bg-gray-50"
      }
      ${className}
    `}
  >
    {children}
  </button>
);

const PrescriptionsTable = ({ data, counts, activeTab, handleTabChange, currentPatientId }) => {
  // 1. State to control the modal visibility
  console.log(useParams(), '******useParams 43***')
  const [isModalOpen, setIsModalOpen] = useState(false);
  const openModal = () => setIsModalOpen(true);
  const closeModal = () => setIsModalOpen(false);
  const handleSave = (formData, medicines) => {
    console.log("Saving Data:", { formData, medicines });
    // API call logic here...
    closeModal(); // Close the modal upon successful save
  };
  const navigate = useNavigate();

  const prescriptionColumns = [
    {
      header: "#",
      key: "id",
      className: "w-12 rounded-tl-lg",
      render: (value, row, rowIndex) => rowIndex + 1,
    },
    {
      header: "ID",
      key: "id" as keyof PrescriptionData,
      className: "w-24",
    },
    {
      header: "DATE",
      key: "date" as keyof PrescriptionData,
    },
    {
      header: "PRESCRIBED BY",
      key: "prescribedBy" as keyof PrescriptionData,
    },
    {
      header: "NUMBER OF MEDICINES",
      key: "numMedicines" as keyof PrescriptionData,
      className: "text-center", // Example: center align this column's cell content
    },
    {
      header: "PRESCRIPTION DURATION (DAYS)",
      key: "duration" as keyof PrescriptionData,
      render: (value: any) => `${value} days`,
    },
    {
      header: "",
      key: "actions",
      className: "rounded-tr-lg w-48",
      render: (value: string, row: PrescriptionData) => (
        <div className="flex space-x-2">
          <button
            className="text-gray-400 hover:text-purple-600 p-1 rounded-full hover:bg-purple-50 transition-colors"
            onClick={() => handleItemClick(row.id)}
          >
            <img
              src={actionsIcon}
              alt={"View Prescription"}
              className="h-7 w-7"
            />
          </button>
          <button
            className="text-gray-400 hover:text-purple-600 p-1 rounded-full hover:bg-purple-50 transition-colors"
            onClick={() => handleDownloadItemClick(row.id)}
          >
            <img
              src={downloadIcon}
              alt={"Download Prescription"}
              className="h-7 w-7"
            />
          </button>
        </div>
      ),
    },
  ];

  const handleItemClick = (prescriptionId: string) => {
    console.log("******", prescriptionId);
    navigate(`/patients/patient_details/${currentPatientId}/${prescriptionId}`);
  };

  const handleDownloadItemClick = (id: string) => {
    console.log(id, "****Downlaod Prescription ******");
  };

  const TableHeader = (
    <div className="flex justify-between items-center mb-4">
      <h3 className="text-lg font-semibold text-gray-800">
        Total Prescriptions ({data ? data.length : 0})
      </h3>
      <ActionButton isPrimary onClick={openModal}>
        + Add New Prescription
      </ActionButton>
      {isModalOpen && (
        <ModalWrapper onClose={closeModal}>
          <AddNewPrescriptions onSave={handleSave} onDiscard={closeModal} />
        </ModalWrapper>
      )}
    </div>
  );

  return (
    <div className="mt-4">
      {TableHeader}

      <Table
        data={data || []}
        columns={prescriptionColumns}
        onTabChange={handleTabChange}
        counts={counts}
        initialActiveTab={activeTab}
      />
    </div>
  );
};

export default PrescriptionsTable;
