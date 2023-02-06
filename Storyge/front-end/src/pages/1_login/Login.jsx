import React from "react";
import * as S from "./Loginstyle.js";
import { GoogleOAuthProvider } from "@react-oauth/google";

import GoogleLogInBtn from "./GoogleLogIn.jsx";
import NaverLoginBtn from "./NaverLogin";
import KakaoLogInBtn from "./KakaoLogIn";
import { GOOGLE_CLIENT_ID } from "./OAuth";

export default function Login() {
  return (
    <S.Login>
      <h1>Login</h1>
      <GoogleOAuthProvider clientId={GOOGLE_CLIENT_ID}>
        <GoogleLogInBtn />
      </GoogleOAuthProvider>

      <NaverLoginBtn />
      <KakaoLogInBtn />
    </S.Login>
  );
}
