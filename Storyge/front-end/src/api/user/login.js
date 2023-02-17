import axios from "axios";
import { setCookie } from "utils/Cookies";

// const CUR_URL = "localhost:3000";
const CUR_URL = "storyge.xyz";

export const kakaoLogin = async (code) => {
  axios({
    method: "GET",
    url: `https://storyge.xyz/api/oauth/callback/kakao?code=${code}&state=kakao&prompt=none`,
  })
    .then((res) => {
      if (res.data.accessToken) {
        setCookie("token", `Bearer ${res.data.accessToken}`, {
          path: "/", // 모든 페이지에서 쿠키 접근 가능
          sameSite: "strict",
        });
      }
      if (res.data.user === true) {
        // 이미 가입된 사용자인 경우,
        window.location.href = `http://${CUR_URL}/`; // 토큰 받았았고 로그인됐으니 화면 전환시켜줌(메인으로)
      } else {
        // 새로 가입한 사용자인 경우,
        window.location.href = `http://${CUR_URL}/modify`;
      }
    })
    .catch((err) => {
      window.alert("로그인에 실패하였습니다.");
      window.location.href = `http://${CUR_URL}/login`; // 로그인 실패하면 로그인화면으로 돌려보냄
    });
};

export const googleLogin = async (code) => {
  axios({
    method: "GET",
    url: `https://storyge.xyz/api/oauth/callback/google?code=${code}&state=google`,
  })
    .then((res) => {
      const ACCESS_TOKEN = res.data.accessToken;
      const REFRESH_TOKEN = res.data.refreshToken;
      const EXPIRATION_TIME = res.data.refreshTokenExpirationTime;

      // 로컬 스토리지 : accessToken,토큰 만료시간
      // 쿠키 : refreshToken
      if (ACCESS_TOKEN) {
        localStorage.setItem("accessToken", ACCESS_TOKEN);
        localStorage.setItem("expirationTime", EXPIRATION_TIME);

        setCookie("token", `Bearer ${ACCESS_TOKEN}`, {
          path: "/", // 모든 페이지에서 쿠키 접근 가능
          sameSite: "strict",
        });
      }
      window.location.href = `http://${CUR_URL}/modify`;
    })
    .catch((err) => {
      window.alert("로그인에 실패하였습니다.");
      window.location.href = `http://${CUR_URL}/login`;
    });
};

export const naverLogin = async (code) => {
  axios({
    method: "GET",
    url: `https://storyge.xyz/api/oauth/callback/naver?code=${code}&state=naver`,
  })
    .then((res) => {
      if (res.data.accessToken) {
        // 쿠키에 access-token 저장
        setCookie("token", `Bearer ${res.data.accessToken}`, {
          path: "/", // 모든 페이지에서 쿠키 접근 가능
          sameSite: "strict",
        });
      }
      window.location.href = `http://${CUR_URL}/modify`;
    })
    .catch((err) => {
      window.alert("로그인에 실패하였습니다.");
      window.location.href = `http://${CUR_URL}/login`;
    });
};
