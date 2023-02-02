import React from 'react';
import * as G from './../../styles/index';
import * as S from './../8_music/Music.js'
import ReactPlayer from 'react-player'

export default function MusicResult() {
  return (
    <G.BodyContainer>
      <S.Rectangle />
      <G.longBtnDisabled style={{marginBottom: '20px'}}><S.Text>분석하기</S.Text></G.longBtnDisabled>
      
      {/* Youtube Player 자동실행 */}
      <ReactPlayer url='https://www.youtube.com/watch?v=a7Xl-eeSFuc' width='300px' height='200px' playing='true'
      style={{margin: 'auto'}}/>
    </G.BodyContainer>
  );
}

