import React, { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import * as S from "./../profileBox/ProfileBoxStyle.js";
import * as G from "styles";
import { getIsFollowing } from "api/user/getIsFollowing.js";
import { postApplyFollow } from "api/user/postApplyFollow.js";
import { deleteFollowing } from "api/user/deleteFollowing.js";
import { getOtherUserData } from "api/user/getOtherUserData.js";

import Swal from "sweetalert2";

export default function ProfileBox(props) {
  const [isStateChanged, setIsStateChanged] = useState();
  const movePage = useNavigate();

  //다른 사람의 profileBox인지 확인!
  const [isOtherProfileBox, setIsOtherProfileBox] = useState();
  useEffect(() => {
    if (props.otherUserId != null) {
      setIsOtherProfileBox(true);
    } else {
      setIsOtherProfileBox(false);
    }
  }, []);

  //다른 사람을 팔로잉하고 있는지 확인!
  const [isFollowing, setIsFollowing] = useState();
  useEffect(() => {
    async function getAndSetIsFollowing() {
      if (props.otherUserId != null) {
        const response = await getIsFollowing(props.otherUserId);
        setIsFollowing(response);
      }
    }
    getAndSetIsFollowing();
  }, [isStateChanged]);

  //이미 내가 팔로우를 신청했는지 여부
  const [isWaitingConfirm, setIsWaitingConfirm] = useState(
    props.isAlreadyApply
  );
  useEffect(() => {
    if (props.otherUserId != null) {
      async function getAndSetIsWaitingConfirm() {
        const response = await getOtherUserData(props.otherUserId);
        setIsWaitingConfirm(response.scope);
      }
      getAndSetIsWaitingConfirm();
    }
  }, [isStateChanged]);

  function gofollowerlist() {
    movePage("/follower");
  }

  function gofollowinglist() {
    movePage("/following");
  }

  async function doFollow() {
    await postApplyFollow(props.otherUserId);
    setIsStateChanged(!isStateChanged);
  }

  async function doUnfollow() {
    if (
      Swal.fire({
        text: "삭제하시겠습니까?",
        icon: "warning",
        showCancelButton: true,
        confirmButtonColor: "#3085d6",
        cancelButtonColor: "#d33",
        confirmButtonText: "Yes",
      }).then((result) => {
        if (result.isConfirmed) {
          deleteFollowing(props.otherUserId);
          // setIsStateChanged(!isStateChanged);
          setIsFollowing(false);
          props.test(!isFollowing);
          // setIsFollowing(!isFollowing);
        }
      })
    ) {
    }
  }

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
      ) : null}
    </S.Container>
  );
}
