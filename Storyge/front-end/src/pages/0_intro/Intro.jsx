import React from 'react';
import { useNavigate } from "react-router-dom";

export default function Intro() {
    const movePage = useNavigate();

    function gologin(){
        movePage('/login');
    }

    return (
        <div>
            <button onClick={gologin}>로그인으로이동</button>
        </div>
    );
}

