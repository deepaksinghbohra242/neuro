import { useState, useEffect } from "react";
import Table from "../common/Table";
import { useNavigate, useLocation } from "react-router-dom";
import { FiSearch } from "react-icons/fi";
import actionsIcon from "../assets/icons/Actions.svg";
import deleteIcon from "../assets/icons/Delete.svg";

interface PatientsData {
  id: number;
  userId: string;
  firstName: string;
  lastName: string;
  email: string;
  phoneNumber: string;
  dateOfBirth: string;
  occupation: string;
  nationality: string;
  ppsn: string;
  addressLine1: string;
  city: string;
  state: string;
  country: string;
  zipCode: string;
  lastVisit: string;
  totalVisits: number;
  externalReferral: boolean;
  status: string;
  action: string;
}

const PatientDashboard = () => {
  const navigate = useNavigate();
  const location = useLocation();

  const isArchivePage = location.pathname.includes("/patients/archive");
  const [activeTab, setActiveTab] = useState<"Active" | "Archive">(
    isArchivePage ? "Archive" : "Active"
  );

  useEffect(() => {
    if (location.pathname.includes("/patients/archive")) {
      setActiveTab("Archive");
    } else {
      setActiveTab("Active");
    }
  }, [location.pathname]);

  const handleTabChange = (tab: "Active" | "Archive") => {
    setActiveTab(tab);
    navigate(tab === "Archive" ? "/patients/archive" : "/patients");
  };

  const PatientData: PatientsData[] = [
    {
      "id": 1,
      "userId": "PA356356",
      "firstName": "Connor",
      "lastName": "Walsh",
      "email": "connor.walsh@example.com",
      "phoneNumber": "+353 123 456 789",
      "dateOfBirth": "1990-03-15",
      "occupation": "Software Engineer",
      "nationality": "Irish",
      "ppsn": "PP1234567A",
      "addressLine1": "12 St. Patrick’s Street",
      "city": "Dublin",
      "state": "Leinster",
      "country": "Ireland",
      "zipCode": "D02 XY12",
      "lastVisit": "06/06/2025",
      "totalVisits": 4,
      "externalReferral": true,
      "status": "Active",
      "action": "Remove"
    },
    {
      "id": 2,
      "userId": "PA356754",
      "firstName": "Emily",
      "lastName": "Dawson",
      "email": "emily.dawson@example.com",
      "phoneNumber": "+353 987 654 321",
      "dateOfBirth": "1992-11-02",
      "occupation": "Teacher",
      "nationality": "Irish",
      "ppsn": "PP7654321B",
      "addressLine1": "45 Abbey Road",
      "city": "Galway",
      "state": "County Galway",
      "country": "Ireland",
      "zipCode": "H91 F3K2",
      "lastVisit": "06/05/2025",
      "totalVisits": 10,
      "externalReferral": false,
      "status": "Active",
      "action": "Remove"
    },
    {
      "id": 3,
      "userId": "PA346743",
      "firstName": "Brady",
      "lastName": "O’Connell",
      "email": "brady.oconnell@example.com",
      "phoneNumber": "+353 654 321 987",
      "dateOfBirth": "1988-08-24",
      "occupation": "Architect",
      "nationality": "Irish",
      "ppsn": "PP3456789C",
      "addressLine1": "78 Highfield Avenue",
      "city": "Cork",
      "state": "Munster",
      "country": "Ireland",
      "zipCode": "T12 P4Q3",
      "lastVisit": "06/04/2025",
      "totalVisits": 6,
      "externalReferral": true,
      "status": "Active",
      "action": "Remove"
    },
    {
      "id": 4,
      "userId": "PA356786",
      "firstName": "Ava",
      "lastName": "McCarthy",
      "email": "ava.mccarthy@example.com",
      "phoneNumber": "+353 456 789 123",
      "dateOfBirth": "1995-05-10",
      "occupation": "Nurse",
      "nationality": "Irish",
      "ppsn": "PP2345678D",
      "addressLine1": "23 Elmwood Drive",
      "city": "Limerick",
      "state": "Munster",
      "country": "Ireland",
      "zipCode": "V94 T9K5",
      "lastVisit": "06/03/2025",
      "totalVisits": 3,
      "externalReferral": true,
      "status": "Active",
      "action": "Remove"
    },
    {
      "id": 5,
      "userId": "PA635673",
      "firstName": "Liam",
      "lastName": "Kennedy",
      "email": "liam.kennedy@example.com",
      "phoneNumber": "+353 765 123 890",
      "dateOfBirth": "1980-09-21",
      "occupation": "General Practitioner",
      "nationality": "Irish",
      "ppsn": "PP5678901E",
      "addressLine1": "42 St. Brendan’s Crescent",
      "city": "Galway",
      "state": "County Galway",
      "country": "Ireland",
      "zipCode": "H91 F7K3",
      "lastVisit": "06/02/2025",
      "totalVisits": 2,
      "externalReferral": true,
      "status": "Active",
      "action": "Remove"
    },
    {
      "id": 6,
      "userId": "PA357656",
      "firstName": "Molly",
      "lastName": "Flanagan",
      "email": "molly.flanagan@example.com",
      "phoneNumber": "+353 543 219 876",
      "dateOfBirth": "1997-01-12",
      "occupation": "Psychologist",
      "nationality": "Irish",
      "ppsn": "PP6789012F",
      "addressLine1": "11 Brookfield Park",
      "city": "Waterford",
      "state": "Munster",
      "country": "Ireland",
      "zipCode": "X91 K8H2",
      "lastVisit": "06/01/2025",
      "totalVisits": 8,
      "externalReferral": false,
      "status": "Active",
      "action": "Remove"
    },
    {
      "id": 7,
      "userId": "PA456738",
      "firstName": "Sean",
      "lastName": "Barrett",
      "email": "sean.barrett@example.com",
      "phoneNumber": "+353 219 876 543",
      "dateOfBirth": "1989-04-30",
      "occupation": "Accountant",
      "nationality": "Irish",
      "ppsn": "PP8901234G",
      "addressLine1": "89 Oakwood Street",
      "city": "Kilkenny",
      "state": "Leinster",
      "country": "Ireland",
      "zipCode": "R95 H4P6",
      "lastVisit": "06/12/2024",
      "totalVisits": 2,
      "externalReferral": false,
      "status": "Inactive",
      "action": "Remove"
    },
    {
      "id": 8,
      "userId": "PA346783",
      "firstName": "Madeline",
      "lastName": "Sloane",
      "email": "madeline.sloane@example.com",
      "phoneNumber": "+353 876 543 210",
      "dateOfBirth": "1994-02-08",
      "occupation": "Therapist",
      "nationality": "Irish",
      "ppsn": "PP9012345H",
      "addressLine1": "14 Greenview Road",
      "city": "Sligo",
      "state": "Connacht",
      "country": "Ireland",
      "zipCode": "F91 P2B8",
      "lastVisit": "06/12/2024",
      "totalVisits": 1,
      "externalReferral": true,
      "status": "Inactive",
      "action": "Remove"
    }
  ]

  const handleItemClick = (userId: string) => {
    navigate(`/patients/patient_details/${userId}`);
  };

  const filteredData = PatientData.filter((item) =>
    activeTab === "Active" ? item.status === "Active" : item.status === "Inactive"
  );

  const columns = [
    { key: "id" as keyof PatientsData, header: "#", className: "text-center w-12" },
    { key: "userId" as keyof PatientsData, header: "ID" },
    {
      key: "firstName" as keyof PatientsData,
      header: "PATIENT NAME",
      render: (_: string, row: PatientsData) => `${row.firstName} ${row.lastName}`,
    },
    { key: "lastVisit" as keyof PatientsData, header: "LAST VISIT" },
    { key: "totalVisits" as keyof PatientsData, header: "TOTAL VISITS" },
    {
      key: "externalReferral" as keyof PatientsData,
      header: "EXTERNAL REFERRAL",
      render: (value: boolean) => (value ? "Yes" : "No"),
    },
    {
      key: "action" as keyof PatientsData,
      header: "",
      render: (_: string, row: PatientsData) => (
        <div className="flex space-x-2">
          <button
            className="text-gray-400 hover:text-purple-600 p-1 rounded-full hover:bg-purple-50 transition-colors"
            onClick={() => handleItemClick(row.userId)}
          >
            <img src={actionsIcon} alt="View Details" className="h-7 w-7" />
          </button>
          <button
            className="text-gray-400 hover:text-red-600 p-1 rounded-full hover:bg-red-50 transition-colors"
            onClick={() => console.log("Delete", row.id)}
          >
            <img src={deleteIcon} alt="Delete" className="h-7 w-7" />
          </button>
        </div>
      ),
    },
  ];

  return (
    <div>
      {/* Header Section */}
      <div className="flex flex-col sm:flex-row justify-between items-start sm:items-center mb-4">
        {/* Title */}
        <div
          className="border-l-4 pl-3 text-xl font-semibold text-gray-900 mb-3 sm:mb-0"
          style={{ borderColor: "#5B3CA1" }}
        >
          Total Patients ({filteredData.length.toString().padStart(2, "0")})
        </div>

        {/* Header Controls */}
        <div className="flex flex-col sm:flex-row items-stretch sm:items-center gap-3 w-full sm:w-auto">
          {/* Search */}
          <div className="flex items-center gap-2 border border-gray-300 rounded-lg px-3 py-2 bg-white w-full sm:w-[320px] md:w-[380px] lg:w-[420px]">
            <FiSearch className="text-gray-500 text-lg shrink-0" />
            <input
              type="text"
              placeholder="Search patient name or patient ID..."
              className="flex-1 outline-none bg-transparent text-gray-700 placeholder-gray-400"
            />
          </div>
        </div>
      </div>

      {/* Table */}
      <Table data={filteredData} columns={columns} counts={false} />
    </div>
  );
};

export default PatientDashboard;
