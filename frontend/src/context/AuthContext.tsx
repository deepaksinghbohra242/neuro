import { createContext, useContext, useState, useEffect } from "react";
import type { ReactNode } from "react";
import { authAPI } from "../services/authService";
import type { LoginCredentials, RegisterData, UserProfile } from "../models";

interface AuthContextType {
  user: UserProfile | null;
  loading: boolean;
  login: (credentials: LoginCredentials) => Promise<void>;
  register: (data: RegisterData) => Promise<void>;
  logout: () => void;
  updateProfile: (data: UserProfile) => Promise<void>;
  deactivateAccount: () => Promise<void>;
}

const AuthContext = createContext<AuthContextType | null>(null);

export const AuthProvider = ({ children }: { children: ReactNode }) => {
  const [user, setUser] = useState<UserProfile | null>(null);
  const [loading, setLoading] = useState<boolean>(true);

  useEffect(() => {
    const fetchUser = async () => {
      try {
        const profile = await authAPI.getProfile();
        setUser(profile);
      } catch (err) {
        console.warn("User not logged in or session expired");
      } finally {
        setLoading(false);
      }
    };

    fetchUser();
  }, []);

  const login = async (credentials: LoginCredentials) => {
    setLoading(true);
    try {
      const data = await authAPI.loginUser(credentials);
      localStorage.setItem("jwtToken", data.token);
      const profile = await authAPI.getProfile();
      setUser(profile);
    } finally {
      setLoading(false);
    }
  };

  const register = async (data: RegisterData) => {
    setLoading(true);
    try {
      const res = await authAPI.registerUser(data);
      localStorage.setItem("jwtToken", res.token);
      const profile = await authAPI.getProfile();
      setUser(profile);
    } finally {
      setLoading(false);
    }
  };

  const logout = () => {
    localStorage.removeItem("jwtToken");
    setUser(null);
  };

  const updateProfile = async (data: UserProfile) => {
    const updated = await authAPI.updateProfile(data);
    setUser(updated);
  };

  const deactivateAccount = async () => {
    await authAPI.deactivateUser();
    logout();
  };

  return (
    <AuthContext.Provider
      value={{ user, loading, login, register, logout, updateProfile, deactivateAccount }}
    >
      {children}
    </AuthContext.Provider>
  );
};

export const useAuthContext = () => {
    const context = useContext(AuthContext);
    if (!context) throw new Error("useAuthContext must be used inside AuthProvider");
    return context;
};
