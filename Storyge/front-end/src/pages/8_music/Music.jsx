import React from "react";
import { useNavigate } from "react-router-dom";
import * as G from "../../styles";
import * as S from "./Music.js";

export default function Music() {
  const movePage = useNavigate();

  function gomusicresult() {
    movePage("/musicresult");
  }
  return (
    <G.BodyContainer>
      <S.Rectangle placeholder="음악 추천을 받고 싶은 사연을 작성해주세요." />
      <G.longBtnDefault
        onClick={gomusicresult}
        style={{ marginBottom: "20px" }}
      >
        <S.Text>분석하기</S.Text>
      </G.longBtnDefault>
    </G.BodyContainer>
  );
}
