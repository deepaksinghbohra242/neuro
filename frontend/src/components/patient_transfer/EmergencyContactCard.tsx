import { MdOutlineEdit } from "react-icons/md";

const EmergencyContactCard = () => {
  return (
    <div className="bg-gray-50 rounded-lg shadow-sm p-4 sm:p-6 border border-gray-100">
      <div className="flex justify-between items-center mb-4">
        <h3 className="border-l-4 pl-3 text-lg font-bold text-gray-900" style={{ borderColor: "#5B3CA1" }}>
          EMERGENCY CONTACT DETAILS <span className="text-gray-500 text-sm font-normal">・ Last Updated: 12 Dec, 2023</span>
        </h3>
        <button className="flex items-center text-indigo-400 text-sm font-medium hover:text-indigo-700 transition-colors duration-200 border border-gray-300 px-3 py-1 rounded-lg gap-1 bg-white">
          <MdOutlineEdit/>
          Edit
        </button>
      </div>

      <div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-4 gap-x-8 gap-y-4 text-sm">
        <div>
          <p className="text-gray-500">Name</p>
          <p className="font-semibold text-gray-800">Tara Quinn</p>
        </div>
        <div>
          <p className="text-gray-500">Phone Number</p>
          <p className="font-semibold cursor-pointer text-blue-600 underline">+353 357 345 245</p>
        </div>
        <div>
          <p className="text-gray-500">Email Address</p>
          <p className="font-semibold cursor-pointer text-blue-600 underline">taraquinnwildheart@gmail.com</p>
        </div>
        <div>
          <p className="text-gray-500">Relation</p>
          <p className="font-semibold text-gray-800">Spouse</p>
        </div>
      </div>
    </div>
  );
};

export default EmergencyContactCard;