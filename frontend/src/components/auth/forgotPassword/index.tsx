import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
import AuthLayout from "../layout/AuthLayout";
import FormInput from "../layout/FormInput";
import SubmitButton from "../layout/SubmitButton";
import AuthFooter from "../layout/AuthFooter";

// Form data interface for Forgot Password
interface ForgotPasswordFormData {
  email: string;
}

// Error messages interface
interface ForgotPasswordFormErrors {
  email?: string;
}

// ForgotPassword Component
const ForgotPassword: React.FC<{}> = () => {
  const navigate = useNavigate();
  const [formData, setFormData] = useState<ForgotPasswordFormData>({
    email: "",
  });

  const [errors, setErrors] = useState<ForgotPasswordFormErrors>({});
  const [isSubmitting, setIsSubmitting] = useState(false);
  const [isEmailSent, setIsEmailSent] = useState(false);

  // Validation functions
  const validateEmail = (email: string): boolean => {
    const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    return emailRegex.test(email);
  };

  const validateForm = (): boolean => {
    const newErrors: ForgotPasswordFormErrors = {};

    // Email validation
    if (!formData.email.trim()) {
      newErrors.email = "Email is required";
    } else if (!validateEmail(formData.email)) {
      newErrors.email = "Please enter a valid email address";
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
    if (errors[name as keyof ForgotPasswordFormErrors]) {
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
      await new Promise((resolve) => setTimeout(resolve, 2000));
      setIsEmailSent(true);
    } catch (error) {
      console.error("Forgot password error:", error);
    } finally {
      setIsSubmitting(false);
    }
  };

  if (isEmailSent) {
    return (
      <AuthLayout
        title="Check Your Email"
        imageAlt="Email Sent Image"
        footerContent={
          <AuthFooter
            linkText="Remember your password?"
            linkAction="Sign In"
            onLinkClick={() => {
              navigate("/signin");
            }}
            showTerms={false}
          />
        }
      >
        <div className="text-center space-y-6">
          <div className="mx-auto w-16 h-16 bg-green-100 rounded-full flex items-center justify-center">
            <svg
              className="w-8 h-8 text-green-600"
              fill="none"
              stroke="currentColor"
              viewBox="0 0 24 24"
            >
              <path
                strokeLinecap="round"
                strokeLinejoin="round"
                strokeWidth={2}
                d="M5 13l4 4L19 7"
              />
            </svg>
          </div>
          <div>
            <h3 className="text-lg font-semibold text-gray-900 mb-2">
              Reset Link Sent!
            </h3>
            <p className="text-gray-600">
              We've sent a password reset link to{" "}
              <span className="font-medium text-[#5B3CA1]">{formData.email}</span>
            </p>
          </div>
          <div className="text-sm text-gray-500">
            <p>Didn't receive the email? Check your spam folder or</p>
            <button
              onClick={() => setIsEmailSent(false)}
              className="text-[#5B3CA1] hover:text-[#4A2F8A] font-medium"
            >
              try again
            </button>
          </div>
        </div>
      </AuthLayout>
    );
  }

  return (
    <AuthLayout
      title="Forgot Your Password?"
      imageAlt="Forgot Password Image"
      footerContent={
        <AuthFooter
          linkText="Remember your password?"
          linkAction="Sign In"
          onLinkClick={() => {
            navigate("/signin");
          }}
          showTerms={false}
        />
      }
    >
      <div className="space-y-2">
        <div className="text-start">
          <p className="text-gray-600">
          Reset your password and regain access to Neuromed Portal.
          </p>
        </div>
        
        <form onSubmit={handleSubmit} className="grid grid-cols-1 sm:grid-cols-2 gap-4">
          {/* Email */}
          <FormInput
            id="email"
            name="email"
            type="email"
            label="Email Address"
            placeholder="Enter your email address"
            value={formData.email}
            onChange={handleInputChange}
            error={errors.email}
            required
            colSpan="2"
          />

          {/* Submit Button */}
          <SubmitButton
            isSubmitting={isSubmitting}
            submittingText="Sending Reset Link..."
            defaultText="Send Reset Link"
          />
        </form>
      </div>
    </AuthLayout>
  );
};

export default ForgotPassword;
