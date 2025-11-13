import React from 'react';
import RequestReviewHeader from './RequestReviewHeader';
import OverviewCard from './RequestOverviewCard';
import NecessaryFieldsCard from './NecessaryFieldsCard';
import ClinicAddressCard from './ClinicAddressCard';
import ReasonForLeaveCard from './ReasonForLeaveCard';

const RequestReviewPage = () => {
  return (
    <div className="min-h-screen bg-gray-50 p-4 sm:p-6 md:p-8 font-sans">
      {/* Top Breadcrumbs */}
      <div className="flex items-center text-sm mb-6 text-gray-500">
        <span className="text-indigo-600 font-medium">Requests</span>
        <span className="mx-1">›</span>
        <span className="text-indigo-600 font-medium">General</span>
        <span className="mx-1">›</span>
        <span className="text-gray-900 font-medium">Review Request</span>
      </div>

      {/* Main Header with navigation and action buttons */}
      <RequestReviewHeader />

      {/* Content Area - Cards */}
      <div className="space-y-6">
        <OverviewCard />
        <NecessaryFieldsCard />
        <ClinicAddressCard />
        <ReasonForLeaveCard />
      </div>
    </div>
  );
};

export default RequestReviewPage;