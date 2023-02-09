import React from "react";
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
