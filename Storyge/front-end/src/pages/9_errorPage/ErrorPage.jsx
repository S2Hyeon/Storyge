import React from "react";
import * as G from "./../../styles/index";
import * as S from "./ErrorPageStyle";
import CryLottie from "../../api/animation/CryLottie.jsx";

function ErrorPage(props) {
  return (
    <G.BodyContainer>
      <S.Container>
        {/* <S.Img src={ErrorIcon} alt="404 error image" /> */}
        <CryLottie />
        <S.Oops>Oops!</S.Oops>
        <S.Text>
          {props.text === "404"
            ? `유효한 주소를 입력해주세요.`
            : "네트워크 오류입니다."}
        </S.Text>
      </S.Container>
    </G.BodyContainer>
  );
}

export default ErrorPage;
