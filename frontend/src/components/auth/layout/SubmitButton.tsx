import React from "react";

interface SubmitButtonProps {
  isSubmitting: boolean;
  submittingText: string;
  defaultText: string;
  className?: string;
}

const SubmitButton: React.FC<SubmitButtonProps> = ({
  isSubmitting,
  submittingText,
  defaultText,
  className = "",
}) => {
  return (
    <div className="col-span-1 sm:col-span-2 pt-4 sm:pt-5">
      <button
        type="submit"
        disabled={isSubmitting}
        className={`px-4 py-2 sm:py-3 rounded-md w-full transition-colors text-sm sm:text-base font-medium ${
          isSubmitting
            ? "bg-gray-400 text-white cursor-not-allowed"
            : "bg-[#5B3CA1] text-white hover:bg-[#4A2F8A]"
        } ${className}`}
      >
        {isSubmitting ? submittingText : defaultText}
      </button>
    </div>
  );
};

export default SubmitButton;
