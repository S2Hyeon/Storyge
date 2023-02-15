import React, { useEffect, useState } from "react";
import Api from "lib/customApi";

import * as G from "./../../styles/index";
import * as S from "./MyPage";
import ProfileBox from "./../../components/profileBox/ProfileBox.jsx";
import { useNavigate } from "react-router-dom";
import { BsPersonCircle, BsQuestionCircle } from "react-icons/bs";
import { getCookie, removeCookie } from "./../../utils/Cookies";
import Chatbot from "api/chatbot/Chatbot";

import Swal from "sweetalert2";
import withReactContent from "sweetalert2-react-content";

const MySwal = withReactContent(Swal);

const Toast = MySwal.mixin({
  toast: true,
  position: "center-center",
  showConfirmButton: false,
  timer: 1000,
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

  const [userData, setUserData] = useState({});

  //처음 렌더링이 될 때만 실행
  useEffect(() => {
    async function getUserData() {
      try {
        const response = await Api.get("/user", {
          headers: {
            Authorization: getCookie("token"),
          },
        });
        console.log("마이페이지");
        setUserData(response.data);
      } catch (err) {
        console.log(err);
      }
    }
    getUserData();
  }, []);

  let [chatbot, setCharbot] = useState(false);

  function chatbotStatus() {
    setCharbot(!chatbot);
  }

  return (
    <G.BodyContainer>
      {userData && (
        <ProfileBox
          profileImg={userData.profileImg}
          nickname={userData.nickname}
          follower={userData.follower}
          following={userData.following}
        />
      )}

      <S.Menu onClick={gomodifyprofile}>
        <BsPersonCircle
          style={{
            color: "var(--color-primary)",
            width: "30px",
            height: "30px",
          }}
        />
        <S.Text>프로필 수정하기</S.Text>
      </S.Menu>
      <S.Menu onClick={chatbotStatus}>
        <BsQuestionCircle
          style={{
            color: "var(--color-primary)",
            width: "30px",
            height: "30px",
          }}
        />
        <S.Text>서비스 알아보기</S.Text>
      </S.Menu>
      <G.longBtnDefault onClick={logout}>로그아웃</G.longBtnDefault>
      {chatbot ? <Chatbot /> : ""}
    </G.BodyContainer>
  );
}
