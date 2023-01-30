import "./App.css";
import { BrowserRouter, Routes, Route } from "react-router-dom";
import Login from "./pages/1_login/Login";
import Intro from "./pages/0_intro/Intro";
import Main from "./pages/2_main/Main";

function App() {
  return (
    <div className="App">
      <BrowserRouter>
        <Routes>
          <Route path="/" element={<Intro />} />
          <Route path="login" element={<Login />} />
          <Route path="main" element={<Main />} />
        </Routes>
      </BrowserRouter>
    </div>
  );
}

export default App;
