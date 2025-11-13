import React from "react";

interface AuthFooterProps {
  linkText: string;
  linkAction: string;
  onLinkClick?: () => void;
  showTerms?: boolean;
}

const AuthFooter: React.FC<AuthFooterProps> = ({
  linkText,
  linkAction,
  onLinkClick,
  showTerms = true,
}) => {
  return (
    <>
      <h5 className="text-center text-sm sm:text-base font-[500] text-[#374151]">
        {linkText}{" "}
        <span
          className="text-[#3B82F6] cursor-pointer hover:underline"
          onClick={onLinkClick}
        >
          {linkAction}
        </span>
      </h5>
      {showTerms && (
        <>
          <p className="text-xs sm:text-sm text-center text-gray-600">
            By continuing to your account you agree to our
          </p>
          <div className="flex flex-wrap justify-center gap-1 text-xs sm:text-sm">
            <button className="text-[#3B82F6]  underline cursor-pointer">
              Terms & Conditions
            </button>
            <span className="text-gray-600">&</span>
            <button className="text-[#3B82F6]  underline cursor-pointer">
              Privacy Policy
            </button>
          </div>
        </>
      )}
    </>
  );
};

export default AuthFooter;
