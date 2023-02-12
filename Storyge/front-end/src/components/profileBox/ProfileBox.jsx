import React, { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import * as S from "./../profileBox/ProfileBoxStyle.js";
import * as G from "styles";
import jwt_decode from "jwt-decode";
import { getCookie } from "utils/Cookies.js";
import { getIsFollowing } from "api/user/getIsFollowing.js";
import { getIsWaitingConfirm } from "api/user/getIsWaitingConfirm.js";

export default function ProfileBox(props) {
  const movePage = useNavigate();

  //다른 사람의 profileBox인지 확인!
  const [isOtherProfileBox, setIsOtherProfileBox] = useState();
  useEffect(() => {
    if (props.otherUserId != null) {
      setIsOtherProfileBox(true);
    } else {
      setIsOtherProfileBox(false);
    }
  });

  //다른 사람을 팔로잉하고 있는지 확인!
  const [isFollowing, setIsFollowing] = useState();
  useEffect(() => {
    async function getAndSetIsFollowing() {
      if (isOtherProfileBox) {
        const response = await getIsFollowing(props.otherUserId);
        setIsFollowing(response);
      }
    }
    getAndSetIsFollowing();
  }, []);

  //이미 내가 팔로우를 신청했는지 확인!
  const [isWaitingConfirm, setIsWaitingConfirm] = useState();
  useEffect(() => {
    async function getAndSetIsWaitingConfirm() {
      if (isOtherProfileBox) {
        const response = await getIsWaitingConfirm(props.otherUserId);
        setIsWaitingConfirm(response);
      }
    }
    getAndSetIsWaitingConfirm();
  }, []);

  function gofollowerlist() {
    movePage("/follower");
  }

  function gofollowinglist() {
    movePage("/following");
  }

  function doFollow() {}

  function doUnfollow() {}

  return (
    <S.Container>
      <S.Box>
        <S.Img src={props.profileImg} alt="img" />
        <S.Name>{props.nickname}</S.Name>
        <S.FollowBox>
          <div onClick={isOtherProfileBox ? null : gofollowerlist}>
            <S.FollowText>팔로워</S.FollowText>
            <S.FollowNumber>{props.follower}</S.FollowNumber>
          </div>
          <div onClick={isOtherProfileBox ? null : gofollowinglist}>
            <S.FollowText>팔로잉</S.FollowText>
            <S.FollowNumber>{props.following}</S.FollowNumber>
          </div>
        </S.FollowBox>
      </S.Box>
      {isOtherProfileBox ? (
        isFollowing ? (
          <G.longBtnBorder onClick={doUnfollow}>언팔로우 하기</G.longBtnBorder>
        ) : isWaitingConfirm ? (
          <G.longBtnDisabled>팔로우 수락 대기 중</G.longBtnDisabled>
        ) : (
          <G.longBtnDefault onClick={doFollow}>팔로우 하기</G.longBtnDefault>
        )
      ) : (
        <></>
      )}
    </S.Container>
  );
}
