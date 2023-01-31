import React from 'react';
import { useNavigate } from "react-router-dom";

export default function Intro() {
    const movePage = useNavigate();

    function gologin(){
        movePage('/login');
    }
    
    function goCreateDiary() {
        movePage('/createDiary');
    }

    return (
        <div>
            <button onClick={gologin}>로그인으로이동</button>
            <button onClick={goCreateDiary}>일기작성페이지로 이동</button>
        </div>
    );
}

