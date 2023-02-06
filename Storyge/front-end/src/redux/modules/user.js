import axios from "axios";

// const kakaoLogin = (code) => {
//   return function (dispatch, getState) {
//     axios({
//       method: "GET",
//       url: `http://localhost:3000/?code=${code}&state=kakao`,
//     })
//       .then((res) => {
//         //get token
//         console.log(res);

//         const ACCESS_TOKEN = res.data.accessToken;

//         //local store (temp)
//         localStorage.setItem("token", ACCESS_TOKEN);

//         window.alert("Login success...");
//         //get token -> change page to HOME
//         //history.replace("/home");
//       })
//       .catch((err) => {
//         console.log("Login error", err);
//         window.alert("Login failed...");
//         //history.replace("/home");
//       });
//   };
// };

// const actionCreators = { kakaoLogin };

// export { actionCreators };

export const kakaoLogin = async (code) => {
  // return await function (dispatch, getState, { history }) {
  axios({
    method: "GET",
    url: `http://localhost:8080/oauth/callback/kakao?code=${code}&state=kakao`
  })
    .then((res) => {
      console.log(res); // 토큰이 넘어올 것임

      const ACCESS_TOKEN = res.data.accessToken;

      localStorage.setItem("token", ACCESS_TOKEN); //예시로 로컬에 저장함

      // history.replace("/intro"); // 토큰 받았았고 로그인됐으니 화면 전환시켜줌(메인으로)
    })
    .catch((err) => {
      console.log("소셜로그인 에러", err);
      window.alert("로그인에 실패하였습니다.");
      // history.replace("/login"); // 로그인 실패하면 로그인화면으로 돌려보냄
    });
  // };
};