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

import Creatediary from "pages/3_myDiary/create/DiaryCreate";
// import Diarylist from "../pages/3_myDiary/Diarylist";
import Diarylist from "pages/3_myDiary/list/DiaryList";
import Diarydetial from "pages/3_myDiary/detail/DiaryDetail";

import MyPage from "./../pages/4_myPage/MyPage.jsx";
import ModifyProfile from "./../pages/4_myPage/ModifyProfile.jsx";
import Alarm from "./../pages/10_alarm/Alarm.jsx";
import Follow from "../pages/5_follow/Follow.jsx";
import OtherPage from "../pages/6_otherPage/OtherProfile";
import OtherDiaryList from "pages/7_otherDiary/otherDiaryList/OtherDiaryList";
import OtherDiaryDetail from "pages/7_otherDiary/otherDiaryDetail/OtherDiaryDetail";

import OAuth2RedirectHandler from "pages/1_login/OAuth2RedirectHandler";
// import { Pages } from "@mui/icons-material";

function AppRouter() {
  return (
    <Routes>
      {/* 헤더, 내브바 포함 X */}
      <Route path="intro" element={<Intro />} />
      <Route path="login" element={<Login />} />
      <Route path="logininfo" element={<LoginInfo />} />
      <Route
        path="/oauth/callback/kakao"
        element={<OAuth2RedirectHandler />}
      ></Route>
      <Route
        path="/oauth/callback/google"
        element={<OAuth2RedirectHandler />}
      ></Route>

      {/* 헤더, 내브바 포함 O */}
      <Route path="/" element={<Layout />}>
        <Route index path="/" element={<Main />} />
        <Route path="music" element={<Music />} />
        <Route path="createDiary" element={<Creatediary />} />
        <Route path="diarylist" element={<Diarylist />} />
        <Route path="diary" element={<Diarydetial />} />
        <Route path="musicresult" element={<MusicResult />} />
        <Route path="mypage" element={<MyPage />} />
        <Route path="modify" element={<ModifyProfile />} />
        <Route path="follower" element={<Follow />} />
        <Route path="following" element={<Follow />} />
        <Route path="alarm" element={<Alarm />} />
        <Route path="otherpage" element={<OtherPage />} />
        <Route path="otherdiarylist" element={<OtherDiaryList />} />
        <Route path="otherdiarydetail" element={<OtherDiaryDetail />} />

        <Route path="/*" element={<ErrorPage />} />
      </Route>
    </Routes>
  );
}

export default AppRouter;
//
