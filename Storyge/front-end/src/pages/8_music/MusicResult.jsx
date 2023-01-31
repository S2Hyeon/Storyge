import React from 'react';
import Header from './../../common/header/Header';
import Nav from './../../common/footer/Nav';
import './../8_music/MusicResult.css'
import ReactPlayer from 'react-player'

export default function MusicResult() {
  return (
    <div>
      <Header />
      <div className='center'>
        <div className="rectangle">
          <textarea></textarea>
        </div>
        <button className='registeBtn' disabled>버튼</button>
        {/* Youtube Player 자동실행 */}
        <ReactPlayer url='https://www.youtube.com/watch?v=ysz5S6PUM-U' width='300px' height='200px' playing='true'
        style={{margin: 'auto'}}/>
      </div>
      <Nav />
    </div>
  );
}

