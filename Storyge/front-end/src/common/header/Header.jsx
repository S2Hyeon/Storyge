import React from "react";
import * as S from "./HeaderStyle";
import { useNavigate } from "react-router-dom";
import Logo from "../../assets/logo1.png";

function Header() {
  const movePage = useNavigate();

  return (
    <>
      <S.Header>
        <S.Img src={Logo} alt="LogoImage" onClick={() => movePage("/intro")} />
      </S.Header>
    </>
  );
}

export default Header;
