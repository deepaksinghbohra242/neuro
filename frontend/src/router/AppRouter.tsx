// AppRouter.tsx (CORRECTED)
import React from "react";
// 🚨 REMOVE: { BrowserRouter as Router, ... }
import { Routes, Route, Navigate } from "react-router-dom"; // ONLY import routing components needed

import SignIn from "../components/auth/signIn";
import SignUp from "../components/auth/signUp";
import ForgotPassword from "../components/auth/forgotPassword";
import ResetPassword from "../components/auth/resetPassword";
import Prescription from "../components/prescriptions";
import PrescriptionNew from "../components/prescriptions/PrescriptionNew";
import PrescriptionReturned from "../components/prescriptions/PrescriptionReturned";
import PrescriptionRejected from "../components/prescriptions/PrescriptionRejected";

import Appointments from "../components/appointments/index";
import AppointmentDetail from "../components/appointments/AppointmentDetail";

import TransferRequestTable from '../components/patient_transfer/index'
import PatientTransferReview from '../components/patient_transfer/PatientTransferReview'

import General from "../components/general/index";
import RequestReviewPage from '../components/general/RequestReviewPage';

import PatientDashboard  from '../components/patients/PatientDashboard'
import PatientDetails from '../components/patients/PatientDetails'
import PrescriptionPage from "../components/prescriptions/PrescriptionPage";
import TestPage from "../components/prescriptions/TestPage";

import NewConsultationForm from "../components/consultation_history/NewConsultationForm";


const AppRouter: React.FC = () => {
  return (
    // 🚨 REMOVE THE <Router> WRAPPER HERE!
    <div className="min-h-screen bg-gray-50">
      <Routes>
        {/* Public/Auth Routes */}
        <Route path="/signin" element={<SignIn />} />
        <Route path="/signup" element={<SignUp />} />
        <Route path="/forgot-password" element={<ForgotPassword />} />
        <Route path="/reset-password" element={<ResetPassword />} />
        
        {/* Authenticated/Dashboard Routes */}
        <Route path="/requests" element={<Prescription />} /> 

        <Route path="/requests/prescriptions" element={<Prescription />} /> 
        <Route path="/requests/prescriptions/new" element={<PrescriptionNew />} /> 
        <Route path="/requests/prescriptions/Returned" element={<PrescriptionReturned />} /> 
        <Route path="/requests/prescriptions/Rejected" element={<PrescriptionRejected />} /> 

        <Route path="/requests/appointments" element={<Appointments />} />
        <Route path="/requests/appointments/:id" element={<AppointmentDetail />} />

        <Route path="/requests/transfers" element={<TransferRequestTable />} />
        <Route path="/requests/transfers/:id" element={<PatientTransferReview />} />

        <Route path="/requests/general" element={<General />} />
        <Route path="/requests/general/:id" element={<RequestReviewPage />} />

        <Route path="/patients" element={<PatientDashboard />} />
        <Route path="/patients/archive" element={<PatientDashboard />} />
        <Route path="/patients/patient_details/:patientId" element={<PatientDetails />} />
        
        <Route path="/patients/patient_details/:patientId/:prescriptionId" element={<PrescriptionPage />} />
        <Route path="/patients/test_details/:patientId/:testId" element={<TestPage />} />

        <Route path="/patients/patient_details/:patientId/consultation_history" element={<NewConsultationForm />} />

        {/* Catch-all route for unmatched paths */}
        <Route path="*" element={<Navigate to="/signin" replace />} />
      </Routes>
    </div>
  );
};

export default AppRouter;