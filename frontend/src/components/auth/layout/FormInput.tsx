import React, { useState } from "react";

interface FormInputProps {
  id: string;
  name: string;
  type: string;
  label: string;
  placeholder: string;
  value: string;
  onChange: (e: React.ChangeEvent<HTMLInputElement>) => void;
  error?: string;
  required?: boolean;
  colSpan?: "1" | "2";
  showPasswordToggle?: boolean;
}

const FormInput: React.FC<FormInputProps> = ({
  id,
  name,
  type,
  label,
  placeholder,
  value,
  onChange,
  error,
  required = false,
  colSpan = "1",
  showPasswordToggle = false,
}) => {
  const [showPassword, setShowPassword] = useState(false);
  const [inputType, setInputType] = useState(type);

  const togglePasswordVisibility = () => {
    setShowPassword(!showPassword);
    setInputType(showPassword ? "password" : "text");
  };

  return (
    <div className={`flex flex-col  ${colSpan === "2" ? "col-span-1 sm:col-span-2" : ""}`}>
      <label
        htmlFor={id}
        className="text-[14px]  font-medium text-gray-700"
      >
        {label} {required && <span className="text-[#FF0000]">*</span>}
      </label>
      <div className="relative">
        <input
          type={inputType}
          id={id}
          name={name}
          value={value}
          onChange={onChange}
          placeholder={placeholder}
          className={`border border-[#E5E7EB] rounded-md p-2 sm:p-3 bg-white text-sm sm:text-base w-full ${
            error ? "border-red-500" : "border-[#4f368bd1]"
          } ${showPasswordToggle ? "pr-10" : ""}`}
        />
        {showPasswordToggle && (
          <button
            type="button"
            onClick={togglePasswordVisibility}
            className="absolute inset-y-0 right-0 pr-3 flex items-center text-gray-400 hover:text-gray-600"
          >
            {showPassword ? (
              // Eye slash icon (hide password)
              <svg
                className="h-5 w-5"
                fill="none"
                stroke="currentColor"
                viewBox="0 0 24 24"
                xmlns="http://www.w3.org/2000/svg"
              >
                <path
                  strokeLinecap="round"
                  strokeLinejoin="round"
                  strokeWidth={2}
                  d="M13.875 18.825A10.05 10.05 0 0112 19c-4.478 0-8.268-2.943-9.543-7a9.97 9.97 0 011.563-3.029m5.858.908a3 3 0 114.243 4.243M9.878 9.878l4.242 4.242M9.878 9.878L3 3m6.878 6.878L21 21"
                />
              </svg>
            ) : (
              // Eye icon (show password)
              <svg
                className="h-5 w-5"
                fill="none"
                stroke="currentColor"
                viewBox="0 0 24 24"
                xmlns="http://www.w3.org/2000/svg"
              >
                <path
                  strokeLinecap="round"
                  strokeLinejoin="round"
                  strokeWidth={2}
                  d="M15 12a3 3 0 11-6 0 3 3 0 016 0z"
                />
                <path
                  strokeLinecap="round"
                  strokeLinejoin="round"
                  strokeWidth={2}
                  d="M2.458 12C3.732 7.943 7.523 5 12 5c4.478 0 8.268 2.943 9.542 7-1.274 4.057-5.064 7-9.542 7-4.477 0-8.268-2.943-9.542-7z"
                />
              </svg>
            )}
          </button>
        )}
      </div>
      {error && (
        <span className="text-[#EF4444] text-[12px] ">{error}</span>
      )}
    </div>
  );
};

export default FormInput;
