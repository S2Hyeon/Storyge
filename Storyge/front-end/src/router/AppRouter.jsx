import React from "react";
import { Router, Routes, Route } from "react-router-dom";
import Layout from "./Layout";

import Intro from "../pages/0_intro/Intro";
import Login from "../pages/1_login/Login";
import LoginInfo from "../pages/1_login/LoginInfo";

import Main from "../pages/2_main/Main";
import Music from "../pages/8_music/Music";
import Creatediary from "../pages/3_myDiary/Creatediary";
import MusicResult from "../pages/8_music/MusicResult";

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
        <Route path="musicresult" element={<MusicResult />} />
      </Route>
    </Routes>
  );
}

export default AppRouter;
