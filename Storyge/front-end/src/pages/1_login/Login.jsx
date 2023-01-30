import React from 'react';
import { FacebookLoginButton, GoogleLoginButton } from "react-social-login-buttons";
import KakaologinBtn from '../../components/button/KakaologinBtn';
import '../1_login/login.css'

export default function Login() {
    return (
        <div className='login'>
            <h1>Login</h1>
            <FacebookLoginButton style={{width: "242px", margin:"auto"}} onClick={() => alert("Hello")}>
                <span>Facebook Login</span>
            </FacebookLoginButton>
            <GoogleLoginButton style={{width: "242px", margin:"auto"}} onClick={() => alert("Hello")}>
                <span>Google Login</span>
            </GoogleLoginButton>
            <KakaologinBtn onClick={() => alert("Hello")}>
            </KakaologinBtn>
        </div>
    );
}

