import { MdOutlineEdit } from "react-icons/md";

const GPDetails = () => {
  return (
    <div className="bg-gray-50 rounded-lg shadow-sm p-4 sm:p-6 border border-gray-100">
      <div className="flex justify-between items-center mb-4">
        <h3 className="border-l-4 pl-3 text-lg font-bold text-gray-900" style={{ borderColor: "#5B3CA1" }}>
          ADDRESS DETAILS <span className="text-gray-500 text-sm font-normal">・ Last Updated: 12 Dec, 2025</span>
        </h3>
        <button className="flex items-center text-indigo-400 text-sm font-medium hover:text-indigo-700 transition-colors duration-200 border border-gray-300 px-3 py-1 rounded-lg gap-1 bg-white">
          <MdOutlineEdit/>
          Edit
        </button>
      </div>

      <div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-4 gap-x-8 gap-y-4 text-sm">
        <div>
          <p className="text-gray-500">Street</p>
          <p className="font-semibold text-gray-800">42 St. Brendan’s Crescent</p>
        </div>
        <div>
          <p className="text-gray-500">City/Town</p>
          <p className="font-semibold text-gray-800">Galway</p>
        </div>
        <div>
          <p className="text-gray-500">State/Province/Region</p>
          <p className="font-semibold text-gray-800">County Galway</p>
        </div>
        <div>
          <p className="text-gray-500">Zip/Postal Code</p>
          <p className="font-semibold text-gray-800">Zip/Postal Code</p>
        </div>
      </div>
    </div>
  );
};

export default GPDetails;