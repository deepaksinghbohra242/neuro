import { Link, useLocation } from "react-router-dom";
import logo from "../assets/logo/logo-icon.png";
import homeIcon from "../assets/icons/home.svg";
import scheduleIcon from "../assets/icons/calender.svg";
import requestIcon from "../assets/icons/Vector.svg";
import patientIcon from "../assets/icons/group-people.svg";
import reportsIcon from "../assets/icons/report.svg";

interface SidebarItem {
  id: string;
  label: string;
  path: string;
  icon: string;
  alt: string;
  badge?: number;
}

interface SidebarProps {
  className?: string;
}

function Sidebar({ className = "" }: SidebarProps) {
  const location = useLocation();

  const sidebarItems: SidebarItem[] = [
    {
      id: "home",
      label: "Home",
      path: "/",
      icon: homeIcon,
      alt: "home",
    },
    {
      id: "schedule",
      label: "Schedule",
      path: "/schedule",
      icon: scheduleIcon,
      alt: "schedule",
      badge: 5,
    },
    {
      id: "requests",
      label: "Requests",
      path: "/requests",
      icon: requestIcon,
      alt: "requests",
      badge: 19, // 8 New + 3 Returned + 8 Rejected
    },
    {
      id: "patients",
      label: "Patients",
      path: "/patients",
      icon: patientIcon,
      alt: "patients",
    },
    {
      id: "reports",
      label: "Reports",
      path: "/reports",
      icon: reportsIcon,
      alt: "reports",
    },
  ];

  const isActive = (path: string) => {
    return location.pathname.includes(path);
  };

  return (
    <aside
      className={`flex flex-col gap-10 w-[120px] items-center border-r border-gray-200 h-screen py-10 ${className}`}
    >
      {/* Logo */}
      <div className="flex-shrink-0">
        <img
          src={logo}
          alt="NeuroMed Clinic Logo"
          
          className="object-contain w-[62px] h-[40px]"
        />
      </div>

      {/* Navigation Items */}
      <nav className="flex-1">
        <ul className="flex flex-col gap-10">
          {sidebarItems.map((item) => (
            <li key={item.id} className="flex flex-col items-center gap-2">
              <Link
                to={item.path}
                className={`flex flex-col items-center gap-2 p-4 rounded-lg transition-colors duration-200 relative ${
                  isActive(item.path)
                    ? "bg-[#EDEAFF] text-[#785BC5]"
                    : "text-gray-600 hover:text-gray-900 hover:bg-gray-50"
                }`}
                title={item.label}
              >
                <div className="relative">
                  <img
                    src={item.icon}
                    alt={item.alt}
                    className="w-[24px] h-[24px] object-contain"
                  />
                  {item.badge && item.badge > 0 && (
                    <span className="absolute -top-2 -right-2 bg-[#785BC5] text-white text-[12px] font-medium rounded-full w-[20px] h-[20px] flex items-center justify-center px-1">
                      {item.badge > 99 ? "99+" : item.badge}
                    </span>
                  )}
                </div>
                <span className=" font-medium text-center leading-tight text-[12px] font-[500] font-ibm">
                  {item.label}
                </span>
              </Link>
            </li>
          ))}
        </ul>
      </nav>
    </aside>
  );
}

export default Sidebar;
