// 리다이렉트될 화면
// OAuth2RedirectHandeler.js

import React, { useEffect } from "react";
import { kakaoLogin, googleLogin, naverLogin } from "api/user/login";
import Spinner from "./../../components/spinner/Spinner";
import * as S from "pages/1_login/Loginstyle";

const OAuth2RedirectHandler = (props) => {
  // 인가코드
  useEffect(() => {
    let code = new URL(window.location.href).searchParams.get("code");
    console.log(new URL(window.location.href).pathname.split("/")[3]);
    let state = new URL(window.location.href).pathname.split("/")[3];

    if (state === "google") {
      googleLogin(code);
    } else if (state === "kakao") {
      kakaoLogin(code);
    } else if (state === "naver") {
      naverLogin(code);
    }
  }, []);

  return (
    <S.Login>
      <Spinner />
    </S.Login>
  );
};

export default OAuth2RedirectHandler;
