import React from "react";
import Table from "../../common/Table";
import actionsIcon from "../../assets/icons/Actions.svg";
import downloadIcon from "../../assets/icons/Download.svg";
import { useNavigate} from "react-router-dom";

interface ConstHistoryData {
  id: string;
  date: string;
  attendingDoctor: string;
  visitType: string;
  duration: string;
}

interface ConstHistoryProps {
  data: ConstHistoryData[];
  counts?: boolean;
  activeTab?: string;
  handleTabChange?: (tab: string) => void;
}

interface ActionButtonProps {
  children: React.ReactNode;
  className?: string;
  isPrimary?: boolean;
  onClick: () => void;
}

const ActionButton: React.FC<ActionButtonProps> = ({
  children,
  className = "",
  isPrimary = false,
  onClick,

}) => (
  <button
    onClick={onClick}
    className={`px-4 py-2 rounded-lg font-medium transition-colors duration-200 cursor-pointer ${isPrimary
      ? "bg-[#917BD2] text-white hover:bg-purple-700"
      : "bg-white text-gray-700 border border-gray-300 hover:bg-gray-50"
      } ${className}`}
  >
    {children}
  </button>
);

const ConstHistory: React.FC<ConstHistoryProps> = ({
  data,
  counts,
  activeTab,
  handleTabChange,
}) => {
  
  const navigate = useNavigate();
  const testColumns = [
    {
      header: "#",
      key: "index",
      className: "w-12 rounded-tl-lg text-center",
      render: (_: unknown, row: ConstHistoryData, rowIndex?: number) => {
        const index =
          rowIndex !== undefined
            ? rowIndex + 1
            : data.findIndex((d) => d.id === row.id) + 1;
        return index;
      },
    },
    {
      header: "ID",
      key: "id" as keyof ConstHistoryData,
      className: "w-24",
    },
    {
      header: "Date",
      key: "date" as keyof ConstHistoryData,
    },
    {
      header: "attending Doctor",
      key: "attendingDoctor" as keyof ConstHistoryData,
    },
    {
      header: "visit Type",
      key: "visitType" as keyof ConstHistoryData,
      // className: "text-center",
    },
    {
      header: "duration",
      key: "duration" as keyof ConstHistoryData,
    },
    {
      header: "",
      key: "actions",
      className: "rounded-tr-lg w-32 text-center",
      render: (_: unknown, row: ConstHistoryData) => (
        <div className="flex justify-center space-x-2">
          <button
            className="text-gray-400 hover:text-purple-600 p-1 rounded-full hover:bg-purple-50 transition-colors cursor-pointer"
            onClick={() => console.log("View", row.id)}
          >
            <img src={actionsIcon} alt="View Test Details" className="h-6 w-6" />
          </button>
          <button
            className="text-gray-400 hover:text-purple-600 p-1 rounded-full hover:bg-purple-50 transition-colors cursor-pointer"
            onClick={() => console.log("Download", row.id)}
          >
            <img src={downloadIcon} alt="Download Test" className="h-6 w-6" />
          </button>
        </div>
      ),
    },
  ];
  
  const handleNewConsultationClick = () => {
    navigate(`/patients/patient_details/PA357656/consultation_history`)
  };

  const TableHeader = (
    <div className="flex justify-between items-center mb-4">
      <h3 className="border-l-4 pl-3 text-xl font-semibold text-gray-800 mb-3 sm:mb-0" style={{ borderColor: "#5B3CA1" }}>
        Consultation History ({data?.length ?? 0})
      </h3>
      <ActionButton isPrimary onClick={() => handleNewConsultationClick()}>+ Add Consultation</ActionButton>
    </div>
  );

  return (
    <div className="mt-4">
      {TableHeader}

      <Table
        data={data || []}
        columns={testColumns}
        onTabChange={handleTabChange}
        counts={counts}
        initialActiveTab={activeTab}
      />
    </div>
  );
};

export default ConstHistory;
