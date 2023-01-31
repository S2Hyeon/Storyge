import React from 'react';
import '../1_login/Login.css'
import { AiFillFacebook } from "react-icons/ai";
import { FcGoogle } from "react-icons/fc";
import { RiKakaoTalkFill } from "react-icons/ri";

export default function Login() {
    return (
        <div className='center'>
            <div className='login'>
                <h1>Login</h1>

                <a href="#!" className='login__btn__google' onClick={() => alert("google")}>
                    <FcGoogle className='icon'/>
                    Google Login
                </a>
                <button href="#!" className='login__btn__google' onClick={() => alert("google")}>
                    <FcGoogle className='icon'/>
                    <div>Google Login</div>
                </button>

                <button href="#!" className='login__btn__kakao' type="button" onClick={() => alert("kakao")}>
                    <RiKakaoTalkFill className='icon'/>
                    <div>Kakao Login</div>
                </button>
                
                <button href="#!" className='login__btn__facebook' type="button" onClick={() => alert("facebook")}>
                    <AiFillFacebook className='icon'/>
                    <div>Facebook Login</div>
                </button>
            </div>
        </div>
    );
}

