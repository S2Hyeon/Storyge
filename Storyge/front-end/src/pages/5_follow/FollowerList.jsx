import React, { useState, useEffect } from "react";
import * as S from "./Follow.js";
import { getCookie } from "./../../utils/Cookies";
import Api from "lib/customApi";
import { useNavigate } from "react-router-dom";

export default function FollowerList() {
  const [flag, setFlag] = useState(false);
  const [deleteFollow, setdeleteFollow] = useState(true);
  const [followerList, setFollowerList] = useState([]);
  const [newList, setNewList] = useState([]);

  const movePage = useNavigate();

  //처음 렌더링이 될 때만 실행
  useEffect(() => {
    async function getNewList() {
      try {
        const response = await Api.get("/follow-wait", {
          headers: {
            Authorization: getCookie("token"),
          },
        });
        console.log(response.data);
        setNewList(response.data);
        console.log("팔로우 대기");
        console.log(newList);
      } catch (err) {
        console.log(err);
      }
    }

    async function getFollowerList() {
      try {
        const response = await Api.get("/follower", {
          headers: {
            Authorization: getCookie("token"),
          },
        });
        console.log(response.data);
        setFollowerList(response.data);
        console.log("팔로워 목록");
        console.log(followerList);
      } catch (err) {
        console.log(err);
      }
    }
    getFollowerList();
    getNewList();
  }, [flag]);

  const deleteFollowWait = async (id, e) => {
    try {
      await Api.delete(`/follow-wait/${id}`, {
        headers: {
          Authorization: getCookie("token"),
        },
      });
      console.log("팔로우 거절");
      setdeleteFollow(true);
      console.log(id); // error
      e.preventDefault();
    } catch (err) {
      console.log(err);
    }
  };

  const deleteFollower = async (id, e) => {
    try {
      await Api.delete(`/follower/${id}`, {
        headers: {
          Authorization: getCookie("token"),
        },
      });
      console.log("팔로워 삭제");
      console.log(id); // error
      setFlag(!flag);
      e.preventDefault();
    } catch (err) {
      console.log(err);
    }
  };

  const acceptFollow = async (id, e) => {
    try {
      await Api.post(
        `/follow`,
        {
          userId: id,
        },
        {
          headers: {
            Authorization: getCookie("token"),
          },
        }
      );
      console.log(id);
      console.log("팔로우 등록");
      setFlag(!flag);
      e.preventDefault();
    } catch (err) {
      console.log(err);
    }
  };

  function goOtherPage(id, e) {
    movePage("/otherpage", { state: { otherId: id } });
  }

  return (
    <S.Container>
      <S.LineText>New</S.LineText>
      <S.List>
        {newList.map((list) => {
          return (
            <S.Profile key={list.userId}>
              <S.AllBox
                onClick={(e) => {
                  goOtherPage(list.userId, e);
                }}
              >
                <S.Img profile={list.profileImg}></S.Img>
                <S.Text>{list.nickname}</S.Text>
              </S.AllBox>
              {deleteFollow ? (
                <S.BtnBox>
                  <S.FollowBtn
                    borderColor="var(--color-primary)"
                    color="var(--color-primary)"
                    onClick={(e) => {
                      acceptFollow(list.userId, e);
                    }}
                  >
                    확인
                  </S.FollowBtn>
                  <S.FollowBtn
                    borderColor="var(--color-warning)"
                    color="var(--color-warning)"
                    onClick={(e) => {
                      deleteFollowWait(list.userId, e);
                    }}
                  >
                    삭제
                  </S.FollowBtn>
                </S.BtnBox>
              ) : (
                <S.Text>요청 거절됨</S.Text>
              )}
            </S.Profile>
          );
        })}
      </S.List>

      <S.LineText>ALL</S.LineText>
      <S.List>
        {followerList.map((follower) => {
          return (
            <S.Profile key={follower.userId}>
              <S.AllBox
                onClick={(e) => {
                  goOtherPage(follower.userId, e);
                }}
              >
                <S.Img profile={follower.profileImg}></S.Img>
                <S.Text>{follower.nickname}</S.Text>
              </S.AllBox>
              <S.BtnBox>
                <S.FollowBtn
                  borderColor="var(--color-warning)"
                  color="var(--color-warning)"
                  onClick={(e) => {
                    deleteFollower(follower.userId, e);
                  }}
                >
                  삭제
                </S.FollowBtn>
              </S.BtnBox>
            </S.Profile>
          );
        })}
      </S.List>
    </S.Container>
  );
}
