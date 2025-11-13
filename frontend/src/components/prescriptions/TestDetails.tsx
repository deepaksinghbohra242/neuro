import React, { useState } from "react";
import Table from "../common/Table";
import reassignTest from '../assets/icons/reassignTest.svg'
import uploadDoc from '../assets/icons/cloud-upload.svg'
import viewDoc from '../assets/icons/viewDoc.svg'
import ReassignTestPopup from "./popUp/ReassignTestPopup";
import UploadDocumentPopup from "./popUp/UploadDocumentPopup";

const TestDetails: React.FC = () => {
  // --- OVERVIEW DATA ---
  const overviewData = {
    testID: "TD839483",
    name: "Blood Work",
  };

  // --- TESTS TABLE DATA ---
  const testsData = [
    { id: 1, testID: "TS834934", date: "06/05/2025", assignedBy: "Dr. Chloe Gallagher", name: "Blood Work", attachment: "View Attachment" },
    { id: 2, testID: "TS245656", date: "06/04/2025", assignedBy: "Dr. Chloe Gallagher", name: "Blood Work", attachment: "View Attachment" },
    { id: 3, testID: "TS824452", date: "06/03/2025", assignedBy: "Dr. Chloe Gallagher", name: "Blood Work", attachment: "View Attachment" },
    { id: 4, testID: "TS292445", date: "06/02/2025", assignedBy: "Dr. Chloe Gallagher", name: "Blood Work", attachment: "View Attachment" },
    { id: 5, testID: "TS454535", date: "06/01/2025", assignedBy: "Dr. Chloe Gallagher", name: "Blood Work", attachment: "View Attachment" },
    { id: 6, testID: "TS535635", date: "06/12/2024", assignedBy: "Dr. Chloe Gallagher", name: "Blood Work", attachment: "View Attachment" },
  ];

  // --- TABLE COLUMNS ---
  const columns = [
    {
      header: "#",
      key: "index",
      className: "w-12 text-center",
      render: (_: unknown, row: any, rowIndex?: number) => {
        const index =
          rowIndex !== undefined
            ? rowIndex + 1
            : testsData.findIndex((d) => d.testID === row.testID) + 1;
        return index;
      },
    },
    { header: "ID", key: "testID", className: "w-32" },
    { header: "DATE", key: "date" },
    { header: "ASSIGNED BY", key: "assignedBy" },
    { header: "NAME", key: "name" },
    {
      header: "",
      key: "attachment",
      render: () => (
        <button
          type="button"
          className="flex items-center gap-2 px-6 py-2 border border-gray-300 rounded-lg text-[#785BC5] bg-[#EDEAFF]
           hover:bg-gray-50 font-medium transition duration-150 ease-in-out cursor-pointer"
        >
          <img src={viewDoc} alt="viewDoc" className="w-4 h-4 text-[#785BC5]" />
          <span>View Attachment</span>
        </button>
      ),
    },
  ];

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


  const [isUploadModalOpen, setIsUploadModalOpen] = useState(false);
  const openUploadModal = () => {
    setIsUploadModalOpen(true);
  };

  const closeUploadModal = () => {
    setIsUploadModalOpen(false);
  };

    const handleUploadSave = (file: File | null) => {
    if (file) {
        console.log("Document uploaded and saved:", file.name);
        // API call to upload document
    }
    closeUploadModal();
  };

  const handleUploadDiscard = () => {
    console.log("Upload discarded.");
    closeUploadModal();
  };

  const formatCount = (count: number) => (count < 10 ? `0${count}` : count);

  return (
    <main className="mx-auto bg-white p-6 rounded-xl shadow-lg">
      {/* --- OVERVIEW SECTION --- */}
      <div className="mb-8">
        <div className="border-l-4 border-indigo-500 pl-3 mb-4">
          <h2 className="text-lg font-semibold text-gray-700 tracking-wider">OVERVIEW</h2>
        </div>

        <div className="bg-white p-6 rounded-lg shadow-sm border border-gray-200 grid grid-cols-2 gap-y-4 text-sm">
          <div>
            <p className="text-gray-500 font-medium mb-1">Test / Document ID</p>
            <p className="text-gray-800 font-semibold">{overviewData.testID}</p>
          </div>
          <div>
            <p className="text-gray-500 font-medium mb-1">Name</p>
            <p className="text-gray-800 font-semibold">{overviewData.name}</p>
          </div>
        </div>
      </div>

      {/* --- TABLE HEADER WITH ACTION BUTTONS --- */}
      <div className="flex justify-between items-center mb-4">
        <div className="border-l-4 border-indigo-500 pl-3">
          <h2 className="text-lg font-semibold text-gray-700 tracking-wider">
            Total Tests ({formatCount(testsData.length)})
          </h2>
        </div>
        <div className="flex space-x-3">
          <button
            type="button"
            onClick={() => openReassignModal(overviewData.testID)}
            className="flex items-center gap-2 px-6 py-2 border border-gray-300 rounded-lg text-gray-700 bg-white 
               hover:bg-gray-50 font-medium transition duration-150 ease-in-out cursor-pointer"
          >
            <img src={reassignTest} alt="reassignTest" className="w-4 h-4" />
            <span>Reassign Test</span>
          </button>

          <button
            type="button"
            onClick={openUploadModal} 
            className="flex items-center gap-2 px-6 py-2 border border-gray-300 rounded-lg text-gray-700 bg-gray-100 
               hover:bg-gray-50 font-medium transition duration-150 ease-in-out cursor-pointer"
          >
            <img src={uploadDoc} alt="uploadDoc" className="w-4 h-4" />
            <span>Upload Document</span>
          </button>
        </div>
      </div>

      {/* --- TABLE SECTION --- */}
      <Table data={testsData} columns={columns} />
      {isReassignModalOpen && (
        <ReassignTestPopup
          onConfirm={handleReassignConfirm}
          onCancel={closeReassignModal}
        />
      )}

      {isUploadModalOpen && (
        <UploadDocumentPopup
          onSave={handleUploadSave}
          onDiscard={handleUploadDiscard}
        />
      )}
    </main>
  );
};

export default TestDetails;
