import React, {useState, useEffect} from "react";
import "./App.css";
import { BrowserRouter } from "react-router-dom";
import AppRouter from "./router/AppRouter";
import { debounce } from 'lodash';
import Size from 'pages/0_intro/Size.jsx';
import ErrorPage from 'pages/9_errorPage/ErrorPage';

function App() {
  const [isConnected, setIsConnected] = useState(false);

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

  const handleConnection = debounce(() => {
    setIsConnected(window.navigator.onLine);
  }, 1000);

  useEffect(() => {
    handleConnection();
  }, [isConnected]);

  return (
    <div className="App">
      {isConnected ?       <BrowserRouter>
        {(windowSize.width < 440 && windowSize.height < 900) ? <AppRouter /> : <Size width={ window.width} height={ window.height} />}
      </BrowserRouter> : <ErrorPage text="network"/>}

    </div>
  );
}

export default App;
