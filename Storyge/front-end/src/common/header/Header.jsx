import React, { useState } from "react";
import { useLocation, useNavigate } from "react-router-dom";
import HeaderBackIcon from "./HeaderBackIcon";
import * as S from "./HeaderStyle";
import Logo from "../../assets/logo1.png";

function Header() {
  const navigate = useNavigate();
  const location = useLocation();
  let [title, setTitle] = useState("");

  const setHeaderContent = () => {
    // 1. 메인이 아니라면 해당 페이지에 맞는 화면 제목 띄우기
    if (location.pathname === "/music") {
      return (
        <>
          <HeaderBackIcon />
          <div>음악 추천</div>
        </>
      );
    }
    // 2. 알림 페이지
    else if (location.pathname === "/alarm") {
      return (
        <>
          <HeaderBackIcon />
          <div>알림</div>
        </>
      );
    }
    // 3. 마이페이지
    else if (location.pathname === "/mypage") {
      return (
        <>
          <HeaderBackIcon />
          <div>마이페이지</div>
        </>
      );
    }
    //4. 다이어리 작성
    else if (location.pathname === "/createDiary") {
      return (
        <>
          <HeaderBackIcon />
          <div>일기 작성</div>
        </>
      );
    }
    // 5. 일기 목록
    else if (location.pathname === "/diarylist") {
      return (
        <>
          <HeaderBackIcon />
          <div>일기 목록</div>
        </>
      );
    }
    // 6. 일기 디테일
    else if (location.pathname === "/diary") {
      return (
        <>
          <HeaderBackIcon />
          <div>나의 일기</div>
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
