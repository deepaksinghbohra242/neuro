import React, { useState } from 'react';
import PatientHeader from './../patient_transfer/PatientHeader';
import GeneralOverviewCard from './../patient_transfer/GeneralOverviewCard';
import MedicalOverviewCard from './../patient_transfer/MedicalOverviewCard';
import EmergencyContactCard from './../patient_transfer/EmergencyContactCard';
import PrescriptionsTable from './Tabs/PrescriptionsTable';
import TestDetails from './Tabs/TestDetails';
import ConstHistory from './Tabs/ConstHistory';
import AppHistory from './Tabs/AppHistory';
import PaymentHistory from './Tabs/PaymentHistory';
import GPDetails from './Tabs/GPDetails';
import { useParams } from 'react-router-dom';

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

const testsData = [
  {
    "id": "TD839483",
    "name": "Blood Work",
    "totalCount": 6
  },
  {
    "id": "TD839483",
    "name": "Creyos Assessment",
    "totalCount": 10
  },
  {
    "id": "TD827437",
    "name": "General Mental Health Assessment",
    "totalCount": 3
  },
  {
    "id": "TD820535",
    "name": "Blood Pressure Ratings",
    "totalCount": 3
  },
  {
    "id": "TD724854",
    "name": "Additional Documents",
    "totalCount": 3
  }
]

const constHistory = [
  {
    "id": "CA835256",
    "date": "2025-06-06",
    "attendingDoctor": "Dr. Chloe Gallagher",
    "visitType": "Follow - Up Consultation",
    "duration": "45 min"
  },
  {
    "id": "CA756437",
    "date": "2025-06-05",
    "attendingDoctor": "Dr. Chloe Gallagher",
    "visitType": "Follow - Up Consultation",
    "duration": "45 min"
  },
  {
    "id": "CA678546",
    "date": "2025-06-04",
    "attendingDoctor": "Dr. Chloe Gallagher",
    "visitType": "Follow - Up Consultation",
    "duration": "45 min"
  },
  {
    "id": "CA567878",
    "date": "2025-06-03",
    "attendingDoctor": "Dr. Chloe Gallagher",
    "visitType": "ADHD Part 2",
    "duration": "60 min"
  },
  {
    "id": "CA465774",
    "date": "2025-06-02",
    "attendingDoctor": "Dr. Chloe Gallagher",
    "visitType": "Follow - Up Consultation",
    "duration": "45 min"
  },
  {
    "id": "CA356576",
    "date": "2025-06-01",
    "attendingDoctor": "Dr. Chloe Gallagher",
    "visitType": "Follow - Up Consultation",
    "duration": "45 min"
  },
  {
    "id": "CA246574",
    "date": "2024-12-06",
    "attendingDoctor": "Dr. Chloe Gallagher",
    "visitType": "ADHD Part 1",
    "duration": "90 min"
  }
]

const appointmentData = [
  {
    id: "CA835256",
    date: "06/06/2025",
    doctorName: "Dr. Chloe Gallagher",
    visitType: "Follow - Up Consultation",
    status: "SCHEDULED",
    duration: "45 min",
  },
  {
    id: "CA756437",
    date: "06/05/2025",
    doctorName: "Dr. Chloe Gallagher",
    visitType: "Follow - Up Consultation",
    status: "EMERGENCY",
    duration: "45 min",
  },
  {
    id: "CA678546",
    date: "06/04/2025",
    doctorName: "Dr. Chloe Gallagher",
    visitType: "Follow - Up Consultation",
    status: "SCHEDULED",
    duration: "45 min",
  },
  {
    id: "CA567878",
    date: "06/03/2025",
    doctorName: "Dr. Chloe Gallagher",
    visitType: "ADHD Part 2",
    status: "EMERGENCY",
    duration: "60 min",
  },
  {
    id: "CA465774",
    date: "06/02/2025",
    doctorName: "Dr. Chloe Gallagher",
    visitType: "Follow - Up Consultation",
    status: "EMERGENCY",
    duration: "45 min",
  },
  {
    id: "CA356576",
    date: "06/01/2025",
    doctorName: "Dr. Chloe Gallagher",
    visitType: "Follow - Up Consultation",
    status: "SCHEDULED",
    duration: "45 min",
  },
  {
    id: "CA246574",
    date: "06/12/2024",
    doctorName: "Dr. Chloe Gallagher",
    visitType: "ADHD Part 1",
    status: "SCHEDULED",
    duration: "90 min",
  },
];

const paymentData = [
  { id: "PY835256", dueDate: "06/06/2025", description: "Appointment Booking", status: "UNPAID" },
  { id: "PY756437", dueDate: "06/05/2025", description: "Appointment Booking", status: "PAID" },
  { id: "PY678546", dueDate: "06/04/2025", description: "Appointment Booking", status: "PAID" },
  { id: "PY567878", dueDate: "06/03/2025", description: "Appointment Booking", status: "PAID" },
  { id: "PY465774", dueDate: "06/02/2025", description: "Appointment Booking", status: "PAID" },
  { id: "PY356576", dueDate: "06/01/2025", description: "Appointment Booking", status: "PAID" },
  { id: "PY246574", dueDate: "06/12/2024", description: "Appointment Booking", status: "PAID" },
];


const PatientDetails = () => {
  const patientId = useParams().patientId

  const [activeTab, setActiveTab] = useState('Overview');
  const [currentPatientId, setPatientId] = useState<string | undefined>(undefined);

  React.useEffect(() => {
    if (patientId) {
      setPatientId(patientId)
    }
  }, [patientId]);

  const tabs = [
    'Overview',
    'Prescriptions',
    'Tests / Documents',
    'Consultation History',
    'Appointment History',
    'Payment History',
    'GP Details'
  ];

  const renderContent = () => {
    switch (activeTab) {
      case 'Overview':
        return (
          <div className="space-y-6 p-4">
            <GeneralOverviewCard />
            <MedicalOverviewCard />
            <EmergencyContactCard />
          </div>
        );

      case 'Prescriptions':
        return (
          <div className="p-6">
            <PrescriptionsTable data={prescriptionsData} currentPatientId={currentPatientId} />
          </div>
        )

      case 'Tests / Documents':
        return (
          <div className="p-6">
            <TestDetails data={testsData} />
          </div>
        )

      case 'Consultation History':
        return (
          <div className="p-6">
            <ConstHistory data={constHistory} />
          </div>
        )

      case 'Appointment History':
        return (
          <div className="p-6">
            <AppHistory data={appointmentData} />
          </div>
        )

      case 'Payment History':
        return (
          <div className="p-6">
            <PaymentHistory data={paymentData} />
          </div>
        )

      case 'GP Details':
        return (
          <div className="p-6">
            <div className="mb-4">
              <GeneralOverviewCard />
            </div>
            <div className="mt-1">
              <GPDetails />
            </div>
          </div>
        )
      default:
        return <div className="p-6 bg-white rounded-lg shadow-sm border border-gray-100 text-center text-gray-500">Content for the {activeTab} tab is not available yet.</div>;
    }
  };

  return (
    <div className="min-h-screen bg-gray-50 p-4 sm:p-6 md:p-8 font-sans">
      {/* Patient Header Section */}
      <PatientHeader />

      <div className="bg-white rounded-lg shadow-sm overflow-x-auto mb-6 border border-gray-100">
        <nav className="flex text-sm font-medium text-gray-600 border-b border-gray-200">
          {tabs.map((tab) => (
            <button
              key={tab}
              // 2. Add onClick handler to update state
              onClick={() => setActiveTab(tab)}
              className={`px-4 py-3 whitespace-nowrap transition-colors duration-150 rounded-t-lg mr-1 ${
                // Check if this tab is the active one
                tab === activeTab
                  ? 'border-indigo-600 text-[#785BC5] bg-indigo-50 font-semibold'
                  : 'hover:text-gray-900 hover:bg-gray-50 bg-gray-100'
                }`}
            >
              {tab}
            </button>
          ))}
        </nav>
        {renderContent()}
      </div>


    </div>
  );
};


export default PatientDetails;
