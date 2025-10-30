import React, { useState } from "react";
import { useNavigate, useSearchParams } from "react-router-dom";
import AuthLayout from "../layout/AuthLayout";
import FormInput from "../layout/FormInput";
import SubmitButton from "../layout/SubmitButton";
import AuthFooter from "../layout/AuthFooter";

interface ResetPasswordFormData {
  password: string;
  confirmPassword: string;
}

interface ResetPasswordFormErrors {
  password?: string;
  confirmPassword?: string;
}

const ResetPassword: React.FC<{}> = () => {
  const navigate = useNavigate();

  const [formData, setFormData] = useState<ResetPasswordFormData>({
    password: "",
    confirmPassword: "",
  });

  const [errors, setErrors] = useState<ResetPasswordFormErrors>({});
  const [isSubmitting, setIsSubmitting] = useState(false);
  const [isPasswordReset, setIsPasswordReset] = useState(false);

  const validatePassword = (password: string): boolean => {
    return password.length >= 8;
  };

  const validateForm = (): boolean => {
    const newErrors: ResetPasswordFormErrors = {};

    if (!formData.password) {
      newErrors.password = "Password is required";
    } else if (!validatePassword(formData.password)) {
      newErrors.password = "Password must be at least 8 characters long";
    }

    if (!formData.confirmPassword) {
      newErrors.confirmPassword = "Please confirm your password";
    } else if (formData.password !== formData.confirmPassword) {
      newErrors.confirmPassword = "Passwords do not match";
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

    if (errors[name as keyof ResetPasswordFormErrors]) {
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
      setIsPasswordReset(true);
    } catch (error) {
      console.error("Reset password error:", error);
    } finally {
      setIsSubmitting(false);
    }
  };

  if (isPasswordReset) {
    return (
      <AuthLayout
        title="Password Reset Successfully!"
        imageAlt="Success Image"
        footerContent={
          <AuthFooter
            linkText="Ready to sign in?"
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
              Password Updated!
            </h3>
            <p className="text-gray-600">
              Your password has been successfully reset. You can now sign in
              with your new password.
            </p>
          </div>
          <button
            onClick={() => navigate("/signin")}
            className="w-full bg-[#5B3CA1] text-white hover:bg-[#4A2F8A] px-4 py-2 rounded-md transition-colors"
          >
            Continue to Sign In
          </button>
        </div>
      </AuthLayout>
    );
  }

  return (
    <AuthLayout
      title="Reset Your Password"
      imageAlt="Reset Password Image"
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
      <div className="space-y-6">
        <div className="text-center">
          <p className="text-gray-600">
            Enter your new password below. Make sure it's secure and easy to
            remember.
          </p>
        </div>

        <form
          onSubmit={handleSubmit}
          className="grid grid-cols-1 sm:grid-cols-2 gap-4"
        >
          {/* New Password */}
          <FormInput
            id="password"
            name="password"
            type="password"
            label="New Password"
            placeholder="Enter your new password"
            value={formData.password}
            onChange={handleInputChange}
            error={errors.password}
            required
            colSpan="2"
            showPasswordToggle={true}
          />

          {/* Confirm Password */}
          <FormInput
            id="confirmPassword"
            name="confirmPassword"
            type="password"
            label="Confirm New Password"
            placeholder="Confirm your new password"
            value={formData.confirmPassword}
            onChange={handleInputChange}
            error={errors.confirmPassword}
            required
            colSpan="2"
            showPasswordToggle={true}
          />

          {/* Password Requirements */}
      

          {/* Submit Button */}
          <SubmitButton
            isSubmitting={isSubmitting}
            submittingText="Updating Password..."
            defaultText="Update Password"
          />
        </form>
      </div>
    </AuthLayout>
  );
};

export default ResetPassword;
