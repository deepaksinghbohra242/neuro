import React, { useState } from "react";
import Table from "../../common/Table";
import actionsIcon from "../../assets/icons/Actions.svg";
import ReshareIcon from "../../assets/icons/Reshare.svg";
import ModalWrapper from "../../common/Popup/ModalWrapper";
import AddNewTest from "../../prescriptions/popUp/AddNewTest";
import ReassignTestPopup from "../../prescriptions/popUp/ReassignTestPopup";
import { useNavigate, useParams } from "react-router-dom";

interface TestDetailsData {
  id: string;
  name: string;
  totalCount: number;
}

interface TestDetailsProps {
  data: TestDetailsData[];
  counts?: boolean;
  activeTab?: string;
  handleTabChange?: (tab: string) => void;
}

interface ActionButtonProps {
  children: React.ReactNode;
  className?: string;
  isPrimary?: boolean;
}

const ActionButton: React.FC<ActionButtonProps> = ({
  children,
  className = "",
  isPrimary = false,
  ...rest
}) => (
  <button
    {...rest}
    className={`px-4 py-2 rounded-lg font-medium transition-colors duration-200 cursor-pointer ${isPrimary
      ? "bg-[#917BD2] text-white hover:bg-purple-700"
      : "bg-white text-gray-700 border border-gray-300 hover:bg-gray-50"
      } ${className}`}
  >
    {children}
  </button>
);

const TestDetails: React.FC<TestDetailsProps> = ({
  data,
  counts,
  activeTab,
  handleTabChange,
}) => {
  const [isModalOpen, setIsModalOpen] = useState(false);
  const openModal = () => setIsModalOpen(true);
  const closeModal = () => setIsModalOpen(false);
  const handleSave = (formData: any, medicines: any) => {
    console.log("Saving Data:", { formData, medicines });
    // API call logic here...
    closeModal();
  };

  const navigate = useNavigate();
  const [isReassignModalOpen, setIsReassignModalOpen] = useState(false);
  const [selectedTest, setSelectedTest] = useState<string | null>(null);

  const openReassignModal = (testId: string) => {
    setSelectedTest(testId);
    setIsReassignModalOpen(true);
  };

  const closeReassignModal = () => {
    setSelectedTest(null);
    setIsReassignModalOpen(false);
  };

  const handleReassignConfirm = () => {
    console.log("Reassign confirmed for test:", selectedTest);
    // API call to reassign test
    closeReassignModal();
  };

  const patientId = useParams().patientId
  const [currentPatientId, setPatientId] = useState<string | undefined>(undefined);

  React.useEffect(() => {
    if (patientId) {
      setPatientId(patientId)
    }
  }, [patientId]);

  const handleItemClick = (testId: string) => {
    navigate(`/patients/test_details/${currentPatientId}/${testId}`);
  };

  const testColumns = [
    {
      header: "#",
      key: "index",
      className: "w-12 rounded-tl-lg text-center",
      render: (_: unknown, row: TestDetailsData, rowIndex?: number) => {
        const index =
          rowIndex !== undefined
            ? rowIndex + 1
            : data.findIndex((d) => d.id === row.id) + 1;
        return index;
      },
    },
    {
      header: "ID",
      key: "id" as keyof TestDetailsData,
      className: "w-24",
    },
    {
      header: "Test / Document Name",
      key: "name" as keyof TestDetailsData,
    },
    {
      header: "Total Count",
      key: "totalCount" as keyof TestDetailsData,
      // className: "text-center",
    },
    {
      header: "",
      key: "actions",
      className: "rounded-tr-lg w-32 text-center",
      render: (_: unknown, row: TestDetailsData) => (
        <div className="flex justify-center space-x-2">
          <button
            className="text-gray-400 hover:text-purple-600 p-1 rounded-full hover:bg-purple-50 transition-colors cursor-pointer"
            onClick={() => handleItemClick(row.id)}
          >
            <img src={actionsIcon} alt="View Test Details" className="h-6 w-6" />
          </button>
          <button
            className="text-gray-400 hover:text-purple-600 p-1 rounded-full hover:bg-purple-50 transition-colors cursor-pointer"
            onClick={() => openReassignModal(row.id)}
          >
            <img src={ReshareIcon} alt="Reshare Test" className="h-6 w-6" />
          </button>
        </div>
      ),
    },
  ];

  const TableHeader = (
    <div className="flex justify-between items-center mb-4">
      <h3 className="border-l-4 pl-3 text-xl font-semibold text-gray-800 mb-3 sm:mb-0" style={{ borderColor: "#5B3CA1" }}>
        Total Tests / Documents ({data?.length ?? 0})
      </h3>
      <ActionButton isPrimary onClick={openModal}>+ Add New Test</ActionButton>
      {isModalOpen && (
        <ModalWrapper onClose={closeModal}>
          <AddNewTest onSave={handleSave} onDiscard={closeModal} />
        </ModalWrapper>
      )}
    </div>
  );

  return (
    <div className="mt-4">
      {TableHeader}

      <Table
        data={data || []}
        columns={testColumns}
        onTabChange={handleTabChange}
        counts={counts}
        initialActiveTab={activeTab}
      />

      {isReassignModalOpen && (
        <ReassignTestPopup
          onConfirm={handleReassignConfirm}
          onCancel={closeReassignModal}
        />
      )}
    </div>
  );
};

export default TestDetails;
