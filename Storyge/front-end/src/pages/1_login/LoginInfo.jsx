import React from 'react';
import ProfileBox from './../../components/profileBox/ProfileBox';
import '../1_login/login.css'
import RegisterBtn from './../../components/button/RegisterBtn';
export default function LoginInfo() {
    return (
        <div className='addInfo'>
            <h2>Set your Nickname</h2>
            <ProfileBox />
            <input class="underline" type="text" /> 
            <RegisterBtn />
        </div>
    );
}

