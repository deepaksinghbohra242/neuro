import React, { useState } from "react";
import ChevronLeft from "../assets/icons/arrow-chevron-left.svg";
import PrescriptionsTable from "../prescriptions/PrescriptionsTable";
import patientIcon from '../assets/icons/Patient.svg'
import { useParams } from "react-router-dom";

// --- Mock Data ---
const prescriptionsData = [
  {
    id: "PR089338",
    date: "21/12/2025",
    prescribedBy: "Dr. Chloe Gallagher",
    numMedicines: 2,
    duration: 7,
  },
  {
    id: "PR088392",
    date: "06/12/2025",
    prescribedBy: "Dr. Chloe Gallagher",
    numMedicines: 4,
    duration: 15,
  },
  {
    id: "PR087123",
    date: "25/11/2025",
    prescribedBy: "Dr. Alex Chen",
    numMedicines: 1,
    duration: 30,
  },
];

// --- Sub-Components ---

const ActionButton = ({ children, className = "", isPrimary = false }) => (
  <button
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

// --- MODIFIED TabItem Component ---
const TabItem = ({ name, isActive, onClick }) => (
  <button
    onClick={() => onClick(name)} // Pass the name back to the handler
    className={`
      px-4 py-3 text-sm font-medium border-b-2 transition-colors whitespace-nowrap
      ${
        isActive
          ? "border-purple-600 text-purple-600"
          : "border-transparent text-gray-500 hover:text-gray-700 hover:border-gray-300"
      }
    `}
  >
    {name}
  </button>
);

// --- Placeholder Component for Other Tabs ---
const OverviewContent = () => (
    <div className="p-6 text-gray-600">
        <h3 className="text-xl font-semibold mb-3">Patient Overview</h3>
        <p>This section would contain the patient's vitals, medical history, and other summary information.</p>
    </div>
);

// --- Main Page Component ---
const PatientDetails = () => {
  const patientId = useParams().patientId

  console.log('PatientDetails:', useParams())
  // 1. State to manage the currently active tab
  const [activeTab, setActiveTab] = useState("Prescriptions");
  const [currentPatientId, setPatientId] = useState<string | undefined>(undefined);

  React.useEffect(() => {
      if (patientId) {
        setPatientId(patientId)
      }
    }, [patientId]);

  const tabs = [
    "Overview",
    "Prescriptions",
    "Tests / Documents",
    "Consultation History",
    "Appointment History",
    "Payment History",
    "GP Details",
  ];

  // 2. Function to render the content based on the active tab
  const renderContent = () => {
    switch (activeTab) {
      case "Overview":
        return <OverviewContent />;
      case "Prescriptions":
        // Assuming PrescriptionsTable is imported and works
        return (
          <div className="p-6">
            <PrescriptionsTable data={prescriptionsData} currentPatientId={currentPatientId} />
          </div>
        );
      case "Tests / Documents":
        return <div className="p-6 text-gray-600">Tests / Documents Content</div>;
      // ... other cases
      default:
        return (
          <div className="p-6 text-gray-600">Content for {activeTab}</div>
        );
    }
  };


  return (
    <div className="min-h-screen bg-gray-100 p-4 sm:p-6 lg:p-8">
      {/* Main Content Card */}
      <div className="bg-white rounded-xl shadow-lg">
        {/* 2. Patient Summary & Visit History */}
        <div className="p-6 border-b border-gray-200 flex justify-between">
          <div className="flex items-start space-x-6">
            <div className="relative h-20 w-20 overflow-hidden bg-gray-200">
              {/* Image Placeholder */}
              <img
                src="path/to/leo-wildheart.png"
                alt="Leo Wildheart"
                className="h-full w-full object-cover"
                onError={(e) => {
                  e.target.onerror = null;
                  e.target.src = patientIcon;
                }}
              />
            </div>
            <div>
              <h2 className="text-2xl font-bold text-gray-900">
                Leo Wildheart111
              </h2>
              <p className="text-sm text-gray-700 mt-1">
                <span className="font-semibold">Gender:</span> Male
              </p>
              <p className="text-sm text-gray-700">
                <span className="font-semibold">Phone Number:</span>{" "}
                <a
                  href="tel:+353824245245"
                  className="text-blue-600 hover:text-blue-800"
                >
                  +353 824 245 245
                </a>
              </p>
              <p className="text-sm text-gray-700">
                <span className="font-semibold">Email ID:</span>{" "}
                <a
                  href="mailto:leowildheart12@gmail.com"
                  className="text-blue-600 hover:text-blue-800"
                >
                  leowildheart12@gmail.com
                </a>
              </p>
              <p className="text-sm text-gray-700">
                <span className="font-semibold">Address:</span> Molana Abbey,
                L2004, County Waterford, P36 W276, Ireland
              </p>
            </div>
          </div>

          <div className="flex flex-col items-end space-y-4">
            <ActionButton className="text-sm border-purple-300 text-purple-600 hover:bg-purple-50">
              <span>Transfer Patient</span>
            </ActionButton>

            <div className="flex items-center space-x-2">
              <span className="text-sm text-gray-500">30 days</span>
              <img
                src={ChevronLeft}
                alt={"Download"}
                className="h-6 w-6 text-gray-500 cursor-pointer"
                onClick={() => ""}
              />
            </div>

            <div className="flex space-x-6">
              <div className="text-center">
                <p className="text-3xl font-bold text-gray-900">01</p>
                <p className="text-xs uppercase text-gray-500">Scheduled</p>
              </div>
              <div className="text-center">
                <p className="text-3xl font-bold text-red-500">00</p>
                <p className="text-xs uppercase text-gray-500">Emergency</p>
              </div>
              <div className="text-center">
                <p className="text-3xl font-bold text-gray-400">00</p>
                <p className="text-xs uppercase text-gray-500">No Show</p>
              </div>
            </div>
          </div>
        </div>

        {/* 3. Tab Navigation (MODIFIED) */}
        <nav className="border-b border-gray-200 px-6">
          <div className="flex space-x-4 overflow-x-auto">
            {tabs.map((tabName) => (
              <TabItem 
                key={tabName}
                name={tabName}
                isActive={activeTab === tabName}
                onClick={setActiveTab} // Pass the state setter function
              />
            ))}
          </div>
        </nav>

        {/* 4. Tab Content (MODIFIED) */}
        {renderContent()}
      </div>
    </div>
  );
};

export default PatientDetails;