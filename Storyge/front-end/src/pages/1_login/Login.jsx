import React from 'react';
import '../1_login/login.css'
import { AiFillFacebook } from "react-icons/ai";
import { FcGoogle } from "react-icons/fc";
import { RiKakaoTalkFill } from "react-icons/ri";

export default function Login() {
    return (
        <div className='login'>
            <h1>Login</h1>

            <a href="#!" className='login__btn__google' onClick={() => alert("google")}>
                <FcGoogle />
                Google Login
            </a>

            <a href="#!" className='login__btn__kakao' type="button" onClick={() => alert("kakao")}>
                <RiKakaoTalkFill />
                Kakao Login
            </a>
            
            <a href="#!" className='login__btn__facebook' type="button" onClick={() => alert("facebook")}>
                <AiFillFacebook />
                Facebook Login
            </a>
        </div>
    );
}

