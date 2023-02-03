import React from "react";
import { useLocation, useNavigate } from "react-router-dom";
import * as S from "./NavStyle";
import { TbHome, TbMusic, TbPlus, TbBell, TbUser } from "react-icons/tb";

function Footer() {
  const location = useLocation();
  const movePage = useNavigate();

  return (
    <S.Nav>
      <S.IconContainer
        onClick={() => {
          movePage("/");
        }}
      >
        <TbHome
          size={30}
          color={location.pathname === "/" ? "#ACCEBC" : "#D9D9D9"}
        />
      </S.IconContainer>
      <S.IconContainer
        onClick={() => {
          movePage("/music");
        }}
      >
        <TbMusic
          size={30}
          color={location.pathname === "/music" ? "#ACCEBC" : "#D9D9D9"}
        />
      </S.IconContainer>
      <S.IconContainer
        onClick={() => {
          movePage("/createDiary");
        }}
      >
        <S.CenterCircle>
          <TbPlus size={30} color="white" />
        </S.CenterCircle>
      </S.IconContainer>
      <S.IconContainer
        onClick={() => {
          movePage("/alarm");
          console.log("알림 모음 페이지로 이동!!");
        }}
      >
        <TbBell
          size={30}
          color={location.pathname === "/alert" ? "#ACCEBC" : "#D9D9D9"}
        />
      </S.IconContainer>
      <S.IconContainer
        onClick={() => {
          movePage("/mypage");
          console.log("마이페이지로 이동!!!");
        }}
      >
        <TbUser
          size={30}
          color={location.pathname === "/mypage" ? "#ACCEBC" : "#D9D9D9"}
        />
      </S.IconContainer>
    </S.Nav>
  );
}

export default Footer;
