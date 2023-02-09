import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
import * as G from "../../styles";
import * as S from "./Music.js";
import { OpenAI } from "../../openai/OpenAI";
import MusicResult from "./MusicResult"
// import { reject } from "q";
// import { resolve } from "path";

export default function Music() {
  const movePage = useNavigate();
  const [result, setResult] = useState("분석전");
  const [content, setContent] = useState("");
  const [link, setLink] = useState(false)

  // function gomusicresult() {
  //   movePage("/musicresult");
  // }
  return (
    <G.BodyContainer>
      <S.Rectangle
        placeholder="음악 추천을 받고 싶은 사연을 작성해주세요."
        value={content}
        onChange={(e) => {
          setContent(e.target.value);
        }}
      />
      <G.longBtnDefault
        onClick={async () => {
          setResult(await OpenAI({ input: content, type: 0 }));
          console.log("분석한 데이터 result -> " + result);
        }}
        style={{ marginBottom: "20px" }}
      >
        <S.Text>분석하기</S.Text>
      </G.longBtnDefault>
      <div>{result}</div>
      {/* {link && <MusicResult link={}/>} */}
    </G.BodyContainer>
  );
}
