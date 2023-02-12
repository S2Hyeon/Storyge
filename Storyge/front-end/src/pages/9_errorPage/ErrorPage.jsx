import React from "react";
import * as G from "./../../styles/index";
import * as S from "./ErrorPageStyle";
import ErrorIcon from "../../assets/errorIcon.png";

function ErrorPage(props) {
  return (
    <G.BodyContainer>
        <S.Container>
          <S.Img src={ErrorIcon} alt="404 error image" />
          <S.Oops>Oops!</S.Oops>
        <S.Text>{ props.text == "404" ? "없는 페이지입니다.": "네트워크 오류입니다."}</S.Text>
      </S.Container>
    </G.BodyContainer>
      
  );
}

export default ErrorPage;
