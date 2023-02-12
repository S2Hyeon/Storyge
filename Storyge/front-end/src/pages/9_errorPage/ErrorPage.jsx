import React from "react";
import * as G from "./../../styles/index";
import * as S from "./ErrorPageStyle";
import ErrorIcon from "../../assets/errorIcon.png";

function ErrorPage() {
  return (
    <G.BodyContainer>
        <S.Container>
          <S.Img src={ErrorIcon} alt="404 error image" />
          <S.Oops>Oops!</S.Oops>
          <S.Text>없는 페이지 입니다!</S.Text>
      </S.Container>
    </G.BodyContainer>
      
  );
}

export default ErrorPage;
