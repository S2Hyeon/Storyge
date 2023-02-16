import React, { useState } from 'react'
import { Routes, Route } from 'react-router-dom'
import { getCookie } from './../utils/Cookies'
import PrivateRoute from './PrivateRoute'
import PublicRoute from './PublicRoute'
import Layout from './Layout'

import Intro from '../pages/0_intro/Intro'
import Login from '../pages/1_login/Login'
import LoginInfo from '../pages/1_login/LoginInfo'

import Main from '../pages/2_main/Main'
import Music from '../pages/8_music/Music.jsx'
import MusicResult from '../pages/8_music/MusicResult.jsx'
import ErrorPage from '../pages/9_errorPage/ErrorPage'

import Modifydiary from 'pages/3_myDiary/modify/DiaryModify'
import Creatediary from 'pages/3_myDiary/create/DiaryCreate'
// import Diarylist from "../pages/3_myDiary/Diarylist";
import Diarylist from 'pages/3_myDiary/list/DiaryList'
import Diarydetial from 'pages/3_myDiary/detail/DiaryDetail'

import MyPage from './../pages/4_myPage/MyPage.jsx'
import ModifyProfile from './../pages/4_myPage/ModifyProfile.jsx'
import Alarm from './../pages/10_alarm/Alarm.jsx'
import Follow from '../pages/5_follow/Follow.jsx'
import OtherPage from '../pages/6_otherPage/OtherProfile'

import OAuth2RedirectHandler from 'pages/1_login/OAuth2RedirectHandler'
// import { Pages } from "@mui/icons-material";

function AppRouter() {
  // const [token, setToken] = useState("");
  // let token = getCookie("token");
  const [token, setToken] = useState(getCookie('token'))

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
      <Route
        path="logininfo"
        element={
          <PrivateRoute component={<LoginInfo />} authenticated={token} />
        }
      />
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
          element={
            <PrivateRoute
              component={token ? <Main /> : <Login />}
              authenticated={token}
            />
          }
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
          path="modifyDiary"
          element={
            <PrivateRoute component={<Modifydiary />} authenticated={token} />
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
            <PrivateRoute
              component={<MyPage setToken={setToken} />}
              authenticated={token}
            />
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
            <PrivateRoute
              component={<Follow status={true} />}
              authenticated={token}
            />
          }
        />
        <Route
          path="following"
          element={
            <PrivateRoute
              component={<Follow status={false} />}
              authenticated={token}
            />
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
        <Route path="/*" element={<ErrorPage text="404" />} />
      </Route>
    </Routes>
  )
}

export default AppRouter
//
