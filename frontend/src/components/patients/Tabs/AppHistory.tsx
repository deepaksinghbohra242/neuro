import React from "react";
import Table from "../../common/Table";

interface AppHistoryData {
  id: string;
  date: string;
  doctorName: string;
  visitType: string;
  status: string;
  duration: string;
}

interface AppHistoryProps {
  data: AppHistoryData[];
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
}) => (
  <button
    className={`px-4 py-2 rounded-lg font-medium transition-colors duration-200 cursor-pointer ${
      isPrimary
        ? "bg-[#917BD2] text-white hover:bg-purple-700"
        : "bg-white text-gray-700 border border-gray-300 hover:bg-gray-50"
    } ${className}`}
  >
    {children}
  </button>
);

const AppHistory: React.FC<AppHistoryProps> = ({
  data,
  counts,
  activeTab,
  handleTabChange,
}) => {
  const appColumns = [
    {
      header: "#",
      key: "index",
      className: "w-12 rounded-tl-lg text-center",
      render: (_: unknown, row: AppHistoryData, rowIndex?: number) => {
        const index =
          rowIndex !== undefined
            ? rowIndex + 1
            : data.findIndex((d) => d.id === row.id) + 1;
        return index;
      },
    },
    {
      header: "ID",
      key: "id" as keyof AppHistoryData,
      className: "w-24",
    },
    {
      header: "Date",
      key: "date" as keyof AppHistoryData,
    },
    {
      header: "Doctor’s Name",
      key: "doctorName" as keyof AppHistoryData,
    },
    {
      header: "Visit Type",
      key: "visitType" as keyof AppHistoryData,
    },
    {
      header: "Status",
      key: "status" as keyof AppHistoryData,
      render: (_: unknown, row: AppHistoryData) => {
        const status = row.status?.toUpperCase();

        const statusClass =
          status === "SCHEDULED"
            ? "bg-[#EEF8FF] text-[#3B82F6] border-[#CEE0EF"
            : status === "EMERGENCY"
            ? "bg-[#FFEADC] text-[#F97316] border-[#FBDAC2]"
            : "bg-gray-100 text-gray-600 border-gray-200";

        return (
          <button
            className={`px-3 py-1.5 text-xs font-semibold rounded-md border ${statusClass}`}
          >
            {status}
          </button>
        );
      },
    },
    {
      header: "Duration",
      key: "duration" as keyof AppHistoryData,
    },
  ];

  const TableHeader = (
    <div className="flex justify-between items-center mb-4">
      <h3
        className="border-l-4 pl-3 text-xl font-semibold text-gray-800 mb-3 sm:mb-0"
        style={{ borderColor: "#5B3CA1" }}
      >
        Appointment History ({data?.length ?? 0})
      </h3>
      <ActionButton isPrimary>+ Schedule Follow-Up</ActionButton>
    </div>
  );

  return (
    <div className="mt-4">
      {TableHeader}

      <Table
        data={data || []}
        columns={appColumns}
        onTabChange={handleTabChange}
        counts={counts}
        initialActiveTab={activeTab}
      />
    </div>
  );
};

export default AppHistory;
