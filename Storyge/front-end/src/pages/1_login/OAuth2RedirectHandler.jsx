// 리다이렉트될 화면
// OAuth2RedirectHandeler.js

import React from "react";
import { useDispatch } from "react-redux";
import { kakaoLogin as userActions } from "./../../redux/modules/user.js";
import Spinner from "./Spinner.jsx";

const OAuth2RedirectHandler = (props) => {
  const dispatch = useDispatch();

  // 인가코드
  let code = new URL(window.location.href).searchParams.get("code");

  React.useEffect(async () => {
    await dispatch(userActions.kakaoLogin(code));
  }, []);

  return <Spinner />;
};

export default OAuth2RedirectHandler;
