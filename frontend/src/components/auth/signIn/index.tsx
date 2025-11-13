import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
import AuthLayout from "../layout/AuthLayout";
import FormInput from "../layout/FormInput";
import SubmitButton from "../layout/SubmitButton";
import AuthFooter from "../layout/AuthFooter";
import { useAuth } from "../../../hooks/appointments/useAuth";

// Form data interface for Sign In
interface SignInFormData {
  email: string;
  password: string;
}

// Error messages interface
interface SignInFormErrors {
  email?: string;
  password?: string;
}

// SignIn Component
const SignIn: React.FC<{}> = () => {
  const {login} = useAuth();
  const navigate = useNavigate();
  const [formData, setFormData] = useState<SignInFormData>({
    email: "",
    password: "",
  });

  const [errors, setErrors] = useState<SignInFormErrors>({});
  const [isSubmitting, setIsSubmitting] = useState(false);

  // Validation functions
  const validateEmail = (email: string): boolean => {
    const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    return emailRegex.test(email);
  };

  const validateForm = (): boolean => {
    const newErrors: SignInFormErrors = {};

    // Email validation
    if (!formData.email.trim()) {
      newErrors.email = "Email ID not registered.";
    } else if (!validateEmail(formData.email)) {
      newErrors.email = "Please enter a valid email address";
    }

    // Password validation
    if (!formData.password) {
      newErrors.password = "Incorrect/invalid password";
    }

    setErrors(newErrors);
    return Object.keys(newErrors).length === 0;
  };

  const handleInputChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    const { name, value } = e.target;
    setFormData((prev) => ({
      ...prev,
      [name]: value,
    }));

    // Clear error when user starts typing
    if (errors[name as keyof SignInFormErrors]) {
      setErrors((prev) => ({
        ...prev,
        [name]: undefined,
      }));
    }
  };

  const handleSubmit = async (e: React.FormEvent) => {
    e.preventDefault();

    if (!validateForm()) {
      return;
    }

    setIsSubmitting(true);

    try {
      await login(formData);
      navigate("/requests/appointments"); 
    } catch (error: any) {
      const message = error.response?.data?.message || "Invalid email or password.";
      setErrors({ email: message });
    } finally {
      setIsSubmitting(false);
    }
  };

  return (
    <AuthLayout
      title="Welcome Back to Neuromed Portal"
      imageAlt="SignIn Image"
      footerContent={
        <AuthFooter
          linkText="Don't have an account?"
          linkAction="Sign Up"
          onLinkClick={() => {
            navigate("/signup");
          }}
        />
      }
    >
      <form
        onSubmit={handleSubmit}
        className="grid grid-cols-1 sm:grid-cols-2 gap-4"
      >
        {/* Email */}
        <FormInput
          id="email"
          name="email"
          type="email"
          label="Email ID"
          placeholder="Enter your email address"
          value={formData.email}
          onChange={handleInputChange}
          error={errors.email}
          required
          colSpan="2"
        />

        {/* Password */}
        <FormInput
          id="password"
          name="password"
          type="password"
          label="Password"
          placeholder="Enter your password"
          value={formData.password}
          onChange={handleInputChange}
          error={errors.password}
          required
          colSpan="2"
          showPasswordToggle={true}
        />

        {/* Remember Me & Forgot Password */}
        <div className="col-span-1 sm:col-span-2 flex items-center justify-between">
          <div className="flex items-center">
            <input
              id="remember-me"
              name="remember-me"
              type="checkbox"
              className="h-4 w-4 text-[#5B3CA1] focus:ring-[#5B3CA1] border-gray-300 rounded"
            />
            <label
              htmlFor="remember-me"
              className="ml-2 block text-sm text-gray-900"
            >
              Remember me
            </label>
          </div>
          <div className="text-sm">
            <button
              type="button"
              onClick={() => navigate("/forgot-password")}
              className="font-medium text-[#5B3CA1] hover:text-[#4A2F8A]"
            >
              Forgot password?
            </button>
          </div>
        </div>

        {/* Submit Button */}
        <SubmitButton
          isSubmitting={isSubmitting}
          submittingText="Signing In..."
          defaultText="Sign In"
        />
      </form>
    </AuthLayout>
  );
};

export default SignIn;
