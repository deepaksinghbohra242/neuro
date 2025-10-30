import React from "react";
import Table from "../common/Table";
import { Link, useNavigate} from "react-router-dom";

// Updated interface to reflect the structure of the Transfer Request data
interface GeneralData {
  id: number; // The # column
  requestID: string; // The ID column (RQ356356, etc.)
  date: string; // The DATE column
  patientName: string; // The PATIENT NAME column
  request_type: string; // The DOCTOR'S NAME column
  reason: string; // The REASON FOR TRANSFER column
  action: string; // The button text
}

function General() {
  const navigate = useNavigate();
  
  // Data matching the Transfer Request table screenshot
  const generalData: GeneralData[] = [
    {
      id: 1,
      requestID: "RQ356356",
      date: "06/06/2025",
      patientName: "Connor Walsh",
      request_type: "Leave Letter",
      reason: "Worem ipsum dolor sit amet, cons...",
      action: "Review Request",
    },
    {
      id: 2,
      requestID: "RQ356754",
      date: "06/05/2025",
      patientName: "Emily Dawson",
      request_type: "Leave Letter",
      reason: "Worem ipsum dolor sit amet, cons...",
      action: "Review Request",
    },
    {
      id: 3,
      requestID: "RQ346743",
      date: "06/04/2025",
      patientName: "Brady O'Connell",
      request_type: "Leave Letter",
      reason: "Worem ipsum dolor sit amet, cons...",
      action: "Review Request",
    },
    {
      id: 4,
      requestID: "RQ356786",
      date: "06/03/2025",
      patientName: "Ava McCarthy",
      request_type: "Leave Letter",
      reason: "Worem ipsum dolor sit amet, cons...",
      action: "Review Request",
    },
    {
      id: 5,
      requestID: "RQ635673",
      date: "06/02/2025",
      patientName: "Liam Kennedy",
      request_type: "Leave Letter",
      reason: "Worem ipsum dolor sit amet, cons...",
      action: "Review Request",
    },
    {
      id: 6,
      requestID: "RQ357656",
      date: "06/01/2025",
      patientName: "Molly Flanagan",
      request_type: "Leave Letter",
      reason: "Worem ipsum dolor sit amet, cons...",
      action: "Review Request",
    },
    {
      id: 7,
      requestID: "RQ456738",
      date: "06/12/2024",
      patientName: "Sean Barrett",
      request_type: "Leave Letter",
      reason: "Worem ipsum dolor sit amet, cons...",
      action: "Review Request",
    },
    {
      id: 8,
      requestID: "RQ346783",
      date: "06/12/2024",
      patientName: "Madeline Sloane",
      request_type: "Leave Letter",
      reason: "Worem ipsum dolor sit amet, cons...",
      action: "Review Request",
    },
  ];

  const handleItemClick = (requestId: string) => {
    // Navigates to a specific request detail page, e.g., /transfers/RQ356356
    console.log(`Navigating to review request: ${requestId}`);
    // Example navigation path (adjust as needed for your routing):
    navigate(`/requests/general/${requestId}`) 
  };

  // Updated columns to match the Transfer Request screenshot's table headers
  const columns = [
    {
      key: "id" as keyof GeneralData,
      header: "#",
      className: "font-medium text-center w-16",
    },
    {
      key: "requestID" as keyof GeneralData,
      header: "ID",
      className: "font-medium",
    },
    {
      key: "date" as keyof GeneralData,
      header: "DATE",
    },
    {
      key: "patientName" as keyof GeneralData,
      header: "PATIENT NAME",
    },
    {
      key: "request_type" as keyof GeneralData,
      header: "REQUEST TYPE",
    },
    {
      key: "reason" as keyof GeneralData,
      header: "REASON FOR TRANSFER",
      // Optional: Add a class to truncate long text, as seen in the screenshot
      className: "max-w-xs truncate", 
    },
    {
      key: "action" as keyof GeneralData,
      header: "",
      render: (value: string, row: GeneralData) => (
        <button 
          // Tailwind classes to match the purple button in the screenshot
          className="text-indigo-700 border border-indigo-200 rounded-md px-3 py-1.5 bg-indigo-50 font-medium hover:bg-indigo-100 transition-colors" 
          onClick={() => handleItemClick(row.requestID)} 
        >
          {value}
        </button>
      ),
    },
  ];

  return (
    // Assuming the Table component handles its own styling, 
    // we pass the new data and column definitions.
    <div>
      <Table
        data={generalData} // Now displays the Transfer Request data
        columns={columns} // Uses the new column headers
        counts={false} // Assuming no New/Returned/Rejected counts are needed for this view
      />
    </div>
  );
}

// Renamed the export for clarity
export default General;