import React from "react";
import * as S from "./Loginstyle";
import {
  KAKAO_AUTH_URL,
  GOOGLE_AUTH_URL,
  NAVER_AUTH_URL,
} from "../1_login/OAuth.js";

export default function Login() {
  return (
    <S.Login>
      <h1>Login</h1>

      <S.Google
        href={GOOGLE_AUTH_URL}
        onClick={() => alert("google")}
      ></S.Google>
      <S.Naver href={NAVER_AUTH_URL} onClick={() => alert("naver")}></S.Naver>
      <S.Kakao href={KAKAO_AUTH_URL} onClick={() => alert("kakao")}></S.Kakao>
    </S.Login>
  );
}
