import axios from "axios";

const localAddress = "localhost:3000";

export const kakaoLogin = async (code) => {
  // return await function (dispatch, getState, { history }) {
  axios({
    method: "GET",
    url: `http://localhost:8080/oauth/callback/kakao?code=${code}&state=kakao`,
  })
    .then((res) => {
      console.log(res); // 토큰이 넘어올 것임
      const ACCESS_TOKEN = res.data.accessToken;
      localStorage.setItem("token", ACCESS_TOKEN); //예시로 로컬에 저장함
      window.location.replace = `http://${localAddress}/logininfo`; // 토큰 받았았고 로그인됐으니 화면 전환시켜줌(메인으로)
    })
    .catch((err) => {
      console.log("소셜로그인 에러", err);
      window.alert("로그인에 실패하였습니다.");
      window.location.replace = `http://${localAddress}/login`; // 로그인 실패하면 로그인화면으로 돌려보냄
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
      const ACCESS_TOKEN = res.data.accessToken;
      localStorage.setItem("token", ACCESS_TOKEN);
      window.location.replace = `http://${localAddress}/logininfo`;
    })
    .catch((err) => {
      console.log("소셜로그인 에러", err);
      window.alert("로그인에 실패하였습니다.");
      window.location.replace = `http://${localAddress}/login`;
    });
};

export const naverLogin = async (code) => {
  axios({
    method: "GET",
    url: `http://localhost:8080/oauth/callback/naver?code=${code}&state=naver`,
  })
    .then((res) => {
      console.log(res);
      const ACCESS_TOKEN = res.data.accessToken;
      localStorage.setItem("token", ACCESS_TOKEN);
      window.location.replace = `http://${localAddress}/logininfo`;
    })
    .catch((err) => {
      console.log("소셜로그인 에러", err);
      window.alert("로그인에 실패하였습니다.");
      window.location.replace = `http://${localAddress}/login`;
    });
};
