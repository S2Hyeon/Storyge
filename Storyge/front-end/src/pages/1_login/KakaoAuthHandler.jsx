// import React, { useEffect } from "react";
// import { actionCreators as userActions } from "./../../redux/modules/user";
// import Spinner from "./Spinner";

// const KaKaoLoginHandler = (props) => {
//   //Dispatch: store에 있는 데이터를 컨트롤 하기 위함
//   //인가 코드

//   useEffect(() => {
//     let code = new URL(window.location.href).searchParams.get("code");
//     userActions(code);
//   }, []);

//   return <Spinner />;
// };

// export default KaKaoLoginHandler;
import React, { useEffect } from "react";
import { kakaoLogin as userActions } from "./../../redux/modules/user.js";
import Spinner from "./Spinner.jsx";

const OAuth2RedirectHandler = (props) => {
  // 인가코드
  useEffect(() => {
    let code = new URL(window.location.href).searchParams.get("code");
    userActions(code);
  }, []);

  return <Spinner />;
};

export default OAuth2RedirectHandler;
