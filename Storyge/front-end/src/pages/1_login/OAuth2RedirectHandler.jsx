// 리다이렉트될 화면
// OAuth2RedirectHandeler.js

import React, { useEffect } from "react";
import { kakaoLogin as userActions } from "./../../redux/modules/user.js";
import Spinner from "./Spinner.jsx";

const OAuth2RedirectHandler = (props) => {
  // 인가코드
  useEffect(() => {
    let code = new URL(window.location.href).searchParams.get("code");
    userActions(code);
  }, []);

  return <Spinner />;
};

export default OAuth2RedirectHandler;
