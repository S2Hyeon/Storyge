import React, { useState } from "react";
import { Outlet } from "react-router-dom";
import Header from "../common/header/Header";
import Footer from "../common/footer/Nav";

function Layout() {
  //화면크기가 작아지면 Footer 사라지게
  const [windowHeight, setWindowHeight] = useState(window.innerHeight);
  const windowWidth = window.innerWidth;
  const [ratio, setRatio] = useState(windowWidth / windowHeight);
  window.onresize = function(event) {
    setWindowHeight(window.innerHeight);
    setRatio(windowWidth / windowHeight);
  };

  return (
    <div>
      <Header />
      <Outlet />
      {ratio < 5 / 7 ? <Footer /> : null}
    </div>
  );
}

export default Layout;
