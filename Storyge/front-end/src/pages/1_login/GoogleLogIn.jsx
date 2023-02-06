import React from "react";
import { useGoogleLogin } from "@react-oauth/google";
import * as S from "./Loginstyle.js";

export default function GoogleLogInBtn() {
  const googleSocialLogin = useGoogleLogin({
    onSuccess: (codeResponse) => console.log(codeResponse),
    flow: "auth-code",
  });
  return (
    <S.Google
      className="social_login_box google"
      onClick={() => googleSocialLogin()}
    ></S.Google>
  );
}
