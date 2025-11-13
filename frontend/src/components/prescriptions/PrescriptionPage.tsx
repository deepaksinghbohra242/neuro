import PrescriptionDetails from "./PrescriptionDetails";
import { useParams } from "react-router-dom";
const PrescriptionPage = () => {

  console.log(useParams(), '** inside PrescriptionDetails**')
  return (
    <>
      <PrescriptionDetails {...useParams} />
    </>
  );
};
export default PrescriptionPage;
