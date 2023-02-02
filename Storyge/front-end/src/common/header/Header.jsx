import React, { useState } from "react";
import * as S from "./HeaderStyle";
import { useLocation, useNavigate } from "react-router-dom";
import Logo from "../../assets/logo1.png";

function Header() {
  const movePage = useNavigate();
  const curPath = useLocation();

  const [Title, setTitle] = useState(
    <S.Img src={Logo} alt="LogoImage" onClick={() => movePage("/intro")} />
  );

  if (curPath === "/music" || curPath === "/musicResult") {
    setTitle(<div />);
  }

  return (
    <>
      <S.Header>{Title}</S.Header>
    </>
  );

  // return (
  //   <>
  //     <S.Header>
  //       if(curPath === "/")
  //       <S.Img src={Logo} alt="LogoImage" onClick={() => movePage("/intro")} />
  //     </S.Header>
  //   </>
  // );
}

export default Header;
