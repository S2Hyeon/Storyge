import React from "react";
import { Routes, Route } from "react-router-dom";
import Layout from "./Layout";

import Intro from "../pages/0_intro/Intro";
import Login from "../pages/1_login/Login";
import LoginInfo from "../pages/1_login/LoginInfo";

import Main from "../pages/2_main/Main";
import Music from "../pages/8_music/Music.jsx";
import MusicResult from "../pages/8_music/MusicResult.jsx";
import ErrorPage from "../pages/9_errorPage/ErrorPage";

import Creatediary from "../pages/3_myDiary/Creatediary";
import Diarylist from "../pages/3_myDiary/Diarylist";
import Diarydetial from "../pages/3_myDiary/Diarydetail";

import MyPage from "./../pages/4_myPage/MyPage.jsx";
import ModifyProfile from "./../pages/4_myPage/ModifyProfile.jsx";
import Alarm from "./../pages/10_alarm/Alarm.jsx";
import Follow from '../pages/5_follow/Follow.jsx';

function AppRouter() {
  return (
    <Routes>
      {/* 헤더, 내브바 포함 X */}
      <Route path="intro" element={<Intro />} />
      <Route path="login" element={<Login />} />
      <Route path="logininfo" element={<LoginInfo />} />

      {/* 헤더, 내브바 포함 O */}
      <Route path="/" element={<Layout />}>
        <Route index path="/" element={<Main />} />
        <Route path="music" element={<Music />} />
        <Route path="createDiary" element={<Creatediary />} />
        <Route path="0201" element={<Diarylist />} />
        <Route path="02011" element={<Diarydetial />} />
        <Route path="musicresult" element={<MusicResult />} />
        <Route path="mypage" element={<MyPage />} />
        <Route path="modify" element={<ModifyProfile />} />
        <Route path="follower" element={<Follow />} />
        <Route path="following" element={<Follow />} />
        <Route path="alarm" element={<Alarm />} />

        <Route path="/*" element={<ErrorPage />} />
      </Route>
    </Routes>
  );
}

export default AppRouter;
