import Table from "../common/Table/index";

const PrescriptionDetails = () => {
  // --- MOCK DATA (Matches the Screenshot) ---
  const overviewData = {
    requestID: "RQ356356",
    date: "06/06/2025",
    prescribedBy: "Dr. Chloe Gallagher",
    patientName: "Connor Walsh",
    duration: "07 Days",
  };

  const medicinesData = [
    {
      id: 1,
      medID: "ME938473",
      name: "Paracitamol",
      dose: "500 mg",
      frequency: "Twice Daily",
      timesOrdered: "01",
    },
    {
      id: 2,
      medID: "ME65626",
      name: "Anti Depressants",
      dose: "05 mg",
      frequency: "Once at Night",
      timesOrdered: "01",
    },
  ];

  const orderHistoryData = [
    {
      id: 1,
      orderID: "OR2450354",
      orderDate: "21/12/2025",
      prescribedBy: "Dr. Chloe Gallagher",
      deliveryDate: "22/12/2025",
      status: "REQUESTED",
      pharmacy: "Medicure Meds",
    },
  ];

  const medicinesColumns = [
    { key: "id", header: "#", headerClass: "w-12", cellClass: "" },
    { key: "medID", header: "ID", headerClass: "", cellClass: "" },
    { key: "name", header: "MEDICINE NAME", headerClass: "", cellClass: "" },
    { key: "dose", header: "PRESCRIBED DOSE", headerClass: "", cellClass: "" },
    { key: "frequency", header: "FREQUENCY", headerClass: "", cellClass: "" },
    {
      key: "timesOrdered",
      header: "TIMES ORDERED",
      headerClass: "text-right",
      cellClass: "text-right",
    },
  ];

  const orderHistoryColumns = [
    { key: "id", header: "#", headerClass: "w-12", cellClass: "" },
    { key: "orderID", header: "ID", headerClass: "", cellClass: "" },
    { key: "orderDate", header: "ORDER DATE", headerClass: "", cellClass: "" },
    {
      key: "prescribedBy",
      header: "PRESCRIBED BY",
      headerClass: "",
      cellClass: "",
    },
    {
      key: "deliveryDate",
      header: "DELIVERY DATE",
      headerClass: "",
      cellClass: "",
    },
    // Custom render function for the STATUS column to apply the badge
    {
      key: "status",
      header: "STATUS",
      headerClass: "",
      cellClass: "",
      render: (status) => (
        <span
          className={`inline-block px-3 py-1 text-xs font-semibold rounded ${
            status === "REQUESTED"
              ? "bg-indigo-100 text-indigo-700"
              : "bg-green-100 text-green-700"
          }`}
        >
          {status}
        </span>
      ),
    },
    {
      key: "pharmacy",
      header: "PHARMACY NAME",
      headerClass: "",
      cellClass: "",
    },
  ];

  const Section = ({ title, children }) => (
    /**
     * Section: Applies the main section title styling (blue vertical line)
     */

    <div className="mb-8">
      <div className="border-l-4 border-indigo-500 pl-3 mb-4">
        <h2 className="text-lg font-semibold text-gray-700 tracking-wider">
          {title}
        </h2>
      </div>
      {children}
    </div>
  );

  /**
   * OverviewItem: Renders a single detail item in the Overview section
   */
  const OverviewItem = ({ label, value }) => (
    <div>
      <p className="text-gray-500 font-medium mb-1">{label}</p>
      <p className="text-gray-800 font-semibold">{value}</p>
    </div>
  );

  /**
   * OverviewCard: Renders the grid layout for the prescription summary
   */
  const OverviewCard = ({ data }) => (
    <div className="bg-white p-6 rounded-lg shadow-sm border border-gray-200 grid grid-cols-2 md:grid-cols-5 gap-y-4 text-sm">
      <OverviewItem label="Request ID" value={data.requestID} />
      <OverviewItem label="Date" value={data.date} />
      <OverviewItem label="Prescribed By" value={data.prescribedBy} />
      <OverviewItem label="Patient Name" value={data.patientName} />
      <OverviewItem label="Prescription Duration" value={data.duration} />
    </div>
  );

  const formatCount = (count) => (count < 10 ? "0" + count : count);

  const handleTabChange = (tab: string) => {
    console.log("selected");
  };

  return (
    <main className="mx-auto bg-white p-6 rounded-xl shadow-lg">
      {/* Overview Section */}
      <Section title="OVERVIEW">
        <OverviewCard data={overviewData} />
      </Section>

      {/* Total Medicines Section - NOW USING GENERIC TABLE */}
      <Section title={`Total Medicines (${formatCount(medicinesData.length)})`}>
        <Table
          data={medicinesData}
          columns={medicinesColumns}
          // onTabChange={handleTabChange}
          // initialActiveTab={false}
        />
      </Section>

      {/* Order History Section - NOW USING GENERIC TABLE */}
      <Section
        title={`Order History (${formatCount(orderHistoryData.length)})`}
      >
        <Table
          data={orderHistoryData}
          columns={orderHistoryColumns}
          // onTabChange={handleTabChange}
          // initialActiveTab={false}
        />
      </Section>
    </main>
  );
};
export default PrescriptionDetails;
