import React from 'react';
import './../8_music/Music.css'
import { useNavigate } from "react-router-dom";


export default function Music() {
  const movePage = useNavigate();

  function gomusicresult() {
    movePage("/musicresult");
  }
  return (
    <div>
      <div className="rectangle">
        <textarea className="text" placeholder="음악을 추천받고 싶은 사연을 작성해주세요." maxLength={200} rows="10" required></textarea>
      </div>
      <button className='registeBtn' onClick={gomusicresult}>버튼</button>
      <h3>애니메이션 추가</h3>
    </div>
  );
}

