import React, { useState } from "react";
import Table from "../common/Table";
import { useNavigate} from "react-router-dom";

interface PrescriptionData {
  id: string;
  requestId: string;
  date: string;
  prescriptionId: string;
  patientName: string;
  duration: string;
  status: "New" | "Returned" | "Rejected";
  action: string;
}

interface HeaderItem {
  id: string;
  label: string;
  count: number;
  isActive?: boolean;
  path?: string;
}


function Prescription() {
  const [activeTab, setActiveTab] = useState<"New" | "Returned" | "Rejected">("New");
  const navigate = useNavigate();
  // Dummy data for prescriptions
  const dummyData: PrescriptionData[] = [
    {
      id: "1",
      requestId: "RQ356356",
      date: "06/06/2025",
      prescriptionId: "PR089338",
      patientName: "Connor Walsh",
      duration: "07 Days",
      status: "New",
      action: "Review Request"
    },
    {
      id: "2",
      requestId: "RQ356754",
      date: "06/05/2025",
      prescriptionId: "PR235634",
      patientName: "Emily Dawson",
      duration: "15 Days",
      status: "New",
      action: "Review Request"
    },
    {
      id: "3",
      requestId: "RQ346743",
      date: "06/04/2025",
      prescriptionId: "PR357357",
      patientName: "Brady O'Connell",
      duration: "15 Days",
      status: "New",
      action: "Review Request"
    },
    {
      id: "4",
      requestId: "RQ356786",
      date: "06/03/2025",
      prescriptionId: "PR357844",
      patientName: "Ava McCarthy",
      duration: "15 Days",
      status: "New",
      action: "Review Request"
    },
    {
      id: "5",
      requestId: "RQ635673",
      date: "06/02/2025",
      prescriptionId: "PR356783",
      patientName: "Liam Kennedy",
      duration: "15 Days",
      status: "New",
      action: "Review Request"
    },
    {
      id: "6",
      requestId: "RQ357656",
      date: "06/01/2025",
      prescriptionId: "PR357367",
      patientName: "Molly Flanagan",
      duration: "15 Days",
      status: "New",
      action: "Review Request"
    },
    {
      id: "7",
      requestId: "RQ456738",
      date: "06/12/2024",
      prescriptionId: "PR364757",
      patientName: "Sean Barrett",
      duration: "15 Days",
      status: "New",
      action: "Review Request"
    },
    {
      id: "8",
      requestId: "RQ346783",
      date: "06/12/2024",
      prescriptionId: "PR467467",
      patientName: "Madeline Sloane",
      duration: "15 Days",
      status: "New",
      action: "Review Request"
    },
    // Returned prescriptions
    {
      id: "9",
      requestId: "RQ123456",
      date: "05/30/2025",
      prescriptionId: "PR123456",
      patientName: "John Murphy",
      duration: "10 Days",
      status: "Returned",
      action: "Review Request"
    },
    {
      id: "10",
      requestId: "RQ234567",
      date: "05/29/2025",
      prescriptionId: "PR234567",
      patientName: "Sarah O'Brien",
      duration: "14 Days",
      status: "Returned",
      action: "Review Request"
    },
    {
      id: "11",
      requestId: "RQ345678",
      date: "05/28/2025",
      prescriptionId: "PR345678",
      patientName: "Michael Kelly",
      duration: "21 Days",
      status: "Returned",
      action: "Review Request"
    },
    // Rejected prescriptions
    {
      id: "12",
      requestId: "RQ456789",
      date: "05/25/2025",
      prescriptionId: "PR456789",
      patientName: "Emma Thompson",
      duration: "30 Days",
      status: "Rejected",
      action: "Review Request"
    },
    {
      id: "13",
      requestId: "RQ567890",
      date: "05/24/2025",
      prescriptionId: "PR567890",
      patientName: "David O'Connor",
      duration: "14 Days",
      status: "Rejected",
      action: "Review Request"
    },
    {
      id: "14",
      requestId: "RQ678901",
      date: "05/23/2025",
      prescriptionId: "PR678901",
      patientName: "Lisa Ryan",
      duration: "7 Days",
      status: "Rejected",
      action: "Review Request"
    },
    {
      id: "15",
      requestId: "RQ789012",
      date: "05/22/2025",
      prescriptionId: "PR789012",
      patientName: "Patrick Walsh",
      duration: "21 Days",
      status: "Rejected",
      action: "Review Request"
    },
    {
      id: "16",
      requestId: "RQ890123",
      date: "05/21/2025",
      prescriptionId: "PR890123",
      patientName: "Jennifer Byrne",
      duration: "10 Days",
      status: "Rejected",
      action: "Review Request"
    },
    {
      id: "17",
      requestId: "RQ901234",
      date: "05/20/2025",
      prescriptionId: "PR901234",
      patientName: "Thomas Murphy",
      duration: "15 Days",
      status: "Rejected",
      action: "Review Request"
    },
    {
      id: "18",
      requestId: "RQ012345",
      date: "05/19/2025",
      prescriptionId: "PR012345",
      patientName: "Rachel O'Neill",
      duration: "28 Days",
      status: "Rejected",
      action: "Review Request"
    },
    {
      id: "19",
      requestId: "RQ111111",
      date: "05/18/2025",
      prescriptionId: "PR111111",
      patientName: "Kevin Doyle",
      duration: "12 Days",
      status: "Rejected",
      action: "Review Request"
    }
  ];

  // Filter data based on active tab
  const filteredData = dummyData.filter(item => item.status === activeTab);

  // Calculate counts for each status
  const counts = {
    new: dummyData.filter(item => item.status === 'New').length,
    returned: dummyData.filter(item => item.status === 'Returned').length,
    rejected: dummyData.filter(item => item.status === 'Rejected').length
  };

  const handleItemClick = (status: string) => {
    console.log(status, '******')
    navigate(`/requests/prescriptions/${status}`)
  };

  const columns = [
    {
      key: "id" as keyof PrescriptionData,
      header: "#",
      className: "font-medium text-center w-16"
    },
    {
      key: "requestId" as keyof PrescriptionData,
      header: "ID",
      className: "font-medium"
    },
    {
      key: "date" as keyof PrescriptionData,
      header: "DATE"
    },
    {
      key: "prescriptionId" as keyof PrescriptionData,
      header: "PRESCRIPTION ID",
      className: "font-medium"
    },
    {
      key: "patientName" as keyof PrescriptionData,
      header: "PATIENT NAME"
    },
    {
      key: "duration" as keyof PrescriptionData,
      header: "PRESCRIPTION DURATION (DAYS)"
    },
    {
      key: "action" as keyof PrescriptionData,
      header: "",
      render: (value: string, item: PrescriptionData) => (
        <button 
          className="text-[#785BC5] border-[1px] border-[#785BC5] rounded-md px-2 py-2 bg-[#F5F3FF] font-semibold" 
          onClick={() => handleItemClick(item.status)} // <--- MODIFIED LINE
        >
          {value}
        </button>
      )
    }
  ];
``
  const handleTabChange = (tab: string) => {
    setActiveTab(tab as "New" | "Returned" | "Rejected");
  };

  return (
    <div>
      <Table 
        data={filteredData} 
        columns={columns}
        onTabChange={handleTabChange}
        counts={counts}
        initialActiveTab={activeTab}
      />
    </div>
  );
}

export default Prescription;
