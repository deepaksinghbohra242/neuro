import axios from "axios";

export const API_CONFIG = {
  BASE_URL: `${import.meta.env.VITE_BASEURL}/neuromed`,
  TIMEOUT: 10000,
  HEADERS: {
    "Content-Type": "application/json",
  },
};

export const apiClient = axios.create({
  baseURL: API_CONFIG.BASE_URL,
  timeout: API_CONFIG.TIMEOUT,
  headers: API_CONFIG.HEADERS,
});

apiClient.interceptors.request.use((config) => {
  const token = localStorage.getItem("jwtToken");
  if (token) {
    config.headers.Authorization = `Bearer ${token}`;
  }
  return config;
});

export const API_ENDPOINTS = {
  AUTH: {
    LOGIN: "/auth/api/login",
    REGISTER: "/auth/api/register",
    GET: "/auth/api/profile",
    PUT: "/auth/api/profile",
    DELETE: "/auth/api/deactivate",
  },

  APPOINTMENTS:{
    GET_ALL: "/appointments/api/listByConsultantId",
  }
}