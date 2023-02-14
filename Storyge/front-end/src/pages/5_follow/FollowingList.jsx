import React, { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import * as S from "./Follow.js";
import { getCookie } from "./../../utils/Cookies";
import Api from "lib/customApi";
import Swal from "sweetalert2";

export default function FollowingList() {
  const movePage = useNavigate();

  const [flag, setFlag] = useState(false);
  const [followingList, setFollowingList] = useState([]);

  //처음 렌더링이 될 때만 실행
  useEffect(() => {
    async function getFollowingList() {
      try {
        const response = await Api.get("/following", {
          headers: {
            Authorization: getCookie("token"),
          },
        });
        console.log(response.data);
        setFollowingList(response.data);
        console.log("팔로잉 리스트");
        console.log(followingList);
      } catch (err) {
        console.log(err);
      }
    }
    getFollowingList();
    // eslint-disable-next-line react-hooks/exhaustive-deps
  }, [flag]);

  const deleteFollowing = async (id, e) => {
    if (
      Swal.fire({
        text: "언팔하시겠습니까?",
        icon: "warning",
        showCancelButton: true,
        confirmButtonColor: "#3085d6",
        cancelButtonColor: "#d33",
        confirmButtonText: "Yes",
      }).then((result) => {
        if (result.isConfirmed) {
          try {
            Api.delete(`/following/${id}`, {
              headers: {
                Authorization: getCookie("token"),
              },
            }).then(() => {
              console.log("팔로잉 삭제");
              console.log(id); // error
              setFlag(!flag);
              e.preventDefault();
            });
          } catch (err) {
            console.log(err);
          }
        }
      })
    ) {
    }
  };

  function goOtherPage(id, e) {
    movePage("/otherpage", { state: { otherId: id } });
  }

  return (
    <S.Container>
      <S.LineText>ALL</S.LineText>
      <S.List>
        {followingList.map((list) => {
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
              <S.BtnBox>
                <S.FollowBtn
                  borderColor="var(--color-primary)"
                  color="var(--color-primary)"
                  onClick={(e) => {
                    deleteFollowing(list.userId, e);
                  }}
                >
                  언팔로
                </S.FollowBtn>
              </S.BtnBox>
            </S.Profile>
          );
        })}
      </S.List>
    </S.Container>
  );
}
