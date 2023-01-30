import React from 'react';
import { useNavigate } from "react-router-dom";

export default function Intro() {
    const movePage = useNavigate();

    function gologin(){
        movePage('/login');
    }

    function gologininfo() {
        movePage('/logininfo')
    }

    return (
        <div>
            <button onClick={gologin}>로그인으로이동</button>
            <button onClick={gologininfo}>로그인 추가 정보 입력으로이동</button>
        </div>
    );
}

