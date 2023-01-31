import React from 'react';
import Header from './../../common/header/Header';
import Nav from './../../common/footer/Nav';
import './../8_music/Music.css'
import { useNavigate } from "react-router-dom";


export default function Music() {
  const movePage = useNavigate();

  function gomusicresult() {
    movePage("/musicresult");
  }
  return (
    <div>
      <Header />
      <div className="rectangle">
        <textarea></textarea>
      </div>
      <button className='registeBtn' onClick={gomusicresult}>버튼</button>
      <h3>애니메이션 추가</h3>
      <Nav />
    </div>
  );
}

