import React, { useState } from "react";
import Table from "../common/Table";
import { useNavigate } from "react-router-dom";
import actionsIcon from "../assets/icons/Actions.svg";
import deleteIcon from "../assets/icons/Delete.svg";

interface PatientData {
  id: string;
  requestId: string;
  date: string;
  prescriptionId: string;
  patientName: string;
  duration: string;
  status: "Active" | "Archive";
  action: string;
}

const PatientDashboard = () => {
  const [activeTab, setActiveTab] = useState<"Active" | "Archive">("Active");
  const navigate = useNavigate();
  const handleTabChange = (tab: "Active" | "Archive") => {
    setActiveTab(tab);
  };

  // 1. Data for the table
  const patientsData = [
    {
      id: 1,
      patientId: "PA356356",
      name: "Connor Walsh",
      lastVisit: "06/06/2025",
      totalVisits: 4,
      externalReferral: "Yes",
    },
    {
      id: 2,
      patientId: "PA356754",
      name: "Emily Dawson",
      lastVisit: "06/05/2025",
      totalVisits: 4,
      externalReferral: "No",
    },
    {
      id: 3,
      patientId: "PA346743",
      name: "Brady O'Connell",
      lastVisit: "06/04/2025",
      totalVisits: 4,
      externalReferral: "Yes",
    },
    {
      id: 4,
      patientId: "PA356786",
      name: "Ava McCarthy",
      lastVisit: "06/03/2025",
      totalVisits: 4,
      externalReferral: "Yes",
    },
    {
      id: 5,
      patientId: "PA635673",
      name: "Liam Kennedy",
      lastVisit: "06/02/2025",
      totalVisits: 2,
      externalReferral: "Yes",
    },
    {
      id: 6,
      patientId: "PA357656",
      name: "Molly Flanagan",
      lastVisit: "06/01/2025",
      totalVisits: 8,
      externalReferral: "No",
    },
    {
      id: 7,
      patientId: "PA456738",
      name: "Sean Barrett",
      lastVisit: "06/12/2024",
      totalVisits: 2,
      externalReferral: "No",
    },
    {
      id: 8,
      patientId: "PA346783",
      name: "Madeline Sloane",
      lastVisit: "06/12/2024",
      totalVisits: 1,
      externalReferral: "Yes",
    },
  ];

  const columns = [
    {
      key: "id" as keyof PatientData,
      header: "#",
      className: "font-medium text-center w-16",
    },
    {
      key: "patientId" as keyof PatientData,
      header: "ID",
      className: "font-medium",
    },
    {
      key: "name" as keyof PatientData,
      header: "PATIENT NAME",
    },
    {
      key: "lastVisit" as keyof PatientData,
      header: "LAST VISIT",
      className: "font-medium",
    },
    {
      key: "totalVisits" as keyof PatientData,
      header: "TOTAL VISIT",
    },
    {
      key: "externalReferral" as keyof PatientData,
      header: "EXTERNAL REFRENCEC",
    },
    {
      header: "",
      key: "actions" as keyof PatientData,
      className: "rounded-tr-lg w-48",
      render: (value: string, row: rowData) => (
        <div className="flex space-x-2">
          <button
            className="text-gray-400 hover:text-purple-600 p-1 rounded-full hover:bg-purple-50 transition-colors"
            onClick={() => handleItemClick(row.patientId)}
          >
            <img
              src={actionsIcon}
              alt={"View Prescription"}
              className="h-7 w-7"
            />
          </button>
          <button
            className="text-gray-400 hover:text-purple-600 p-1 rounded-full hover:bg-purple-50 transition-colors"
            onClick={() => console.log("Download", rowData.id)}
          >
            <img
              src={deleteIcon}
              alt={"Download Prescription"}
              className="h-7 w-7"
            />
          </button>
        </div>
      ),
    },
  ];

  const handleItemClick = (requestId: string) => {
    // Navigates to a specific request detail page, e.g., /transfers/RQ356356
    console.log(`Navigating to review request: ${requestId}`);
    // Example navigation path (adjust as needed for your routing):
    navigate(`/patients/patient_details/${requestId}`);
  };

  const filteredData = patientsData.filter(
    (item) =>
      activeTab === "Active"
        ? item.totalVisits === 4 // IF 'Active', check for 4 visits
        : true // ELSE, return true (include all items)
  );

  console.log(activeTab, "******138", filteredData);
  return (
    <div>
      <Table
        data={filteredData}
        columns={columns}
        onTabChange={handleTabChange}
        counts={false}
        initialActiveTab={activeTab}
      />
    </div>
  );
};

export default PatientDashboard;
