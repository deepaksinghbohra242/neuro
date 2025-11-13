import React, { useState, type ChangeEvent, type FormEvent } from "react";

interface SelectOption {
  value: string;
  label: string;
}

interface TestFormData {
  testId: string;
  documentId: string;
}

interface FormInputProps {
  label: string;
  id: keyof TestFormData | string;
  value: string;
  onChange: (e: ChangeEvent<HTMLInputElement>) => void;
  placeholder?: string;
  type?: "text" | "number" | "email" | "password";
  required?: boolean;
  unit?: string;
}

const FormInput: React.FC<FormInputProps> = ({
  label,
  id,
  value,
  onChange,
  placeholder,
  type = "text",
  required = false,
  unit,
}) => (
  <div className="flex flex-col space-y-1">
    <label htmlFor={id} className="text-sm font-medium text-gray-700">
      {label} {required && <span className="text-red-500">*</span>}
    </label>
    <div className="flex items-center">
      <input
        type={type}
        id={id}
        value={value}
        onChange={onChange}
        placeholder={placeholder}
        required={required}
        className="block w-full rounded-lg border border-gray-300 bg-white p-2.5 text-gray-900 shadow-sm 
                   focus:border-indigo-500 focus:ring-indigo-500 sm:text-sm"
      />
      {unit && <span className="ml-2 text-gray-500 text-sm">{unit}</span>}
    </div>
  </div>
);

interface FormSelectProps {
  label: string;
  id: keyof TestFormData | string;
  value: string;
  onChange: (e: ChangeEvent<HTMLSelectElement>) => void;
  options: SelectOption[];
  required?: boolean;
  placeholder?: string;
}

const FormSelect: React.FC<FormSelectProps> = ({
  label,
  id,
  value,
  onChange,
  options,
  required = false,
  placeholder,
}) => (
  <div className="flex flex-col space-y-1">
    <label htmlFor={id} className="text-sm font-medium text-gray-700">
      {label} {required && <span className="text-red-500">*</span>}
    </label>
    <select
      id={id}
      value={value}
      onChange={onChange}
      required={required}
      className="block w-full rounded-lg border border-gray-300 bg-white p-2.5 text-gray-900 shadow-sm 
                 focus:border-indigo-500 focus:ring-indigo-500 sm:text-sm appearance-none pr-10"
    >
      <option value="" disabled>
        {placeholder || "Select an option"}
      </option>
      {options.map((option) => (
        <option key={option.value} value={option.value}>
          {option.label}
        </option>
      ))}
    </select>
  </div>
);

interface AddNewTestProps {
  onSave?: (data: TestFormData) => void;
  onDiscard?: () => void;
}

const AddNewTest: React.FC<AddNewTestProps> = ({ onSave, onDiscard }) => {
  const [formData, setFormData] = useState<TestFormData>({
    testId: "",
    documentId: "",
  });

  const testOptions: SelectOption[] = [
    { value: "test_1", label: "Blood Test" },
    { value: "test_2", label: "X-Ray" },
    { value: "test_3", label: "CT Scan" },
  ];

  const handleChange = (
    e: ChangeEvent<HTMLInputElement | HTMLSelectElement>
  ) => {
    const { id, value } = e.target;
    setFormData((prev) => ({ ...prev, [id]: value }));
  };

  const handleSubmit = (e: FormEvent) => {
    e.preventDefault();
    if (onSave) onSave(formData);
  };

  return (
    <div className="w-full max-w-4xl bg-white rounded-xl shadow-2xl overflow-hidden">
      {/* Header */}
      <div className="px-6 py-4 border-b border-gray-200">
        <h2 className="text-xl font-bold text-gray-900">Assign New Test</h2>
      </div>

      {/* Form */}
      <form onSubmit={handleSubmit} className="space-y-6">
        <div className="grid grid-cols-1 md:grid-cols-2 gap-4 p-4">
          <FormSelect
            label="Test / Document"
            id="testId"
            value={formData.testId}
            onChange={handleChange}
            options={testOptions}
            required
            placeholder="Select test"
          />
          <FormInput
            label="Test / Document ID"
            id="documentId"
            value={formData.documentId}
            onChange={handleChange}
            placeholder="Enter document id"
            required
          />
        </div>

        {/* Action Buttons */}
        <div className="flex justify-end space-x-3 p-4 border-t border-gray-200">
          <button
            type="button"
            onClick={onDiscard}
            className="px-6 py-2 border border-gray-300 rounded-lg text-gray-700 bg-white 
                       hover:bg-gray-50 font-medium transition duration-150 ease-in-out"
          >
            Discard
          </button>
          <button
            type="submit"
            className="px-6 py-2 bg-[#917BD2] rounded-lg text-white hover:bg-purple-500 
                       shadow-md font-medium transition duration-150 ease-in-out"
          >
            Confirm
          </button>
        </div>
      </form >
    </div>
  );
};

export default AddNewTest;
