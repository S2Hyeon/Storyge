import React from "react";
import * as S from "./SizeStyle.js";
import logo from "./../../assets/logo1.png";

export default function Size(props) {
  return (
    <S.Box>
      <S.Logo src={logo}></S.Logo>
      <S.TextM>Storyge는 모바일 환경에서 지원됩니다</S.TextM>
      <S.TextS>권장 사이즈는 390x844 입니다</S.TextS>
    </S.Box>
  );
}
