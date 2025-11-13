import ChevronLeft from "../assets/icons/arrow-chevron-left.svg";
import ChevronRight from "../assets/icons/arrow-chevron-right.svg";
import { useNavigate } from "react-router-dom";
const customPurple = "rgb(109, 83, 178)";

const CorrectedHeader = ({
  consultationId = "CA835256",
  handleDiscard,
  handleSave,
  onGoBack,
  isSaveDisabled = true,
}) => {
  const navigate = useNavigate();

  const handleItemClick = (requestUrl: string) => {
    // Navigates to a specific request detail page, e.g., /transfers/RQ356356
    console.log(`Navigating to review request: ${requestUrl}`);
    // Example navigation path (adjust as needed for your routing):
    navigate(`${requestUrl}`);
  };

  return (
    <header className="fixed top-0 left-0 right-0 h-16 bg-white border-b border-gray-200 shadow-sm flex items-center justify-between px-8 z-10">
      {/* 1. Left Section: Back Button and Text */}
      <div className="flex items-center text-lg font-medium">
        {/* Back Button (matching the visual style) */}
        <button
          onClick={onGoBack}
          className="mr-3 p-2 bg-white border border-gray-300 rounded-lg text-gray-700 hover:bg-gray-50 transition-colors"
          aria-label="Go back"
        >
          <img
            src={ChevronLeft}
            alt="user avatar"
            className="w-4 h-4"
            onClick={() =>
              handleItemClick("/patients/patient_details/PA357656/")
            }
          />
        </button>

        {/* Text/Breadcrumb Section */}
        <div className="flex items-center text-sm font-medium">
          <span className="text-gray-500 uppercase tracking-wider">
            ADD CONSULTATION
          </span>
          <span className="mx-2 text-gray-300 font-normal">
            <img src={ChevronRight} alt="user avatar" className="w-4 h-4" />
          </span>{" "}
          <span
            className="text-purple-700 font-semibold"
            style={{ color: customPurple }}
          >
            {'PA357656'}
          </span>
        </div>
      </div>

      {/* 2. Right Section: Action Buttons */}
      <div className="space-x-4">
        <button
          type="button"
          onClick={handleDiscard}
          className="px-6 py-2 text-purple-700 border border-gray-300 rounded-lg hover:bg-purple-50 transition-colors shadow-sm"
        >
          Discard
        </button>
        <button
          type="button"
          onClick={handleSave}
          disabled={isSaveDisabled}
          className={`px-6 py-2 rounded-lg transition-colors shadow-sm ${
            isSaveDisabled
              ? "bg-gray-200 text-gray-500 cursor-not-allowed"
              : "bg-purple-600 text-white hover:bg-purple-700"
          }`}
        >
          Save
        </button>
      </div>
    </header>
  );
};

export default CorrectedHeader;
