import * as S from "./HeaderStyle";
import React from "react";
import Logo from "../../assets/logo1.png";

function Header() {
  return (
    <>
      <S.Header>
        <S.Img src={Logo} alt="LogoImage" />
      </S.Header>
    </>
  );
}

export default Header;
