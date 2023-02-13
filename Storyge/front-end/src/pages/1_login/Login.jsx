import React from "react";
import * as S from "./Loginstyle";
import {
  KAKAO_AUTH_URL,
  GOOGLE_AUTH_URL,
  NAVER_AUTH_URL,
} from "../1_login/OAuth.js";

import logo from "./../../assets/Storyge.gif";
import { AnimateOnChange } from "react-animation";

export default function Login() {
  return (
    <S.Login>
      <AnimateOnChange durationOut="1000">
        <S.Logo src={logo}></S.Logo>
      </AnimateOnChange>

      <S.LinkBtn href={GOOGLE_AUTH_URL} onClick={() => alert("google")}>
        <S.GoogleImg
          src="https://t1.daumcdn.net/cfile/tistory/998689465C3D7D1217?original"
          alt="naver"
        ></S.GoogleImg>
      </S.LinkBtn>
      <S.LinkBtn href={NAVER_AUTH_URL} onClick={() => alert("naver")}>
        <S.NaverImg
          src="https://t1.daumcdn.net/cfile/tistory/99580C465C3D7D130C?original"
          alt="naver"
        ></S.NaverImg>
      </S.LinkBtn>
      <S.LinkBtn href={KAKAO_AUTH_URL} onClick={() => alert("kakao")}>
        <S.KakaoImg
          src="https://t1.daumcdn.net/cfile/tistory/99BEE8465C3D7D1214?original"
          alt="kakao"
        ></S.KakaoImg>
      </S.LinkBtn>
    </S.Login>
  );
}
