import React, { useState, ChangeEvent, FormEvent } from "react";

// --- Type Definitions ---

/** Defines the structure for a select option. */
interface SelectOption {
  value: string;
  label: string;
}

/** Defines the structure for a single medicine item in the prescription. */
interface MedicineItemData {
  name: string;
  dose: string;
  frequency: string;
}

/** Defines the overall structure for the non-medicine form state. */
interface PrescriptionFormData {
  prescriptionId: string;
  dateOfPrescription: string;
  prescribedBy: string;
  prescriptionDuration: string;
}

// --- Reusable Input Component Props ---
interface FormInputProps {
  label: string;
  id: keyof PrescriptionFormData | string; // Use string for medicine inputs
  value: string;
  onChange: (e: ChangeEvent<HTMLInputElement>) => void;
  placeholder?: string;
  type?: "text" | "number" | "email" | "password";
  required?: boolean;
  unit?: string;
}

/** Reusable Input Component (Functional Component) */
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
        className="block w-full rounded-lg border border-gray-300 bg-white p-2.5 text-gray-900 shadow-sm focus:border-indigo-500 focus:ring-indigo-500 sm:text-sm"
      />
      {unit && <span className="ml-2 text-gray-500 text-sm">{unit}</span>}
    </div>
  </div>
);

// --- Reusable Select Component Props ---
interface FormSelectProps {
  label: string;
  id: keyof PrescriptionFormData | string;
  value: string;
  onChange: (e: ChangeEvent<HTMLSelectElement>) => void;
  options: SelectOption[];
  required?: boolean;
  placeholder?: string;
}

/** Reusable Select Component (Functional Component) */
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
      // appearance-none for custom arrow
      className="block w-full rounded-lg border border-gray-300 bg-white p-2.5 text-gray-900 shadow-sm focus:border-indigo-500 focus:ring-indigo-500 sm:text-sm appearance-none pr-10"
    >
      <option value="" disabled>
        {placeholder}
      </option>
      {options.map((option) => (
        <option key={option.value} value={option.value}>
          {option.label}
        </option>
      ))}
    </select>
  </div>
);

// --- Medicine Item Component Props ---
interface MedicineItemProps {
  medicine: MedicineItemData;
  index: number;
  onChange: (
    index: number,
    field: keyof MedicineItemData,
    value: string
  ) => void;
  onRemove: (index: number) => void;
}

/** Medicine Item Component (for reusability) */
const MedicineItem: React.FC<MedicineItemProps> = ({
  medicine,
  index,
  onChange,
  onRemove,
}) => {
  // Dummy options for Frequency
  const frequencyOptions: SelectOption[] = [
    { value: "once_daily", label: "Once a Daily" },
    { value: "twice_daily", label: "Twice a Daily" },
    { value: "thrice_daily", label: "Thrice a Daily" },
    { value: "once_in_the_morning", label: "Once in the morning" },
    { value: "once_in_the_afternoon", label: "Once in the Afternoon" },
    { value: "once_in_the_night", label: "Once in the Night" },
    { value: "once_in_the_week", label: "Once in the Week" },
  ];

  return (
    <div className="p-4 bg-white rounded-lg border border-gray-200 mt-4 shadow-sm">
      <div className="flex justify-between items-center mb-4">
        <h4 className="text-base font-semibold text-gray-900">
          Medicine {index + 1}
          {index === 0 && (
            <span className="ml-2 text-xs font-normal text-indigo-600 bg-indigo-50 px-2 py-0.5 rounded-full inline-flex items-center">
              <svg
                className="w-3 h-3 mr-1"
                fill="currentColor"
                viewBox="0 0 20 20"
                xmlns="http://www.w3.org/2000/svg"
              >
                <path
                  d="M10 2a8 8 0 100 16 8 8 0 000-16zM8.707 11.707a1 1 0 001.414 0L12 10.414l1.879 1.879a1 1 0 101.414-1.414L13.414 9l1.879-1.879a1 1 0 10-1.414-1.414L12 7.586 10.121 5.707a1 1 0 00-1.414 1.414L10.586 9 8.707 10.879a1 1 0 000 1.414z"
                  fillRule="evenodd"
                  clipRule="evenodd"
                ></path>
              </svg>
              Suggested by AI
            </span>
          )}
        </h4>
        <div className="flex items-center space-x-3">
          <button
            type="button"
            className="text-sm text-indigo-600 hover:text-indigo-800 font-medium"
          >
            Dose Calculator
          </button>
          {index > 0 && (
            <button
              type="button"
              onClick={() => onRemove(index)}
              className="text-red-500 hover:text-red-700"
            >
              <svg
                className="w-5 h-5"
                fill="none"
                stroke="currentColor"
                viewBox="0 0 24 24"
                xmlns="http://www.w3.org/2000/svg"
              >
                <path
                  strokeLinecap="round"
                  strokeLinejoin="round"
                  strokeWidth="2"
                  d="M19 7l-.867 12.142A2 2 0 0116.138 21H7.862a2 2 0 01-1.995-1.858L5 7m5 4v6m4-6v6m1-10V4a1 1 0 00-1-1h-4a1 1 0 00-1 1v3M4 7h16"
                ></path>
              </svg>
            </button>
          )}
        </div>
      </div>

      <div className="grid grid-cols-1 md:grid-cols-3 gap-4">
        <FormInput
          label="Medicine Name"
          id={`medicine-name-${index}`}
          value={medicine.name}
          onChange={(e) => onChange(index, "name", e.target.value)}
          required
        />
        <FormInput
          label="Dose"
          id={`medicine-dose-${index}`}
          value={medicine.dose}
          // The Dose value is treated as a string in state, but the input type is number
          onChange={(e) => onChange(index, "dose", e.target.value)}
          required
          unit="mg"
          type="number"
        />
        <FormSelect
          label="Frequency"
          id={`medicine-frequency-${index}`}
          value={medicine.frequency}
          onChange={(e) => onChange(index, "frequency", e.target.value)}
          options={frequencyOptions}
          required
          placeholder="Select Frequency"
        />
      </div>
    </div>
  );
};

// --- Main Component Props ---
interface AddNewPrescriptionsProps {
  onSave?: (data: PrescriptionFormData, medicines: MedicineItemData[]) => void;
  onDiscard?: () => void;
}

/** Main Component */
const AddNewPrescriptions: React.FC<AddNewPrescriptionsProps> = ({
  onSave,
  onDiscard,
}) => {
  // --- Form State ---
  const [formData, setFormData] = useState<PrescriptionFormData>({
    prescriptionId: "PR826493",
    dateOfPrescription: "28/12/2025",
    prescribedBy: "Dr. Chloe Gallagher",
    prescriptionDuration: "",
  });

  const [medicines, setMedicines] = useState<MedicineItemData[]>([
    { name: "Paracitamol", dose: "500", frequency: "twice_daily" },
  ]);

  // Dummy options for Duration
  const durationOptions: SelectOption[] = [
    { value: "1_days", label: "1 Days" },
    { value: "7_days", label: "7 Days" },
    { value: "15_days", label: "15 Days" },
    { value: "20_days", label: "20 Days" },
    { value: "30_days", label: "30 Days" },
  ];

  // --- Handlers ---
  const handleFormChange = (
    e: ChangeEvent<HTMLInputElement | HTMLSelectElement>
  ) => {
    const { id, value } = e.target;
    // Type assertion to ensure 'id' is a key of PrescriptionFormData
    setFormData((prev) => ({ ...prev, [id as keyof PrescriptionFormData]: value }));
  };

  const handleMedicineChange = (
    index: number,
    field: keyof MedicineItemData,
    value: string
  ) => {
    setMedicines((prev) =>
      prev.map((medicine, i) =>
        i === index ? { ...medicine, [field]: value } : medicine
      )
    );
  };

  const handleAddMedicine = () => {
    setMedicines((prev) => [
      ...prev,
      // Empty new medicine item
      { name: "", dose: "", frequency: "" },
    ]);
  };

  const handleRemoveMedicine = (indexToRemove: number) => {
    setMedicines((prev) => prev.filter((_, index) => index !== indexToRemove));
  };

  const handleSubmit = (e: FormEvent) => {
    e.preventDefault();
    console.log("Form Submitted in :", { formData, medicines });
    // Add logic for API call/data saving here
    if (onSave) {
      onSave(formData, medicines);
    }
  };

  return (
    <div className="w-full max-w-4xl bg-white rounded-xl shadow-2xl overflow-hidden">
      {/* Header */}
      <div className="px-6 py-4 border-b border-gray-200">
        <h2 className="text-xl font-bold text-gray-900">
          Add New Prescription
        </h2>
      </div>

      {/* Form Body */}
      <form onSubmit={handleSubmit} className="p-6 space-y-6">
        {/* Prescription Info Grid */}
        <div className="grid grid-cols-1 md:grid-cols-2 gap-4">
          <FormInput
            label="Prescription ID"
            id="prescriptionId"
            value={formData.prescriptionId}
            onChange={handleFormChange}
            placeholder="e.g., PR826493"
          />
          <FormInput
            label="Date of Prescription"
            id="dateOfPrescription"
            value={formData.dateOfPrescription}
            onChange={handleFormChange}
            placeholder="DD/MM/YYYY"
          />
          <FormInput
            label="Prescribed By"
            id="prescribedBy"
            value={formData.prescribedBy}
            onChange={handleFormChange}
            placeholder="e.g., Dr. Jane Doe"
          />
          <FormSelect
            label="Prescription Duration"
            id="prescriptionDuration"
            value={formData.prescriptionDuration}
            onChange={handleFormChange}
            options={durationOptions}
            required
            placeholder="Select duration period"
          />
        </div>

        {/* Medicine Section */}
        <div className="p-4 rounded-lg bg-gray-50 border border-gray-100">
          {medicines.map((medicine, index) => (
            <MedicineItem
              key={index}
              index={index}
              medicine={medicine}
              onChange={handleMedicineChange}
              onRemove={handleRemoveMedicine}
            />
          ))}

          {/* Add Another Medicine Button */}
          <div className="mt-4">
            <button
              type="button"
              onClick={handleAddMedicine}
              className="w-full flex items-center justify-center p-3 text-indigo-600 bg-indigo-50 hover:bg-indigo-100 rounded-lg font-medium transition duration-150 ease-in-out"
            >
              <svg
                className="w-5 h-5 mr-2"
                fill="none"
                stroke="currentColor"
                viewBox="0 0 24 24"
                xmlns="http://www.w3.org/2000/svg"
              >
                <path
                  strokeLinecap="round"
                  strokeLinejoin="round"
                  strokeWidth="2"
                  d="M12 6v6m0 0v6m0-6h6m-6 0H6"
                ></path>
              </svg>
              Add Another Medicine
            </button>
          </div>
        </div>

        {/* Action Buttons */}
        <div className="flex justify-end space-x-3 pt-4">
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
      </form>
    </div>
  );
};

export default AddNewPrescriptions;