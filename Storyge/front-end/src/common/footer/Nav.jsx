import { TbHome, TbMusic, TbPlus, TbBell, TbUser } from "react-icons/tb";
import * as S from "./NavStyle";
import React from "react";
import { useState } from "react";

function Footer() {
  const [home, setHome] = useState(true);
  const [music, setMusic] = useState(false);
  const [alert, setAlert] = useState(false);
  const [user, setUser] = useState(false);

  function goHome() {
    setHome(true);
    setMusic(false);
    setAlert(false);
    setUser(false);
  }
  function goMusic() {
    setHome(false);
    setMusic(true);
    setAlert(false);
    setUser(false);
  }
  function goWrite() {
    setHome(false);
    setMusic(false);
    setAlert(false);
    setUser(false);
  }
  function goAlert() {
    setHome(false);
    setMusic(false);
    setAlert(true);
    setUser(false);
  }
  function goUser() {
    setHome(false);
    setMusic(false);
    setAlert(false);
    setUser(true);
  }

  return (
    <S.Nav>
      <S.IconContainer
        onClick={() => {
          goHome();
        }}
      >
        <TbHome size={30} color={home ? "#ACCEBC" : "#D9D9D9"} />
      </S.IconContainer>
      <S.IconContainer
        onClick={() => {
          goMusic();
        }}
      >
        <TbMusic size={30} color={music ? "#ACCEBC" : "#D9D9D9"} />
      </S.IconContainer>
      <S.IconContainer
        onClick={() => {
          goWrite();
        }}
      >
        <S.CenterCircle>
          <TbPlus size={30} color="white" />
        </S.CenterCircle>
      </S.IconContainer>
      <S.IconContainer
        onClick={() => {
          goAlert();
        }}
      >
        <TbBell size={30} color={alert ? "#ACCEBC" : "#D9D9D9"} />
      </S.IconContainer>
      <S.IconContainer
        onClick={() => {
          goUser();
        }}
      >
        <TbUser size={30} color={user ? "#ACCEBC" : "#D9D9D9"} />
      </S.IconContainer>
    </S.Nav>
  );
}

export default Footer;
