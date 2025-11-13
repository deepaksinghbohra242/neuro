import React from "react";
import AppRouter from "./router/AppRouter";
import Sidebar from "./components/sidebar";
import { BrowserRouter, Route, Routes } from "react-router-dom";
import DashboardLayout from "./components/layout/dashboardLayout";


function App() {
  return (
    <BrowserRouter>
      <DashboardLayout>
        <AppRouter />
      </DashboardLayout>
    </BrowserRouter>
  );
}

export default App;
