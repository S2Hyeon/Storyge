import React from "react";
import * as S from "../Loginstyle.js";

import { GOOGLE_AUTH_URL } from "./../OAuth.js";

export default function GoogleLogIn() {
  const googleLogin = () => {
    window.location.href = GOOGLE_AUTH_URL;
  };
  return <S.Google onClick={googleLogin}></S.Google>;
}
