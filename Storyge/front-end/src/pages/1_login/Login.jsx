import React from "react";
import "../1_login/Login.css";
import { AiFillFacebook } from "react-icons/ai";
import { FcGoogle } from "react-icons/fc";
import { RiKakaoTalkFill } from "react-icons/ri";

import { KAKAO_AUTH_URL } from "../1_login/OAuth.jsx";

export default function Login() {
  return (
    <div className="login">
      <h1>Login</h1>

      <a
        href="#!"
        className="login__btn__google"
        onClick={() => alert("google")}
      >
        <FcGoogle className="icon" />
        <div>Google Login</div>
      </a>

      <a
        href={KAKAO_AUTH_URL}
        className="login__btn__kakao"
        type="button"
        onClick={() => alert("kakao")}
      >
        <RiKakaoTalkFill className="icon" />
        <div>Kakao Login</div>
      </a>

      <a
        href="#!"
        className="login__btn__facebook"
        type="button"
        onClick={() => alert("facebook")}
      >
        <AiFillFacebook className="icon" />
        <div>Facebook Login</div>
      </a>
    </div>
  );
}
