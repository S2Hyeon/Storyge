import React from "react";
import { useNavigate } from "react-router-dom";

export default function Intro() {
  const movePage = useNavigate();

    function gologin(){
        movePage('/login');
    }

    function gologininfo() {
        movePage('/logininfo')
    }

    function gomusic() {
        movePage('/music')
    }
    function goCreateDiary() {
        movePage('/createDiary');
    }

    return (
        <div>
            <button onClick={gologin}>로그인으로 이동</button>
            <button onClick={gologininfo}>로그인 추가 정보 입력으로 이동</button>
            <button onClick={gomusic}>음악 추천으로 이동</button>
            <button onClick={goCreateDiary}>일기작성페이지로 이동</button>

        </div>
    );
}
