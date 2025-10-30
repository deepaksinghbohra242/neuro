import { apiClient, API_ENDPOINTS } from "./config";
import type { Appointment } from "../models";

export const appointmentService = {
  getAllAppointments: async (consultantId: number): Promise<Appointment[]> => {
    try {
      const response = await apiClient.get(API_ENDPOINTS.APPOINTMENTS.GET_ALL, {
        params: { consultantId },
      });
      return response.data;
    } catch (error: any) {
      console.error("Failed to fetch appointments:", error);
      throw error;
    }
  },
};
