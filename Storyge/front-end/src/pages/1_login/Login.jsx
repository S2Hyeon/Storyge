import React from "react";
import * as S from "./Loginstyle.js";

// import { GoogleOAuthProvider } from "@react-oauth/google";
// import GoogleLogInBtn from "./google/GoogleLogIn.jsx";

import KakaoLogInBtn from "./Kakao/KakaoLogIn.jsx";
import NaverLoginBtn from "./naver/NaverLogin.jsx";
// import { GOOGLE_CLIENT_ID } from "./OAuth";

export default function Login() {
  return (
    <S.Login>
      <S.LoginText>Login</S.LoginText>
      {/* <GoogleOAuthProvider clientId={GOOGLE_CLIENT_ID}>
        <GoogleLogInBtn />
      </GoogleOAuthProvider> */}

      <NaverLoginBtn />

      <KakaoLogInBtn />
    </S.Login>
  );
}
