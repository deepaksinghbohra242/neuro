import React, { useState } from "react";
import {
  Trash2,
  Plus,
  PenTool,
  Bold,
  Italic,
  Underline,
  Strikethrough,
  List,
  ListOrdered,
  ChevronUp,
  ChevronDown,
  X,
  FileText,
} from "lucide-react";

import ConsultantHeader from './ConsultantHeader'

// ===============================================
// 1. INTERFACE DEFINITIONS
// ===============================================

// Define the structure for a single medicine entry
interface Medicine {
  id: number;
  name: string;
  dose: string;
  unit: string;
  frequency: string;
}

// Define the structure for the clinical template file
interface ClinicalTemplate {
  name: string;
  size: string;
}

// Define the overall structure of the form data state
interface FormDataState {
  overview: {
    id: string;
    date: string;
    doctor: string;
    visitType: string;
    duration: string;
  };
  notes: string;
  prescriptionDuration: string;
  medicines: Medicine[];
  selectedForms: string[];
  clinicalTemplate: ClinicalTemplate | null;
}

// Define props for the Accordion component
interface AccordionProps {
  title: string;
  children: React.ReactNode;
  defaultOpen?: boolean;
}

// Define props for the MedicineInput component
interface MedicineInputProps {
  medicine: Medicine;
}

// ===============================================
// REUSABLE ACCORDION COMPONENT
// ===============================================
const Accordion: React.FC<AccordionProps> = ({
  title,
  children,
  defaultOpen = true,
}) => {
  const [isOpen, setIsOpen] = useState(defaultOpen);
  const Icon = isOpen ? ChevronUp : ChevronDown;

  return (
    <div className="bg-white p-6 rounded-lg shadow-sm border border-gray-200">
      <button
        className="w-full flex justify-between items-center text-lg font-semibold text-gray-800 cursor-pointer focus:outline-none"
        onClick={() => setIsOpen(!isOpen)}
        aria-expanded={isOpen}
      >
        <span className="uppercase tracking-wide">{title}</span>
        <Icon
          size={20}
          className="text-gray-500 transition-transform duration-300"
        />
      </button>

      <div
        className={`pt-4 mt-4 border-t border-gray-100 ${
          isOpen ? "block" : "hidden"
        }`}
      >
        {children}
      </div>
    </div>
  );
};

// ===============================================
// MAIN CONSULTATION FORM COMPONENT
// ===============================================
const NewConsultationForm: React.FC = () => {
  // 1. STATE INITIALIZATION (Full Data Set with Typescript inference)
  const [formData, setFormData] = useState<FormDataState>({
    overview: {
      id: "CA835256",
      date: "06/06/2025",
      doctor: "Dr. Chloe Gallagher",
      visitType: "Follow - Up Consultation",
      duration: "45 min",
    },
    notes:
      "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Etiam eu turpis molestie, dictum est a, mattis tellus. Sed dignissim, metus nec fringilla accumsan, risus sem sollicitudin lacus, ut interdum tellus elit sed risus. Maecenas eget condimentum velit, sit amet feugiat lectus. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Etiam eu turpis molestie, dictum est a, mattis tellus.",
    prescriptionDuration: "7 Days",
    medicines: [
      {
        id: 1,
        name: "Paracitamol",
        dose: "500",
        unit: "mg",
        frequency: "Twice Daily",
      },
      {
        id: 2,
        name: "Anti Depressants",
        dose: "05",
        unit: "mg",
        frequency: "Once at Night",
      },
    ],
    selectedForms: [
      "Blood Work",
      "Creyos Assessment",
      "General Mental Health Assessment",
      "Blood Pressure Ratings",
      "Additional Documents",
    ],
    clinicalTemplate: {
      name: "Leave Application.pdf",
      size: "176 KB",
    },
  });

  // --- HANDLERS (Typed) ---
  const handleNotesChange = (e: React.ChangeEvent<HTMLTextAreaElement>) => {
    setFormData((prev) => ({ ...prev, notes: e.target.value }));
  };
  const handleDurationChange = (e: React.ChangeEvent<HTMLSelectElement>) => {
    setFormData((prev) => ({ ...prev, prescriptionDuration: e.target.value }));
  };

  const handleMedicineChange = (
    id: number,
    field: keyof Medicine,
    value: string
  ) => {
    setFormData((prev) => ({
      ...prev,
      medicines: prev.medicines.map((med) =>
        med.id === id ? { ...med, [field]: value } : med
      ),
    }));
  };

  const handleAddMedicine = () => {
    const newId = Date.now();
    setFormData((prev) => ({
      ...prev,
      medicines: [
        ...prev.medicines,
        {
          id: newId,
          name: "",
          dose: "",
          unit: "mg",
          frequency: "",
        } as Medicine,
      ],
    }));
  };

  const handleRemoveMedicine = (idToRemove: number) => {
    setFormData((prev) => ({
      ...prev,
      medicines: prev.medicines.filter((med) => med.id !== idToRemove),
    }));
  };

  const handleRemoveForm = (formToRemove: string) => {
    setFormData((prev) => ({
      ...prev,
      selectedForms: prev.selectedForms.filter((form) => form !== formToRemove),
    }));
  };

  const handleReplaceTemplate = (e: React.ChangeEvent<HTMLInputElement>) => {
    const file = e.target.files ? e.target.files[0] : null;
    if (file) {
      setFormData((prev) => ({
        ...prev,
        clinicalTemplate: {
          name: file.name,
          size: (file.size / 1024).toFixed(0) + " KB",
        },
      }));
    }
  };

  const handleDeleteTemplate = () => {
    setFormData((prev) => ({ ...prev, clinicalTemplate: null }));
  };

  const handleAddTemplateClick = () => {
    document.getElementById("file-upload-input")?.click();
  };

  const handleSave = () => {
    console.log("Saving Final Data:", formData);
    alert("Form data saved! Check the console.");
  };
  const handleDiscard = () => {
    if (window.confirm("Are you sure?")) {
      console.log("Discarding changes.");
    }
  };

  // --- Helper Component for Medicine Input Row ---
  // Note: TypeScript enforces that all props in MedicineInputProps are provided
  const MedicineInput: React.FC<MedicineInputProps> = ({ medicine }) => (
    <div className="flex space-x-4 mb-4 items-end border-b border-gray-100 pb-4">
      <h4 className="w-1/12 text-sm font-medium text-gray-700 pt-1">
        Medicine {medicine.id}
        <span className="block text-xs text-purple-600 font-normal">
          {medicine.id === 1 && "Suggested by AI"}
        </span>
      </h4>

      {/* Medicine Name Input - Controlled */}
      <div className="w-4/12">
        <label className="text-xs font-medium text-gray-700 block mb-1">
          Medicine Name *
        </label>
        <input
          type="text"
          placeholder="Enter medicine name here"
          value={medicine.name}
          onChange={(e) =>
            handleMedicineChange(medicine.id, "name", e.target.value)
          }
          className="w-full p-2 border border-gray-300 rounded focus:border-purple-600 focus:ring-purple-600 focus:ring-1 outline-none"
        />
      </div>

      {/* Dose Input - Controlled */}
      <div className="w-2/12">
        <label className="text-xs font-medium text-gray-700 block mb-1">
          Dose *
        </label>
        <input
          type="number"
          placeholder="Dose"
          value={medicine.dose}
          onChange={(e) =>
            handleMedicineChange(medicine.id, "dose", e.target.value)
          }
          className="w-full p-2 border border-gray-300 rounded focus:border-purple-600 focus:ring-purple-600 focus:ring-1 outline-none"
        />
      </div>

      {/* Unit Select/Text - Controlled */}
      <div className="w-1/12 text-gray-500 pb-2">
        <label className="text-xs font-medium text-gray-700 block mb-1 invisible">
          Unit
        </label>
        <select
          className="w-full p-2 border border-gray-300 rounded focus:border-purple-600 focus:ring-purple-600 focus:ring-1 outline-none bg-white"
          value={medicine.unit}
          onChange={(e) =>
            handleMedicineChange(medicine.id, "unit", e.target.value)
          }
        >
          <option value="mg">mg</option>
          <option value="ml">ml</option>
        </select>
      </div>

      {/* Frequency Select - Controlled */}
      <div className="w-3/12">
        <label className="text-xs font-medium text-gray-700 block mb-1">
          Frequency *
        </label>
        <select
          className="w-full p-2 border border-gray-300 rounded focus:border-purple-600 focus:ring-purple-600 focus:ring-1 outline-none bg-white"
          value={medicine.frequency}
          onChange={(e) =>
            handleMedicineChange(medicine.id, "frequency", e.target.value)
          }
        >
          <option value="">Select frequency</option>
          <option value="Twice Daily">Twice Daily</option>
          <option value="Once at Night">Once at Night</option>
          <option value="Three Times Daily">Three Times Daily</option>
        </select>
      </div>

      {/* Action Buttons - Functional */}
      <div className="flex items-center space-x-3 w-1/12 justify-end pb-2">
        <button
          type="button"
          className="text-sm text-purple-600 hover:text-purple-800 whitespace-nowrap"
        >
          Dose Calculator
        </button>
        <button
          type="button"
          onClick={() => handleRemoveMedicine(medicine.id)}
          className="text-gray-400 hover:text-red-500 p-1 transition-colors"
        >
          <Trash2 size={18} />
        </button>
      </div>
    </div>
  );
  // --------------------------------------------------

  return (
    <div className="min-h-screen bg-gray-50 p-0">
      {/* Hidden Input for File Upload */}
      <input
        type="file"
        id="file-upload-input"
        onChange={handleReplaceTemplate}
        className="hidden"
      />

      {/* Header */}
      <ConsultantHeader />
      {/* Main Form Container */}
      <div className="flex-1 p-6 overflow-auto">
        {/* 1. OVERVIEW Section */}
        <div className="bg-white p-6 rounded-lg shadow-sm border border-gray-200">
          <h2 className="text-lg font-semibold text-gray-800 mb-4 tracking-wide">
            OVERVIEW
          </h2>
          <div className="grid grid-cols-5 gap-4 text-sm">
            {Object.entries(formData.overview).map(([key, value]) => (
              <div
                key={key}
                className="p-2 border-r border-gray-100 last:border-r-0"
              >
                <p className="uppercase text-xs font-medium text-gray-500 mb-1">
                  {key.replace(/([A-Z])/g, " $1").trim()}
                </p>
                <p className="font-semibold text-gray-700">{value}</p>
              </div>
            ))}
          </div>
        </div>

        <hr className="border-gray-300" />

        {/* 2. CONSULTATION NOTES Section */}
        <Accordion title="CONSULTATION NOTES" defaultOpen={true}>
          {/* Toolbar */}
          <div className="flex space-x-2 p-2 border border-gray-300 rounded-t-lg mb-0">
            <button type="button" className="p-1 rounded hover:bg-gray-100">
              <Bold size={18} />
            </button>
            <button type="button" className="p-1 rounded hover:bg-gray-100">
              <Italic size={18} />
            </button>
            <button type="button" className="p-1 rounded hover:bg-gray-100">
              <Underline size={18} />
            </button>
            <button type="button" className="p-1 rounded hover:bg-gray-100">
              <Strikethrough size={18} />
            </button>
            <div className="h-full border-l border-gray-300 mx-1"></div>
            <button type="button" className="p-1 rounded hover:bg-gray-100">
              <PenTool size={18} />
            </button>
            <button type="button" className="p-1 rounded hover:bg-gray-100">
              <List size={18} />
            </button>
            <button type="button" className="p-1 rounded hover:bg-gray-100">
              <ListOrdered size={18} />
            </button>
          </div>

          {/* Text Area - Controlled and Editable */}
          <textarea
            value={formData.notes}
            onChange={handleNotesChange}
            className="w-full p-4 border border-gray-300 rounded-b-lg focus:border-purple-600 focus:ring-purple-600 focus:ring-1 outline-none h-48 resize-none text-base"
            placeholder="Start writing your notes here..."
          ></textarea>
        </Accordion>

        <hr className="border-gray-300" />

        {/* 3. PRESCRIPTION Section */}
        <Accordion title="PRESCRIPTION" defaultOpen={true}>
          {/* Prescription Duration - Controlled and Editable */}
          <div className="mb-8">
            <label className="block text-sm font-medium text-gray-700 mb-2">
              Prescription Duration <span className="text-red-500">*</span>
            </label>
            <select
              value={formData.prescriptionDuration}
              onChange={handleDurationChange}
              className="w-full p-2 border border-gray-300 rounded focus:border-purple-600 focus:ring-purple-600 focus:ring-1 outline-none bg-white"
            >
              <option value="">Select duration period</option>
              <option value="7 Days">7 Days</option>
              <option value="15 Days">15 Days</option>
              <option value="30 Days">30 Days</option>
            </select>
          </div>

          {/* Medicine Rows - Dynamic and Functional */}
          <div className="space-y-2">
            {formData.medicines.map((med) => (
              <MedicineInput key={med.id} medicine={med} />
            ))}
          </div>

          {/* Add Another Medicine Button - Functional */}
          <div className="mt-8 text-center">
            <button
              type="button"
              onClick={handleAddMedicine}
              className="px-6 py-2 bg-purple-100 text-purple-600 rounded-lg font-medium hover:bg-purple-200 transition-colors flex items-center justify-center mx-auto"
            >
              <Plus size={20} className="mr-2" /> Add Another Medicine
            </button>
          </div>
        </Accordion>

        <hr className="border-gray-300" />

        {/* 4. ONLINE INTAKE FORMS Section */}
        <Accordion title="ONLINE INTAKE FORMS" defaultOpen={true}>
          <label className="block text-sm font-medium text-gray-700 mb-2">
            Forms <span className="text-red-500">*</span>
          </label>
          <div className="flex flex-wrap items-center p-2 border border-gray-300 rounded focus-within:border-purple-600 focus-within:ring-1 relative bg-white">
            {/* Display Selected Forms as Tags - Functional */}
            {formData.selectedForms.map((form) => (
              <div
                key={form}
                className="flex items-center bg-purple-100 text-purple-800 text-sm font-medium px-3 py-1 mr-2 mb-2 rounded"
              >
                {form}
                <button
                  type="button"
                  onClick={() => handleRemoveForm(form)}
                  className="ml-2 text-purple-600 hover:text-purple-900 transition-colors"
                >
                  <X size={14} />
                </button>
              </div>
            ))}

            <select className="flex-1 min-w-[100px] bg-transparent p-0 border-none focus:ring-0 appearance-none">
              <option value="">Select or type forms...</option>
            </select>
            <ChevronDown size={18} className="text-gray-500" />
          </div>
        </Accordion>

        <hr className="border-gray-300" />

        {/* 5. CLINICAL TEMPLATE Section */}
        <Accordion title="CLINICAL TEMPLATE" defaultOpen={true}>
          {formData.clinicalTemplate ? (
            // Display File Card when a template is present - Functional
            <div className="flex items-center space-x-4 p-4 bg-gray-50 rounded-lg border border-gray-200">
              <div className="bg-white p-3 rounded-lg flex-shrink-0 border border-gray-300">
                <FileText size={28} className="text-purple-600" />
              </div>
              <div>
                <p className="font-medium text-gray-800">
                  {formData.clinicalTemplate.name}
                </p>
                <p className="text-sm text-gray-500">
                  Size: {formData.clinicalTemplate.size}
                </p>
              </div>
              <div className="flex space-x-3 ml-auto">
                <button
                  type="button"
                  onClick={handleDeleteTemplate}
                  className="px-3 py-1 text-sm text-red-600 border border-gray-300 rounded hover:bg-gray-100 transition-colors"
                >
                  Delete
                </button>
                <button
                  type="button"
                  onClick={handleAddTemplateClick}
                  className="px-3 py-1 text-sm text-purple-600 border border-gray-300 rounded hover:bg-gray-100 transition-colors"
                >
                  Replace
                </button>
              </div>
            </div>
          ) : (
            // Add Template Button - Functional
            <div className="mt-4 text-center">
              <button
                type="button"
                onClick={handleAddTemplateClick}
                className="w-full px-6 py-2 bg-purple-100 text-purple-600 rounded-lg font-medium hover:bg-purple-200 transition-colors flex items-center justify-center"
              >
                <Plus size={20} className="mr-2" /> Add Template
              </button>
            </div>
          )}
        </Accordion>
      </div>
    </div>
  );
};

export default NewConsultationForm;
