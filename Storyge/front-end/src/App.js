import "./App.css";
import { BrowserRouter, Routes, Route } from 'react-router-dom';
import Login from './pages/1_login/Login';
import LoginInfo from './pages/1_login/LoginInfo';
import Intro from './pages/0_intro/Intro';
import Music from './pages/8_music/Music';

function App() {
  return (
    <div className="App">
      <h1>Intro</h1>

      <BrowserRouter>
          <Routes>
            <Route path="/" element={<Intro />} />
            <Route path="login" element={<Login />} />
            <Route path="logininfo" element={<LoginInfo />} />
            <Route path="music" element={ <Music /> } />
          </Routes>
      </BrowserRouter>
    </div>
  );
}

export default App;
