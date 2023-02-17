import React from "react";
import * as S from "./Loginstyle";
import {
  KAKAO_AUTH_URL,
  GOOGLE_AUTH_URL,
  NAVER_AUTH_URL,
} from "../1_login/OAuth.js";

import logo from "./../../assets/StorygeColor.gif";
import { AnimateOnChange } from "react-animation";
import { setCookie } from "./../../utils/Cookies";

const myToken =
  "Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiLjhYfjhLnjhLTjhYciLCJ1c2VySWQiOjQsImF1dGgiOiJzc2gxNjkyQG5hdmVyLmNvbSIsImV4cCI6MTY3OTE4Mjc5MX0.bWkyO5-OHSXlFeFrLXHST1ALhrtZEQG7y0V0BHfGbIQ";
function setMyToken() {
  setCookie("token", myToken, {
    path: "/", // 모든 페이지에서 쿠키 접근 가능
    sameSite: "strict",
  });
  window.location.href = `http://localhost:3000/`;
}
export default function Login() {
  return (
    <S.Login>
      <botton onClick={setMyToken}>수현토큰</botton>
      <AnimateOnChange durationOut="1000">
        <S.Logo src={logo}></S.Logo>
      </AnimateOnChange>

      <S.LinkBtn href={GOOGLE_AUTH_URL}>
        <S.GoogleImg
          src="https://t1.daumcdn.net/cfile/tistory/998689465C3D7D1217?original"
          alt="naver"
        ></S.GoogleImg>
      </S.LinkBtn>
      <S.LinkBtn href={NAVER_AUTH_URL}>
        <S.NaverImg
          src="https://t1.daumcdn.net/cfile/tistory/99580C465C3D7D130C?original"
          alt="naver"
        ></S.NaverImg>
      </S.LinkBtn>
      <S.LinkBtn href={KAKAO_AUTH_URL}>
        <S.KakaoImg
          src="https://t1.daumcdn.net/cfile/tistory/99BEE8465C3D7D1214?original"
          alt="kakao"
        ></S.KakaoImg>
      </S.LinkBtn>
    </S.Login>
  );
}
