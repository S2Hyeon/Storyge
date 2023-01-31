import React from "react";
import { Outlet } from "react-router-dom";
import Header from "../common/header/Header";
import Nav from "../common/footer/Nav";

function Layout() {
  return (
    <div>
      <Header />
      <Outlet />
      <Nav />
    </div>
  );
}

export default Layout;
