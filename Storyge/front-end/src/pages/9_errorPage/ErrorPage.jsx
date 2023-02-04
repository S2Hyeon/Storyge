import React from "react";
import * as S from "./ErrorPageStyle";
import ErrorIcon from "../../assets/errorIcon.png";

function ErrorPage() {
  return (
    <S.Container>
      <div>
        <S.Img src={ErrorIcon} alt="404 error image" />
        <S.Oops>Oops!</S.Oops>
        <div>없는 페이지 입니다!</div>
      </div>
    </S.Container>
  );
}

export default ErrorPage;
