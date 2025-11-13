import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
import AuthLayout from "../layout/AuthLayout";
import FormInput from "../layout/FormInput";
import SubmitButton from "../layout/SubmitButton";
import AuthFooter from "../layout/AuthFooter";
import { useAuth } from "../../../hooks/appointments/useAuth";

// Form data interface
interface FormData {
  firstName: string;
  lastName: string;
  email: string;
  password: string;
  confirmPassword: string;
}

// Error messages interface
interface FormErrors {
  firstName?: string;
  lastName?: string;
  email?: string;
  password?: string;
  confirmPassword?: string;
}

// SignUp Component
const SignUp: React.FC<{}> = () => {
  const { register } = useAuth();
  const navigate = useNavigate();
  const [formData, setFormData] = useState<FormData>({
    firstName: "",
    lastName: "",
    email: "",
    password: "",
    confirmPassword: "",
  });

  const [errors, setErrors] = useState<FormErrors>({});
  const [isSubmitting, setIsSubmitting] = useState(false);

  // Validation functions
  const validateEmail = (email: string): boolean => {
    const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    return emailRegex.test(email);
  };

  const validatePassword = (password: string): boolean => {
    return password.length >= 8;
  };

  const validateForm = (): boolean => {
    const newErrors: FormErrors = {};

    // First Name validation
    if (!formData.firstName.trim()) {
      newErrors.firstName = "First name is required";
    } else if (formData.firstName.trim().length < 2) {
      newErrors.firstName = "First name must be at least 2 characters";
    }

    // Last Name validation
    if (!formData.lastName.trim()) {
      newErrors.lastName = "Last name is required";
    } else if (formData.lastName.trim().length < 2) {
      newErrors.lastName = "Last name must be at least 2 characters";
    }

    // Email validation
    if (!formData.email.trim()) {
      newErrors.email = "Email already registered.";
    } else if (!validateEmail(formData.email)) {
      newErrors.email = "Please enter a valid email address";
    }

    // Password validation
    if (!formData.password) {
      newErrors.password = "Incorrect/invalid password";
    } else if (!validatePassword(formData.password)) {
      newErrors.password = "Password must be at least 8 characters long";
    }

    // Confirm Password validation
    if (!formData.confirmPassword) {
      newErrors.confirmPassword = "Please confirm your password";
    } else if (formData.password !== formData.confirmPassword) {
      newErrors.confirmPassword = "The passwords do't match";
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
    if (errors[name as keyof FormErrors]) {
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
      await register({
        firstName: formData.firstName,
        lastName: formData.lastName,
        email: formData.email,
        password: formData.password,
        roles: ["USER"],
      });
      navigate("/requests/appointments");
    } catch (error: any) {
      const message =
        error.response?.data?.message || "Email already registered.";
      setErrors({ email: message });
    } finally {
      setIsSubmitting(false);
    }
  };
  return (
    <AuthLayout
      title="Welcome to the Neuromed Portal"
      imageAlt="SignUp Image"
      footerContent={
        <AuthFooter
          linkText="Already a user?"
          linkAction="Sign In"
          onLinkClick={() => {
            navigate("/signin");
          }}
        />
      }
    >
      <form
        onSubmit={handleSubmit}
        className="grid grid-cols-1 sm:grid-cols-2 gap-4"
      >
        {/* First Name */}
        <FormInput
          id="firstName"
          name="firstName"
          type="text"
          label="First Name"
          placeholder="Enter first name here"
          value={formData.firstName}
          onChange={handleInputChange}
          error={errors.firstName}
          required
        />

        {/* Last Name */}
        <FormInput
          id="lastName"
          name="lastName"
          type="text"
          label="Last Name"
          placeholder="Enter last name here"
          value={formData.lastName}
          onChange={handleInputChange}
          error={errors.lastName}
          required
        />

        {/* Email */}
        <FormInput
          id="email"
          name="email"
          type="email"
          label="Email ID"
          placeholder="Enter your email ID here"
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
          placeholder="Maximum 8 characters"
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
          label="Confirm Password"
          placeholder="Maximum 8 characters"
          value={formData.confirmPassword}
          onChange={handleInputChange}
          error={errors.confirmPassword}
          required
          colSpan="2"
          showPasswordToggle={true}
        />

        {/* Submit Button */}
        <SubmitButton
          isSubmitting={isSubmitting}
          submittingText="Creating Account..."
          defaultText="Continue"
        />
      </form>
    </AuthLayout>
  );
};

export default SignUp;
