import React from "react";
import { Routes, Route } from "react-router-dom";
import { getCookie } from "./../utils/Cookies";
import PrivateRoute from "./PrivateRoute";
import PublicRoute from "./PublicRoute";
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
  const token = getCookie("token");
  let state = false;

  return (
    <Routes>
      {/* 헤더, 내브바 포함 X */}

      <Route
        path="intro"
        element={<PublicRoute component={<Intro />} authenticated={token} />}
      />
      <Route
        path="login"
        element={<PublicRoute component={<Login />} authenticated={token} />}
      />
      <Route path="logininfo" element={<LoginInfo />} />
      <Route
        path="/oauth/callback/kakao"
        element={
          <PublicRoute
            component={<OAuth2RedirectHandler />}
            authenticated={token}
          />
        }
      ></Route>
      <Route
        path="/oauth/callback/google"
        element={
          <PublicRoute
            component={<OAuth2RedirectHandler />}
            authenticated={token}
          />
        }
      ></Route>
      <Route
        path="/oauth/callback/naver"
        element={
          <PublicRoute
            component={<OAuth2RedirectHandler />}
            authenticated={token}
          />
        }
      ></Route>

      {/* 헤더, 내브바 포함 O */}
      {/* <Route path="/viewer/room" element={<PrivateRoute component={<RoomViewer/>} authenticated={token}/>}/>
       */}
      <Route path="/" element={<Layout />}>
        <Route
          index
          path="/"
          element={<PrivateRoute component={<Main />} authenticated={token} />}
        />
        <Route
          path="music"
          element={<PrivateRoute component={<Music />} authenticated={token} />}
        />
        <Route
          path="createDiary"
          element={
            <PrivateRoute component={<Creatediary />} authenticated={token} />
          }
        />
        <Route
          path="diarylist"
          element={
            <PrivateRoute component={<Diarylist />} authenticated={token} />
          }
        />
        <Route
          path="diary"
          element={
            <PrivateRoute component={<Diarydetial />} authenticated={token} />
          }
        />
        <Route
          path="musicresult"
          element={
            <PrivateRoute component={<MusicResult />} authenticated={token} />
          }
        />
        <Route
          path="mypage"
          element={
            <PrivateRoute component={<MyPage />} authenticated={token} />
          }
        />
        <Route
          path="modify"
          element={
            <PrivateRoute component={<ModifyProfile />} authenticated={token} />
          }
        />
        <Route
          path="follower"
          element={
            <PrivateRoute component={<Follow />} authenticated={token} />
          }
        />
        <Route
          path="following"
          element={
            <PrivateRoute component={<Follow />} authenticated={token} />
          }
        />
        <Route
          path="alarm"
          element={<PrivateRoute component={<Alarm />} authenticated={token} />}
        />
        <Route
          path="otherpage"
          element={
            <PrivateRoute component={<OtherPage />} authenticated={token} />
          }
        />
        <Route
          path="otherdiarylist"
          element={
            <PrivateRoute
              component={<OtherDiaryList />}
              authenticated={token}
            />
          }
        />
        <Route
          path="otherdiarydetail"
          element={
            <PrivateRoute
              component={<OtherDiaryDetail />}
              authenticated={token}
            />
          }
        />

        <Route path="/*" element={<ErrorPage />} />
      </Route>
    </Routes>
  );
}

export default AppRouter;
//
