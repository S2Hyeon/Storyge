import React from 'react';
import * as G from './../../styles/index';
import * as S from './MyPage';
import ProfileBox from './../../components/profileBox/ProfileBox.jsx';
import { useNavigate } from "react-router-dom";
import { BsPersonCircle } from "react-icons/bs";

export default function MyPage() {
  const movePage = useNavigate();

  function gomodifyprofile() {
    movePage("/modify");
  }

  return (
    <G.BodyContainer>
      <ProfileBox />
      <S.Menu onClick={gomodifyprofile}>
        <BsPersonCircle style={{color: '#ACCEBC', width: '30px', height: '30px'}}/>
        <S.Text>프로필 수정하기</S.Text>
      </S.Menu>
      <G.longBtnDefault></G.longBtnDefault>
    </G.BodyContainer>
  );
}

