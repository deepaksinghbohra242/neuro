import type { UserModel } from "./user.model";

export interface EndTime {
  hour: number;
  minute: number;
  second: number;
  nano: number;
}

export type AppointmentStatus = "PENDING" | "SCHEDULED" | "COMPLETED" | "CANCELLED";

export interface Appointment {
  id: number;
  patientId: number;
  consultantId: number;
  date: string;            
  timeSlot: string;        
  endTime?: EndTime;       
  status: AppointmentStatus;
  visitType?: string;
  reason?: string;
  billingId?: number;
  duration?: number;

  userModel?: UserModel;          
  patientUserModel?: UserModel;   
  consultantName?: string;        
}
