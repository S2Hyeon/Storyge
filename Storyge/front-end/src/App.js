import React, {useState, useEffect} from "react";
import "./App.css";
import { BrowserRouter } from "react-router-dom";
import AppRouter from "./router/AppRouter";
import { debounce } from 'lodash';
import Size from 'pages/0_intro/Size.jsx';

function App() {
  const [windowSize, setWindowSize] = useState({
    width: window.innerWidth,
    height: window.innerHeight
  });

  // handleResize 함수를 debounce로 감싸고, 시간을 설정한다
  // 1000ms = 1sec
  const handleResize = debounce(() => {
    setWindowSize({
      width: window.innerWidth,
      height: window.innerHeight
    });
  }, 100);
 
  useEffect(() => {
    window.addEventListener('resize', handleResize);
    return () => { // cleanup 
      window.removeEventListener('resize', handleResize);
    }
  }, []);


  return (
    <div className="App">
      <BrowserRouter>
        {(windowSize.width < 440 && windowSize.height < 900) ? <AppRouter /> : <Size width={ window.width} height={ window.height} />}
      </BrowserRouter>
    </div>
  );
}

export default App;
