// 리다이렉트될 화면
// OAuth2RedirectHandeler.js

import React, { useEffect } from "react";
import * as S from "./Loginstyle";
import {
  kakaoLogin,
  googleLogin,
  naverLogin,
} from "./../../redux/modules/user.js";
import Spinner from "./../../components/spinner/Spinner";

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

  return <S.Login><Spinner /></S.Login>;
};

export default OAuth2RedirectHandler;
