import React from "react";
import { useNavigate } from "react-router-dom";
import * as S from "./../profileBox/ProfileBoxStyle.js";

export default function ProfileBox(props) {
  const movePage = useNavigate();

  //다른 사람의 profileBox인지 확인!
  

  function gofollowerlist() {
    movePage("/follower");
  }

  function gofollowinglist() {
    movePage("/following");
  }

  return (
    <S.Box>
      <S.Img src={props.profileImg} alt="img" />
      <S.Name>{props.nickname}</S.Name>
      <S.FollowBox>
        <div onClick={gofollowerlist}>
          <S.FollowText>팔로워</S.FollowText>
          <S.FollowNumber>{props.follower}</S.FollowNumber>
        </div>
        <div onClick={gofollowinglist}>
          <S.FollowText>팔로잉</S.FollowText>
          <S.FollowNumber>{props.following}</S.FollowNumber>
        </div>
      </S.FollowBox>
    </S.Box>
  );
}
