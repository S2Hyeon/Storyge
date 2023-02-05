import React, { useState } from "react";
import { useLocation, useNavigate } from "react-router-dom";
import HeaderBackIcon from "./HeaderBackIcon";
import * as S from "./HeaderStyle";
import Logo from "../../assets/logo1.png";
import { TbMusic, TbBell, TbWriting, TbUser } from "react-icons/tb";

function Header() {
  const navigate = useNavigate();
  const location = useLocation();
  let [title, setTitle] = useState("");

  const setHeaderContent = () => {
    // 1. 메인이 아니라면 해당 페이지에 맞는 화면 제목 띄우기
    if (location.pathname === "/music") {
      return (
        <>
          {/* <HeaderBackIcon /> */}
          <TbMusic />
          <S.TitleContainer>음악 추천</S.TitleContainer>
        </>
      );
    }
    // 2. 알림 페이지
    else if (location.pathname === "/alarm") {
      return (
        <>
          {/* <HeaderBackIcon /> */}
          <TbBell />
          <S.TitleContainer>알림</S.TitleContainer>
        </>
      );
    }
    // 3. 마이페이지
    else if (location.pathname === "/mypage") {
      return (
        <>
          {/* <HeaderBackIcon /> */}
          <TbUser />
          <S.TitleContainer>마이페이지</S.TitleContainer>
        </>
      );
    }
    //4. 다이어리 작성
    else if (location.pathname === "/createDiary") {
      return (
        <>
          {/* <HeaderBackIcon /> */}
          <TbWriting />
          <S.TitleContainer>일기 작성</S.TitleContainer>
        </>
      );
    }
    // 5. 일기 목록
    else if (location.pathname === "/diarylist") {
      return (
        <>
          <HeaderBackIcon />
          <S.TitleContainer>일기 목록</S.TitleContainer>
        </>
      );
    }
    // 6. 일기 디테일
    else if (location.pathname === "/diary") {
      return (
        <>
          <HeaderBackIcon />
          <S.TitleContainer>나의 일기</S.TitleContainer>
        </>
      );
    }

    //
    //
    //
    // !!!기본 상태: 로고
    else {
      return (
        <S.Img src={Logo} alt="LogoImage" onClick={() => navigate("/intro")} />
      );
    }
  };

  return <S.Header>{setHeaderContent()}</S.Header>;
}

export default Header;
//
