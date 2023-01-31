import React from 'react';
import '../1_login/login.css'
import { AiFillFacebook } from "react-icons/ai";
import { FcGoogle } from "react-icons/fc";
import { RiKakaoTalkFill } from "react-icons/ri";

export default function Login() {

    return (
        
        <div className='login'>
            <h1>Login</h1>
            <button className='login__btn__facebook' onClick={() => alert("facebook")}>
                <a href="#1">
                    <AiFillFacebook />
                    Facebook Login
                </a>
            </button>

            <button className='login__btn__google' onClick={() => alert("google")}>
                <FcGoogle />
                Google Login
            </button>

            <button className='login__btn__kakao' onClick={() => alert("kakao")}>
                <RiKakaoTalkFill />
                Kakao Login
            </button>
        </div>
    );
}

