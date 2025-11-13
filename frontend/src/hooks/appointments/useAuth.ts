import { useAuthContext } from "../../context/AuthContext";

export const useAuth = () => {
  const { user, loading, login, register, logout, updateProfile, deactivateAccount } =
    useAuthContext();

  return { user, loading, login, register, logout, updateProfile, deactivateAccount };
};
