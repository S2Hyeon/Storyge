import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
import * as G from "../../styles";
import * as S from "./Music.js";
import { OpenAI } from "../../openai/OpenAI";

export default function Music() {
  const movePage = useNavigate();
  const [result, setResult] = useState("뽕");

  function getMusic() {
    
  }

  function gomusicresult() {
    movePage("/musicresult");
  }
  return (
    <G.BodyContainer>
      <S.Rectangle placeholder="음악 추천을 받고 싶은 사연을 작성해주세요." />
      <G.longBtnDefault
        onClick={() => {
          setResult(OpenAI({ input: "기분이 너무 꿀꿀해요", type: 0 }));
          console.log(
            typeof OpenAI({ input: "기분이 너무 꿀꿀해요", type: 0 })
          );
        }}
        style={{ marginBottom: "20px" }}
      >
        <S.Text>분석하기</S.Text>
      </G.longBtnDefault>
      <div>{result}</div>
    </G.BodyContainer>
  );
}
