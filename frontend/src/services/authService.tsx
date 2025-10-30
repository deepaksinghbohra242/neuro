import { apiClient, API_ENDPOINTS } from "./config";
import type { LoginCredentials , RegisterData , UserProfile} from "../models";

export const authAPI = {
  loginUser: async (credentials: LoginCredentials) => {
    try {
      const response = await apiClient.post(API_ENDPOINTS.AUTH.LOGIN, credentials);
      return response.data;
    } catch (err: any) {
      console.error("Login Error:", err.response?.data?.message || err.message);
      throw err;
    }
  },

  registerUser: async (userData: RegisterData) => {
    try {
      const response = await apiClient.post(API_ENDPOINTS.AUTH.REGISTER, userData);
      return response.data;
    } catch (err: any) {
      console.error("Registration Error:", err.response?.data?.message || err.message);
      throw err;
    }
  },

  getProfile: async (): Promise<UserProfile> => {
    try {
      const response = await apiClient.get(API_ENDPOINTS.AUTH.GET);
      return response.data;
    } catch (err: any) {
      console.error("Get Profile Error:", err.response?.data?.message || err.message);
      throw err;
    }
  },

  updateProfile: async (profileData: UserProfile): Promise<UserProfile> => {
    try {
      const response = await apiClient.put(API_ENDPOINTS.AUTH.PUT, profileData);
      return response.data;
    } catch (err: any) {
      console.error("Update Profile Error:", err.response?.data?.message || err.message);
      throw err;
    }
  },

  deactivateUser: async (): Promise<any> => {
    try {
      const response = await apiClient.delete(API_ENDPOINTS.AUTH.DELETE);
      return response.data;
    } catch (err: any) {
      console.error("Deactivate User Error:", err.response?.data?.message || err.message);
      throw err;
    }
  },
};
