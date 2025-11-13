import React from "react";
import Header from "../../header";
import Sidebar from "../../sidebar";
import { useLocation } from "react-router-dom";

interface DashboardLayoutProps {
  children: React.ReactNode;
}

function DashboardLayout({ children }: DashboardLayoutProps) {
  const location = useLocation();
  const isPatientDetailsView = location.pathname.includes("/patient_details/");

  return (
    <div className="flex h-screen bg-gray-50">
      {/* Sidebar */}
      {!isPatientDetailsView && <Sidebar />}

      {/* Main Content Area */}
      <div className="flex-1 flex flex-col">
        {/* Header */}
        <Header />

        {/* Page Content */}
        <main className="flex-1 p-6 overflow-auto">{children}</main>
      </div>
    </div>
  );
}

export default DashboardLayout;
