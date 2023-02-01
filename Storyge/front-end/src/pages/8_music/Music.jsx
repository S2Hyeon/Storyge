import React from "react";
import "./../8_music/Music.css";
import { useNavigate } from "react-router-dom";
import * as G from "../../styles";

export default function Music() {
  const movePage = useNavigate();

  function gomusicresult() {
    movePage("/musicresult");
  }
  return (
    <G.BodyContainer>
      <div className="rectangle">
        <textarea
          className="text"
          placeholder="노래를 추천받을&#13;&#10;오늘 하루의 글을 작성해주세요."
          maxLength={200}
          rows="10"
          required
        ></textarea>
      </div>
      <button className="registeBtn" onClick={gomusicresult}>
        버튼
      </button>
      <h3>애니메이션 추가</h3>
    </G.BodyContainer>
  );
}
