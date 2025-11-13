import React from "react";
import Table from "../../common/Table";

interface PaymentHistoryData {
  id: string;
  dueDate: string;
  description: string;
  status: string;
}

interface PaymentHistoryProps {
  data: PaymentHistoryData[];
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

const PaymentHistory: React.FC<PaymentHistoryProps> = ({
  data,
  counts,
  activeTab,
  handleTabChange,
}) => {
  const paymentColumns = [
    {
      header: "#",
      key: "index",
      className: "w-12 rounded-tl-lg text-center",
      render: (_: unknown, row: PaymentHistoryData, rowIndex?: number) => {
        const index =
          rowIndex !== undefined
            ? rowIndex + 1
            : data.findIndex((d) => d.id === row.id) + 1;
        return index;
      },
    },
    {
      header: "ID",
      key: "id" as keyof PaymentHistoryData,
      className: "w-24",
    },
    {
      header: "Due Date",
      key: "dueDate" as keyof PaymentHistoryData,
    },
    {
      header: "Description",
      key: "description" as keyof PaymentHistoryData,
    },
    {
      header: "Status",
      key: "status" as keyof PaymentHistoryData,
      render: (_: unknown, row: PaymentHistoryData) => {
        const status = row.status?.toUpperCase();
        const statusClass =
          status === "PAID"
            ? "bg-[#EDFEF3] text-[#10B981] border-[#CBEBDB"
            : "bg-[#FFF2F2] text-[#EF4444] border-[#F9E2E2";

        return (
          <button
            className={`px-3 py-1.5 text-xs font-semibold rounded-md border w-[136px] ${statusClass}`}
          >
            {status}
          </button>
        );
      },
    },
  ];

  const TableHeader = (
    <div className="flex justify-between items-center mb-4">
      <h3
        className="border-l-4 pl-3 text-xl font-semibold text-gray-800 mb-3 sm:mb-0"
        style={{ borderColor: "#5B3CA1" }}
      >
        Payment History ({data?.length ?? 0})
      </h3>
      <ActionButton isPrimary>+ Add Additional Billing</ActionButton>
    </div>
  );

  return (
    <div className="mt-4">
      {TableHeader}
      <Table
        data={data || []}
        columns={paymentColumns}
        onTabChange={handleTabChange}
        counts={counts}
        initialActiveTab={activeTab}
      />
    </div>
  );
};

export default PaymentHistory;
