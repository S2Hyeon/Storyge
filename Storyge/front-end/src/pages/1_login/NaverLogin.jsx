import React from "react";
import NaverLogin from "react-naver-login";
import { NAVER_CLIENT_ID } from "./OAuth.js";
import { NAVER_CALLBACK_URI } from "./OAuth.js";
import * as S from "./Loginstyle.js";

export default function NaverLoginBtn() {
  const result = "네이버 로그인 Error!!!!";

  return (
    <NaverLogin
      clientId={NAVER_CLIENT_ID}
      callbackUrl={NAVER_CALLBACK_URI}
      render={(props) => <S.Naver onClick={props.onClick}></S.Naver>}
      onSuccess={(naverUser) => console.log(naverUser)}
      onFailure={() => console.error(result)}
    />
  );
}
