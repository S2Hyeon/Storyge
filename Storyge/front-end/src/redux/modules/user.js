import axios from "axios";
import { setCookie } from "utils/Cookies";

const ServerURL = "localhost:8080";
const localURL = "localhost:3030";

export const kakaoLogin = async (code) => {
  axios({
    method: "GET",
    // url: `http://localhost:8080/api/oauth/callback/kakao?code=${code}&state=kakao&prompt=none`,
    url: `https://storyge.xyz/api/oauth/callback/kakao?code=${code}&state=kakao&prompt=none`,
  })
    .then((res) => {
      console.log(res);
      if (res.data.accessToken) {
        setCookie("token", `Bearer  ${res.data.accessToken}`, {
          path: "/", // 모든 페이지에서 쿠키 접근 가능
          sameSite: "strict",
        });
        // axios.defaults.headers.common[
        //   "Authorization"
        // ] = `Bearer ${res.data.accessToken}`;
      }
      window.location.href = `http://localhost:3000/`; // 토큰 받았았고 로그인됐으니 화면 전환시켜줌(메인으로)
      // window.location.href = `http://storyge.xyz/`; // 토큰 받았았고 로그인됐으니 화면 전환시켜줌(메인으로)
    })
    .catch((err) => {
      console.log("소셜로그인 에러", err);
      window.alert("로그인에 실패하였습니다.");
      // window.location.href = `http://localhost:3000/login`; // 로그인 실패하면 로그인화면으로 돌려보냄
      // window.location.href = `http://storyge.xyz/login`; // 로그인 실패하면 로그인화면으로 돌려보냄
    });
};

export const googleLogin = async (code) => {
  axios({
    method: "GET",
    url: `http://${ServerURL}/oauth/callback/google?code=${code}&state=google`,
  })
    .then((res) => {
      console.log("구글 로그인 성공");
      console.log(res);

      const ACCESS_TOKEN = res.data.accessToken;
      const REFRESH_TOKEN = res.data.refreshToken;
      const EXPIRATION_TIME = res.data.refreshTokenExpirationTime;

      console.log(ACCESS_TOKEN);
      console.log(REFRESH_TOKEN);
      console.log(EXPIRATION_TIME);

      // 로컬 스토리지 : accessToken,토큰 만료시간
      // 쿠키 : refreshToken
      if (ACCESS_TOKEN) {
        localStorage.setItem("accessToken", ACCESS_TOKEN);
        localStorage.setItem("expirationTime", EXPIRATION_TIME);

        setCookie("accessToken", `Bearer ${ACCESS_TOKEN}`, {
          path: "/", // 모든 페이지에서 쿠키 접근 가능
          sameSite: "strict",
        });
      }
      window.location.href = `http://localhost:3000/`;
      // window.location.href = `http://storyge.xyz/`;
    })
    .catch((err) => {
      console.log("소셜로그인 에러", err);
      window.alert("로그인에 실패하였습니다.");
      window.location.href = `http://localhost:3000/login`;
      // window.location.href = `http://storyge.xyz/login`;
    });
};

export const naverLogin = async (code) => {
  axios({
    method: "GET",
    url: `http://${ServerURL}/oauth/callback/naver?code=${code}&state=naver`,
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
      // window.location.href = `http://storyge.xyz/`;
    })
    .catch((err) => {
      console.log("소셜로그인 에러", err);
      window.alert("로그인에 실패하였습니다.");
      window.location.href = `http://localhost:3000/login`;
      // window.location.href = `http://storyge.xyz/login`;
    });
};
