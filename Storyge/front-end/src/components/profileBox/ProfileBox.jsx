import React from 'react';
import { useNavigate } from "react-router-dom";
import * as S from "./../profileBox/ProfileBoxStyle.js";

export default function ProfileBox() {
  const movePage = useNavigate();

  function gofollowerlist() {
    movePage("/follower");
  }

  function gofollowinglist() {
    movePage("/following");
  }

  return (
      <S.Box>
        <S.Img src="https://cdn.pixabay.com/photo/2015/10/05/22/37/blank-profile-picture-973460__340.png" alt="img" />
        <S.Name>안태봉</S.Name>
        <S.FollowBox>
          <div onClick={gofollowerlist}>
            <S.FollowText>팔로워</S.FollowText>
            <S.FollowNumber>50</S.FollowNumber>
          </div>
          <div onClick={gofollowinglist}>
            <S.FollowText>팔로잉</S.FollowText>
            <S.FollowNumber>100</S.FollowNumber>
          </div>
        </S.FollowBox>
      </S.Box>
  );
}

