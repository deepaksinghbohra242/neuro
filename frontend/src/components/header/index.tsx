import React, { useState, useEffect } from "react";
import { useNavigate, useLocation, useParams } from "react-router-dom";
import notificationIcon from "../assets/icons/notification.svg";
import avatarIcon from "../assets/icons/Avatar.svg";
import arrowIcon from "../assets/icons/arrow.svg";

import ConsultationHeader from "./ConsultationHeader";

interface HeaderItem {
  id: string;
  label: string;
  count?: number;
  isActive?: boolean;
  path?: string;
}

interface HeaderProps {
  className?: string;
  notificationCount?: number;
  onItemClick?: (itemId: string) => void;
}

// 💡 Helper function to determine the base path (e.g., from "/requests/appointments" -> "/requests")
const getBasePath = (path: string): string => {
  if (path.startsWith("/patients")) return "/patients";
  if (path.startsWith("/requests")) return "/requests";
  return "";
};


function NavHeader({
  className = "",
  notificationCount = 5,
  onItemClick,
}: HeaderProps) {
  const [activeItem, setActiveItem] = useState("");
  const navigate = useNavigate();
  const location = useLocation();
  const currentPath = location.pathname;

  let headerItems: HeaderItem[] = [];
  let basePath = getBasePath(currentPath);

  // 1. Determine the tabs based on the path
  if (currentPath.includes("/patients")) {
    // 💡 REMOVED 'count' property from patient items
    headerItems = [
      {
        id: "active",
        label: "Active",
        path: "/patients",
      },
      {
        id: "archive",
        label: "Archive",
        path: "/patients/archive",
      },
    ];
  } else if (currentPath.includes("/requests")) {
    // These items retain the 'count' property
    headerItems = [
      {
        id: "appointments",
        label: "New Appointments",
        count: 8,
        path: "/requests/appointments",
      },
      {
        id: "prescriptions",
        label: "Prescriptions",
        count: 19,
        path: "/requests/prescriptions",
      },
      {
        id: "transfers",
        label: "Patient Transfer",
        count: 8,
        path: "/requests/transfers",
      },
      {
        id: "general",
        label: "General",
        count: 8,
        path: "/requests/general",
      },
    ];
  }

  // 2. Use useEffect to set the active tab whenever the currentPath changes.
  useEffect(() => {
    // Find the item that matches the current path exactly
    let matchingItem = headerItems.find((item) => item.path === currentPath);

    // If no exact match AND the current path is the base path (e.g., /requests),
    // we assume the first item is the default.
    if (!matchingItem && currentPath === basePath && headerItems.length > 0) {
      // Check if the first item's path is the one the base path should default to.
      // E.g., if currentPath is "/requests" and first item path is "/requests/appointments"
      if (headerItems[0].path?.startsWith(currentPath)) {
        matchingItem = headerItems[0];
      }
    }

    if (matchingItem) {
      setActiveItem(matchingItem.id);
    }
    // eslint-disable-next-line react-hooks/exhaustive-deps
  }, [currentPath, basePath]); // Dependency array includes currentPath and basePath

  const handleItemClick = (item: HeaderItem) => {
    setActiveItem(item.id);
    onItemClick?.(item.id);

    if (item.path) {
      navigate(item.path);
    }
  };

  return (
    <header
      className={`flex justify-between items-center p-8 border-b border-gray-200 ${className}`}
    >
      {/* Navigation Items */}
      <nav>
        <ul className="flex gap-4 items-center">
          {headerItems.map((item) => (
            <li key={item.path || item.id}>
              <button
                onClick={() => handleItemClick(item)}
                className={`px-4 py-2 rounded-md text-sm font-medium transition-colors duration-200 ${
                  activeItem === item.id
                    ? "text-[#785BC5] border-[1px] border-[#785BC5] bg-[#F5F3FF]"
                    : "text-gray-600 hover:text-gray-900 hover:bg-gray-50"
                }`}
              >
                {item.label}{" "}
                {/* 💡 CONDITIONAL RENDERING: Only display count if 'item.count' is present (not undefined) */}
                {item.count !== undefined && (
                  <>({item.count.toString().padStart(2, "0")})</>
                )}
              </button>
            </li>
          ))}
        </ul>
      </nav>

      {/* Right Side Actions */}
      <div className="flex gap-4 items-center">
        {/* Notification Icon with Badge */}
        <div className="relative w-[24px] h-[24px] cursor-pointer">
          <img
            src={notificationIcon}
            alt="notifications"
            className="w-[24px] h-[24px] hover:opacity-80 transition-opacity"
          />
          {notificationCount > 0 && (
            <span className="absolute -top-2 -right-2 bg-[#FFCC00] text-white text-[12px] font-medium rounded-full w-[18px] h-[18px] flex items-center justify-center">
              {notificationCount > 99 ? "99+" : notificationCount}
            </span>
          )}
        </div>

        {/* User Profile Dropdown */}
        <div className="flex items-center gap-1 cursor-pointer hover:opacity-80 transition-opacity">
          <img
            src={avatarIcon}
            alt="user avatar"
            className="w-[30px] h-[30px] rounded-full"
          />
          <img
            src={arrowIcon}
            alt="dropdown arrow"
            className="w-[24px] h-[24px]"
          />
        </div>
      </div>
    </header>
  );
}

// --- Main Exported Component (Now directly uses hooks) ---

export default function Header(props: HeaderProps) {
  // These hooks now rely on an external <BrowserRouter> wrapper
  const location = useLocation();
  console.log(useParams(), '******location *******88888888*')
  // Check if the path includes /patients/patient_details/
  const isPatientDetailsView = location.pathname.includes("/patient_details/");

  // Conditional rendering based on the path
  if (isPatientDetailsView) {
    return <ConsultationHeader />;
  }

  // Render the regular navigation header for all other routes
  return <NavHeader {...props} />;
}
