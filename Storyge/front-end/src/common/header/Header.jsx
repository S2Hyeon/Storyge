import * as S from "./HeaderStyle";
import React from "react";
import Logo from "../../assets/logo.png";

function Header() {
  return (
    <>
      <S.Header>
        <S.Img src={Logo} alt="LogoImage" />
        <span>Storyge</span>
      </S.Header>
    </>
  );
}

export default Header;
