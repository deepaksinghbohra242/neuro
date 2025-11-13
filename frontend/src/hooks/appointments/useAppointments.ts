import { useEffect, useState } from "react";
import { appointmentService } from "../../services/appointmentService";
import type { Appointment } from "../../models";

export const useAppointments = (consultantId: number) => {
  const [appointments, setAppointments] = useState<Appointment[]>([]);
  const [loading, setLoading] = useState<boolean>(false);
  const [error, setError] = useState<string | null>(null);

  const fetchAppointments = async () => {
    if (!consultantId) return;
    setLoading(true);
    setError(null);
    try {
      const data = await appointmentService.getAllAppointments(consultantId);
      setAppointments(data);
    } catch (err: any) {
      console.error("Error fetching appointments:", err);
      setError(err.message || "Failed to fetch appointments");
    } finally {
      setLoading(false);
    }
  };

  useEffect(() => {
    fetchAppointments();
  }, [consultantId]);

  return {
    appointments,
    loading,
    error,
    refetch: fetchAppointments,
  };
};
