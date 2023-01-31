import "./App.css";
import { BrowserRouter, Routes, Route } from "react-router-dom";
import Login from "./pages/1_login/Login";
import LoginInfo from "./pages/1_login/LoginInfo";
import Intro from "./pages/0_intro/Intro";
import Music from "./pages/8_music/Music";
import Creatediary from "./pages/3_myDiary/Creatediary";
import Main from "./pages/2_main/Main";
import MusicResult from './pages/8_music/MusicResult';

function App() {
  return (
    <div className="App">
      <BrowserRouter>
        <Routes>
          <Route path="/" element={<Intro />} />
          <Route path="login" element={<Login />} />
          <Route path="logininfo" element={<LoginInfo />} />
          <Route path="music" element={<Music />} />
          <Route path="createDiary" element={<Creatediary />} />
          <Route path="main" element={<Main />} />
          <Route path="musicresult" element={<MusicResult />} />
        </Routes>
      </BrowserRouter>
    </div>
  );
}

export default App;
