import React from "react";
import * as G from "./../../styles/index";
import * as S from "./MyPage";
import ProfileBox from "./../../components/profileBox/ProfileBox.jsx";
import { useNavigate } from "react-router-dom";
import { BsPersonCircle } from "react-icons/bs";
import { removeCookie } from "./../../utils/Cookies";

export default function MyPage() {
  const movePage = useNavigate();

  function gomodifyprofile() {
    movePage("/modify");
  }

  function logout() {
    if (window.confirm("로그아웃?")) {
      removeCookie("token");
      movePage("/login");
    }
  }

  return (
    <G.BodyContainer>
      <ProfileBox />
      <S.Menu onClick={gomodifyprofile}>
        <BsPersonCircle
          style={{ color: "#ACCEBC", width: "30px", height: "30px" }}
        />
        <S.Text>프로필 수정하기</S.Text>
      </S.Menu>
      <G.longBtnDefault onClick={logout}>로그아웃</G.longBtnDefault>
    </G.BodyContainer>
  );
}
