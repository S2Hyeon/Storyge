import React, { useEffect, useState } from "react";
import axios from "axios";

import * as G from "./../../styles/index";
import * as S from "./MyPage";
import ProfileBox from "./../../components/profileBox/ProfileBox.jsx";
import { useNavigate } from "react-router-dom";
import { BsPersonCircle } from "react-icons/bs";
import { removeCookie } from "./../../utils/Cookies";

import Swal from "sweetalert2";
import withReactContent from "sweetalert2-react-content";

const MySwal = withReactContent(Swal);

const Toast = MySwal.mixin({
  toast: true,
  position: "center-center",
  showConfirmButton: false,
  timer: 3000,
  timerProgressBar: true,
  didOpen: (toast) => {
    toast.addEventListener("mouseenter", Swal.stopTimer);
    toast.addEventListener("mouseleave", Swal.resumeTimer);
  },
});

export default function MyPage({ setToken }) {
  const movePage = useNavigate();

  function gomodifyprofile() {
    movePage("/modify");
  }

  function logout() {
    if (
      Swal.fire({
        text: "로그아웃하시겠습니까?",
        icon: "warning",
        showCancelButton: true,
        confirmButtonColor: "#3085d6",
        cancelButtonColor: "#d33",
        confirmButtonText: "Yes",
      }).then((result) => {
        if (result.isConfirmed) {
          setToken(undefined);
          removeCookie("token");
          Toast.fire({
            icon: "warning",
            title: "로그아웃되었습니다.",
          });
          movePage("/login");
        }
      })
    ) {
    }
  }

  const [userData, setUserData] = useState({
    profile: "",
    nickname: "",
    follower: "",
    following: "",
  });

  //처음 렌더링이 될 때만 실행
  useEffect(() => {
    // async await 함수를 사용
    async function getUserData() {
      try {
        setUserData(await axios.get("/user"));
        console.log(userData);
      } catch {
        // 오류 발생시 실행
        alert("마이데이터 못 가져옴!!!");
      }
    }

    getUserData();
  }, [userData]);

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
