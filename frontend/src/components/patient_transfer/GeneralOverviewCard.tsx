import { MdOutlineEdit } from "react-icons/md";

const GeneralOverviewCard = () => {
  return (
    <div className="bg-gray-50 rounded-lg shadow-sm p-4 sm:p-6 border border-gray-100">
      <div className="flex justify-between items-center mb-4">
        <h3 className="border-l-4 pl-3 text-lg font-bold text-gray-900" style={{ borderColor: "#5B3CA1" }}>
          GENERAL OVERVIEW <span className="text-gray-500 text-sm font-normal">・ Last Updated: 12 Dec, 2023</span>
        </h3>
        <button className="flex items-center text-indigo-400 text-sm font-medium hover:text-indigo-700 transition-colors duration-200 border border-gray-300 px-3 py-1 rounded-lg gap-1 bg-white">
          <MdOutlineEdit/>
          Edit
        </button>
      </div>

      <div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-4 gap-x-8 gap-y-4 text-sm">
        <div>
          <p className="text-gray-500">Patient ID</p>
          <p className="font-semibold text-gray-800">PA035829</p>
        </div>
        <div>
          <p className="text-gray-500">Date of Birth</p>
          <p className="font-semibold text-gray-800">19 Sep, 1997 <span className="text-gray-500 ml-1">28 Years</span></p>
        </div>
        <div>
          <p className="text-gray-500">Occupation</p>
          <p className="font-semibold text-gray-800">Commercial Manager</p>
        </div>
        {/* Placeholder for Nationality, moved here to match grid */}
        <div>
          <p className="text-gray-500">Nationality</p>
          <p className="font-semibold text-gray-800">Ireland</p>
        </div>
        <div>
          <p className="text-gray-500">PPSN</p>
          <p className="font-semibold text-gray-800">XXXXXXXX124</p>
        </div>
      </div>
    </div>
  );
};

export default GeneralOverviewCard;