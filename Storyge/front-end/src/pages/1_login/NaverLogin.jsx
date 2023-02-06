import React from "react";
import NaverLogin from "react-naver-login";
import { NAVER_CLIENT_ID } from "../1_login/OAuth.js";
import { NAVER_CALLBACK_URI } from "../1_login/OAuth.js";

const NaverLoginBtn = () => {
  const result = "네이버 로그인 Error!!!!";

  return (
    <NaverLogin
      clientId={NAVER_CLIENT_ID}
      callbackUrl={NAVER_CALLBACK_URI}
      render={(props) => <div onClick={props.onClick}>Naver Login</div>}
      onSuccess={(naverUser) => console.log(naverUser)}
      onFailure={() => console.error(result)}
    />
  );
};

export default NaverLoginBtn;
