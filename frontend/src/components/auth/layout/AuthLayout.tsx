import React from "react";
import SignUpImage from "../../assets/images/signup.jpg";
import Logo from "../../assets/logo/logo.png";
import BackgroundImage from "../../assets/bg/bg.jpeg";

interface AuthLayoutProps {
  title: string;
  children: React.ReactNode;
  footerContent?: React.ReactNode;
  imageAlt?: string;
}

const AuthLayout: React.FC<AuthLayoutProps> = ({
  title,
  children,
  footerContent,
  imageAlt = "Authentication Image",
}) => {
  return (
    <div className="flex flex-col lg:flex-row items-center justify-center h-screen overflow-hidden">
      {/* Form Section - Responsive */}
      <section
        className="w-full lg:w-[960px] py-10 mx-auto px-8  relative min-h-screen flex flex-col justify-center"
        style={{
          backgroundImage: `url(${BackgroundImage})`,
          backgroundSize: "cover",
          backgroundPosition: "center",
          backgroundRepeat: "no-repeat",
        }}
      >
        {/* Professional overlay for better text readability */}
        <div className="absolute inset-0 bg-white/80 lg:bg-white/50"></div>

        {/* Content with proper z-index */}
        <div className="relative z-10 py-8 lg:py-0 lg:w-[631px] mx-auto">
          <div className="pb-3 flex justify-center lg:justify-start">
            <img
              src={Logo}
              alt="Logo"
              className="w-32 sm:w-40 lg:w-48 h-auto"
            />
          </div>
          <h2 className="text-center lg:text-left  text-[20px] sm:text-[30px] lg:text-[40px] font-[700] text-[#5B3CA1] mb-6 lg:mb-8 w-full">
            {title}
          </h2>
          <div className=" w-full">
            {children}
            {footerContent && (
              <div className="flex flex-col gap-2 items-center mt-6">
                {footerContent}
              </div>
            )}
          </div>
        </div>
      </section>

      {/* Image Section - Hidden on mobile, visible on large screens */}
      <section className="hidden lg:block w-[649px] h-screen">
        <img
          src={SignUpImage}
          alt={imageAlt}
          className="h-full w-full object-cover"
        />
      </section>
    </div>
  );
};

export default AuthLayout;
