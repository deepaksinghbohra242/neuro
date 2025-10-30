import React, { useState } from "react";
import ModalWrapper from "../../common/Popup/ModalWrapper";
import uploadedDoc from '../../assets/icons/selectedDoc.svg'

interface UploadDocumentPopupProps {
  onSave: (file: File | null) => void;
  onDiscard: () => void;
}

const UploadDocumentPopup: React.FC<UploadDocumentPopupProps> = ({
  onSave,
  onDiscard,
}) => {
  const [file, setFile] = useState<File | null>(null);

  const handleFileSelect = (event: React.ChangeEvent<HTMLInputElement>) => {
    if (event.target.files && event.target.files.length > 0) {
      setFile(event.target.files[0]);
    }
  };

  const handleFileDrop = (event: React.DragEvent<HTMLDivElement>) => {
    event.preventDefault();
    if (event.dataTransfer.files && event.dataTransfer.files.length > 0) {
      setFile(event.dataTransfer.files[0]);
    }
  };

  const handleDragOver = (event: React.DragEvent<HTMLDivElement>) => {
    event.preventDefault();
  };

  const handleDelete = () => {
    setFile(null);
  };

  const formatFileSize = (bytes: number) => {
    if (bytes === 0) return '0 Bytes';
    const k = 1024;
    const sizes = ['Bytes', 'KB', 'MB', 'GB'];
    const i = Math.floor(Math.log(bytes) / Math.log(k));
    return parseFloat((bytes / Math.pow(k, i)).toFixed(2)) + ' ' + sizes[i];
  };

  return (
    <ModalWrapper onClose={onDiscard}>
      <div className="bg-white rounded-xl shadow-lg max-w-2xl w-full">
        <h2 className="text-xl font-semibold text-gray-900 border-b border-gray-200 p-4">
          Upload Document
        </h2>

        {file ? (
          <div className="p-6 mb-6 border border-gray-200 rounded-lg bg-gray-50 flex flex-col items-start gap-4">
            {/* --- FILE DETAILS --- */}
            <div className="flex items-center gap-4 w-full">
              <div className="w-12 h-12 flex items-center justify-center bg-indigo-100 rounded-lg flex-shrink-0">
                <img
                  src={uploadedDoc}
                  alt="uploadedDoc"
                  className="w-8 h-8 object-contain text-[#917BD2]"
                />
              </div>
              <div className="flex flex-col flex-grow">
                <p className="font-medium text-gray-800 break-all">{file.name}</p>
                <p className="text-sm text-gray-500">Size: {formatFileSize(file.size)}</p>
                {/* --- ACTION BUTTONS BELOW FILE --- */}
                <div className="flex flex-wrap gap-3 w-full justify-start mt-2">
                  <button
                    type="button"
                    onClick={handleDelete}
                    className="px-4 py-1.5 border border-gray-300 rounded-lg text-gray-700 bg-white 
                   hover:bg-gray-50 text-sm font-medium transition duration-150 ease-in-out"
                  >
                    Delete
                  </button>
                  <label
                    htmlFor="replace-file"
                    className="px-4 py-1.5 border border-[#785BC5] rounded-lg text-[#785BC5] bg-[#EDEAFF] 
                   hover:bg-indigo-50 text-sm font-medium transition duration-150 ease-in-out cursor-pointer"
                  >
                    Replace
                    <input
                      type="file"
                      id="replace-file"
                      onChange={handleFileSelect}
                      className="hidden"
                    />
                  </label>
                </div>
              </div>
            </div>
          </div>
        ) : (
          /* --- Dropzone (Before Upload) --- */
          <div
            onDrop={handleFileDrop}
            onDragOver={handleDragOver}
            className="border-2 border-dashed border-gray-300 rounded-lg m-6 p-6 text-center bg-gray-50"
          >
            <p className="text-gray-500 mb-4">Drop files here to upload</p>
            <p className="text-gray-500 mb-4">OR</p>
            <label
              htmlFor="file-upload"
              className="inline-flex items-center px-4 py-2 border border-gray-300 rounded-lg text-[#785BC5] bg-white 
                 hover:bg-gray-50 font-medium transition duration-150 ease-in-out cursor-pointer"
            >
              Select File
              <input
                type="file"
                id="file-upload"
                onChange={handleFileSelect}
                className="hidden"
              />
            </label>
          </div>
        )}

        {/* --- Action Buttons --- */}
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
            onClick={() => onSave(file)}
            disabled={!file}
            className={`px-6 py-2 rounded-lg text-white font-medium transition duration-150 ease-in-out 
              ${file ? "bg-[#917BD2] hover:bg-purple-500 shadow-md" : "bg-gray-400 cursor-not-allowed"}`}
          >
            Save
          </button>
        </div>
      </div>
    </ModalWrapper>
  );
};

export default UploadDocumentPopup;