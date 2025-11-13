import React from "react";
import Table from "../common/Table";
import { useNavigate } from "react-router-dom";
import { useAppointments } from "../../hooks";
import type { Appointment } from "../../models";

function Prescription() {
  const navigate = useNavigate();
  const consultantId = 501; // Replace with logged-in consultant’s ID (e.g., from auth context)
  const { appointments, loading, error } = useAppointments(consultantId);

  // Transform Appointment[] into table display format
  const visitData = appointments.map((appointment: Appointment, index) => ({
    id: (index + 1).toString(),
    visitId: `${appointment.id}`,
    dateTime: `${appointment.date} ${appointment.timeSlot}`,
    patientName: `${appointment.patientUserModel?.firstName || ""} ${appointment.patientUserModel?.lastName || ""}`,
    visitType: appointment.visitType || "Consultation",
    duration: appointment.duration ? `${appointment.duration} min` : "N/A",
    action: "Review Request",
  }));

  type VisitData = typeof visitData[number];

  const handleItemClick = (visitId: string) => {
    navigate(`/requests/appointments/${visitId}`);
  };

  const columns: {
    key: keyof VisitData;
    header: string;
    className?: string;
    render?: (value: VisitData[keyof VisitData], row: VisitData) => React.ReactNode;
  }[] = [
    { key: "id", header: "#", className: "font-medium text-center w-16" },
    { key: "visitId", header: "ID" },
    { key: "dateTime", header: "DATE & TIME" },
    { key: "patientName", header: "PATIENT NAME" },
    { key: "visitType", header: "VISIT TYPE" },
    { key: "duration", header: "DURATION" },
    {
      key: "action",
      header: "",
      render: (value, row) => (
        <button
          className="text-indigo-700 border border-indigo-200 rounded-md px-3 py-1.5 bg-indigo-50 font-medium hover:bg-indigo-100 transition-colors"
          onClick={() => handleItemClick(row.visitId)}
        >
          {value}
        </button>
      ),
    },
  ];

  if (loading) return <p className="text-gray-600">Loading appointments...</p>;
  if (error) return <p className="text-red-600">Error: {error}</p>;

  return (
    <div>
      {/* ✅ Removed invalid `counts={false}` */}
      <Table data={visitData} columns={columns} />
    </div>
  );
}

export default Prescription;
