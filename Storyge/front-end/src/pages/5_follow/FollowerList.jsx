import React, { useState, useEffect } from "react";
import * as S from "./Follow.js";
import { getCookie } from "./../../utils/Cookies";
import Api from "lib/customApi";
import { useNavigate } from "react-router-dom";
import Swal from "sweetalert2";
import { MdClose, MdCheck } from "react-icons/md";

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
        setNewList(response.data);
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
        setFollowerList(response.data);
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
      setdeleteFollow(true);
      e.preventDefault();
    } catch (err) {
      console.log(err);
    }
  };

  const deleteFollower = async (id, e) => {
    try {
      if (
        Swal.fire({
          text: "삭제하시겠습니까?",
          icon: "warning",
          showCancelButton: true,
          confirmButtonColor: "var(--color-primary)",
          cancelButtonColor: "var(--color-warning)",
          confirmButtonText: "Yes",
        }).then((result) => {
          if (result.isConfirmed) {
            Api.delete(`/follower/${id}`, {
              headers: {
                Authorization: getCookie("token"),
              },
            }).then(() => {
              setFlag(!flag);
              e.preventDefault();
            });
          }
        })
      ) {
      }
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
      {newList != null && newList.length > 0 ? (
        <S.LineText>New</S.LineText>
      ) : null}
      <S.List>
        {newList &&
          newList.map((list) => {
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
                    <MdCheck
                      color="var(--color-primary)"
                      size="23"
                      onClick={(e) => {
                        acceptFollow(list.userId, e);
                      }}
                    >
                      확인
                    </MdCheck>
                    <MdClose
                      color="var(--color-warning)"
                      size="23"
                      onClick={(e) => {
                        deleteFollowWait(list.userId, e);
                      }}
                    >
                      삭제
                    </MdClose>
                  </S.BtnBox>
                ) : (
                  <S.Text>요청 거절됨</S.Text>
                )}
              </S.Profile>
            );
          })}
      </S.List>

      <S.LineText>ALL</S.LineText>

      {followerList.length === 0 ? (
        <S.NoFollow>팔로워가 없어요 🥲</S.NoFollow>
      ) : (
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
                  <MdClose
                    color="var(--color-warning)"
                    size="23"
                    onClick={(e) => {
                      deleteFollower(follower.userId, e);
                    }}
                  >
                    삭제
                  </MdClose>
                </S.BtnBox>
              </S.Profile>
            );
          })}
        </S.List>
      )}
    </S.Container>
  );
}
