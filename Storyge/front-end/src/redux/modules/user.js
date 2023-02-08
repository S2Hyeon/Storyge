import axios from "axios";
import { setCookie } from "utils/Cookies";

export const kakaoLogin = async (code) => {
  // return await function (dispatch, getState, { history }) {
  axios({
    method: "GET",
    url: `http://localhost:8080/oauth/callback/kakao?code=${code}&state=kakao&prompt=none`,
  })
    .then((res) => {
      console.log(res); // 토큰이 넘어올 것임
      if (res.data.accessToken) {
        // 쿠키에 access-token 저장
        setCookie("token", `${res.data.accessToken}`, {
          path: "/", // 모든 페이지에서 쿠키 접근 가능
          sameSite: "strict",
        });
      }
      window.location.href = `http://localhost:3000/`; // 토큰 받았았고 로그인됐으니 화면 전환시켜줌(메인으로)
    })
    .catch((err) => {
      console.log("소셜로그인 에러", err);
      window.alert("로그인에 실패하였습니다.");
      window.location.href = `http://localhost:3000/login`; // 로그인 실패하면 로그인화면으로 돌려보냄
    });
  // };
};

export const googleLogin = async (code) => {
  axios({
    method: "GET",
    url: `http://localhost:8080/oauth/callback/google?code=${code}&state=google`,
  })
    .then((res) => {
      console.log(res);
      if (res.data.accessToken) {
        // 쿠키에 access-token 저장
        setCookie("token", `${res.data.accessToken}`, {
          path: "/", // 모든 페이지에서 쿠키 접근 가능
          sameSite: "strict",
        });
      }
      window.location.href = `http://localhost:3000/`;
    })
    .catch((err) => {
      console.log("소셜로그인 에러", err);
      window.alert("로그인에 실패하였습니다.");
      window.location.href = `http://localhost:3000/login`;
    });
};

export const naverLogin = async (code) => {
  axios({
    method: "GET",
    url: `http://localhost:8080/oauth/callback/naver?code=${code}&state=naver`,
  })
    .then((res) => {
      console.log(res);
      if (res.data.accessToken) {
        // 쿠키에 access-token 저장
        setCookie("token", `${res.data.accessToken}`, {
          path: "/", // 모든 페이지에서 쿠키 접근 가능
          sameSite: "strict",
        });
      }
      window.location.href = `http://localhost:3000/`;
    })
    .catch((err) => {
      console.log("소셜로그인 에러", err);
      window.alert("로그인에 실패하였습니다.");
      window.location.href = `http://localhost:3000/login`;
    });
};
