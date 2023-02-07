import React from "react";
import * as S from "./../Loginstyle.js";

import { KAKAO_AUTH_URL } from "./../OAuth.js";

export default function KakaoLogIn() {
  const kakaoLogin = () => {
    window.location.href = KAKAO_AUTH_URL;
  };

  return <S.Kakao onClick={kakaoLogin}></S.Kakao>;
}
