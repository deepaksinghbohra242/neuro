import React from "react";
import TableHeader from "../TableHeader";
import emptyTable from "../../assets/icons/no-data.svg";
// import actionsIcon from "../../assets/icons/actions.svg";
// import arrowIcon from "../../assets/icons/arrow.svg";

interface Column<T> {
  key: keyof T;
  header: string;
  render?: (value: T[keyof T], row: T) => React.ReactNode;
  className?: string;
}

interface TableProps<T> {
  data: T[];
  columns: Column<T>[];
  className?: string;
  onRowClick?: (row: T) => void;
  loading?: boolean;
  emptyMessage?: string;
  onTabChange?: (activeTab: string) => void;
  counts?: {
    new: number;
    returned: number;
    rejected: number;
  };
  initialActiveTab?: string;
}

function Table<T extends Record<string, any>>({
  data,
  columns,
  className = "",
  onRowClick,
  loading = false,
  emptyMessage = "No data available",
  onTabChange,
  counts,
  initialActiveTab,
}: TableProps<T>) {
  if (loading) {
    return (
      <div className="flex justify-center items-center h-32">
        <div className="animate-spin rounded-full h-8 w-8 border-b-2 border-blue-500"></div>
      </div>
    );
  }

  // To maintain the overall height and prevent the layout from shifting,
  // we use 'min-h-[70vh]' on the outer container and let the content flow naturally.
  // The height is better controlled on the scrollable container.
  return (
    <div className="flex flex-col"> 
      {counts && (
        <TableHeader
          onTabChange={onTabChange}
          counts={counts}
          initialActiveTab={initialActiveTab}
        />
      )}
      <div className={`overflow-auto ${className} flex-grow`}> 
        <table className="min-w-full bg-white border border-gray-200 rounded-lg">
          <thead className="bg-gray-50 sticky top-0 z-10">
            <tr>
              {columns.map((column, index) => (
                <th
                  key={index}
                  className={`px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider ${
                    column.className || ""
                  }`}
                >
                  {column.header}
                </th>
              ))}
            </tr>
          </thead>
          <tbody className="bg-white divide-y divide-gray-200">
            {data.length === 0 ? (
              <tr className=""> 
                <td colSpan={columns.length} className="px-6 py-12">
                  <div className="flex flex-col items-center justify-center min-h-[700px]"> {/* Use min-height for empty state centering */}
                    <img
                      src={emptyTable}
                      alt="empty table"
                      className="w-[120px] h-[120px] mb-4"
                    />
                    <h1 className="text-xl font-semibold text-gray-700">
                      No data found
                    </h1>
                    <p className="text-gray-500">
                      There is no data to show you right now.
                    </p>
                  </div>
                </td>
              </tr>
            ) : (
              data.map((row, rowIndex) => (
                <tr
                  key={rowIndex}
                  onClick={() => onRowClick?.(row)}
                  className={`hover:bg-gray-50 ${
                    onRowClick ? "cursor-pointer" : ""
                  }`}
                >
                  {columns.map((column, colIndex) => (
                    <td
                      key={colIndex}
                      className={`px-6 py-4 whitespace-nowrap text-sm text-gray-900 ${
                        column.className || ""
                      }`}
                    >
                      {column.render
                        ? column.render(row[column.key], row)
                        : String(row[column.key] || "-")}
                    </td>
                  ))}
                </tr>
              ))
            )}
          </tbody>
        </table>
      </div>
    </div>
  );
}

export default Table;