import React from "react";
import { useNavigate } from "react-router-dom";

export default function Intro() {
  const movePage = useNavigate();

  function gologin() {
    movePage("/login");
  }

  function gologininfo() {
    movePage("/logininfo");
  }

  function gomusic() {
    movePage("/music");
  }
  function goCreateDiary() {
    movePage("/createDiary");
  }
  function goMain() {
    movePage("/");
  }
  function goDiaryList() {
    movePage("/0201");
  }
  function goDiaryDetail() {
    movePage("/02011");
  }
  function goOtherPage() {
    movePage("/otherpage");
  }

  return (
    <div>
      <button onClick={gologin}>로그인으로 이동</button>
      <button onClick={gologininfo}>로그인 추가 정보 입력으로 이동</button>
      <button onClick={gomusic}>음악 추천으로 이동</button>
      <button onClick={goCreateDiary}>일기작성페이지로 이동</button>
      <button onClick={goMain}>메인페이지로 이동</button>
      <button onClick={goDiaryList}>일기 리스트로 이동</button>
      <button onClick={goDiaryDetail}>일기 디테일로 이동</button>
      <button onClick={goOtherPage}>다른 사람 메인페이지</button>
    </div>
  );
}
