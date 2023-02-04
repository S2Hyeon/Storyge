import React from "react";
import * as S from "./Loginstyle.js";

import { KAKAO_AUTH_URL } from "../1_login/OAuth.jsx";

export default function Login() {
  return (
    <S.Login>
      <h1>Login</h1>

      <S.GoogleBtn onClick={() => alert("google")}></S.GoogleBtn>
      <S.NaverBtn onClick={() => alert("naver")}></S.NaverBtn>
      <S.KakaoBtn
        href={KAKAO_AUTH_URL}
        onClick={() => alert("kakao")}
      ></S.KakaoBtn>
    </S.Login>
  );
}
