import React from 'react';
import './../8_music/MusicResult.css'
import ReactPlayer from 'react-player'

export default function MusicResult() {
  return (
    <div>
      <div className='center'>
        <div className="rectangle">
          <textarea></textarea>
        </div>
        <button className='registeBtn' disabled>버튼</button>
        {/* Youtube Player 자동실행 */}
        <ReactPlayer url='https://www.youtube.com/watch?v=a7Xl-eeSFuc' width='300px' height='200px' playing='true'
        style={{margin: 'auto'}}/>
      </div>
    </div>
  );
}

