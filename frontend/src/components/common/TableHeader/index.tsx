import React, { useState, useEffect } from 'react';

interface TableHeaderProps {
  onTabChange?: (activeTab: string) => void;
  counts?: {
    new: number;
    returned: number;
    rejected: number;
  };
  initialActiveTab?: string;
}

function TableHeader({ onTabChange, counts, initialActiveTab = 'New' }: TableHeaderProps) {
  const [activeTab, setActiveTab] = useState(initialActiveTab);

  const tabs = [
    { id: 'New', label: 'New', count: counts?.new || 0 },
    { id: 'Returned', label: 'Returned', count: counts?.returned || 0 },
    { id: 'Rejected', label: 'Rejected', count: counts?.rejected || 0 }
  ];

  // Update active tab when initialActiveTab changes
  useEffect(() => {
    setActiveTab(initialActiveTab);
  }, [initialActiveTab]);

  const handleTabClick = (tabId: string) => {
    setActiveTab(tabId);
    onTabChange?.(tabId);
  };

  return (
    <div className="bg-white border-b border-gray-200">
      <div className="flex">
        {tabs.map((tab) => (
          <button
            key={tab.id}
            onClick={() => handleTabClick(tab.id)}
            className={`px-6 py-4 text-sm font-medium border-b-2 transition-colors duration-200 flex items-center gap-2 ${
              activeTab === tab.id
                ? 'border-[#785BC5] text-[#785BC5] bg-[#EDEAFF]'
                : 'border-transparent text-gray-500 hover:text-gray-700 hover:border-gray-300'
            }`}
          >
            <span>{tab.label}</span>
            {tab.count > 0 && (
              <span className={`px-2 py-1 text-xs font-medium rounded-full ${
                activeTab === tab.id
                  ? 'bg-[#785BC5] text-white'
                  : 'bg-gray-200 text-gray-600'
              }`}>
                {tab.count}
              </span>
            )}
          </button>
        ))}
      </div>
    </div>
  );
}

export default TableHeader;
