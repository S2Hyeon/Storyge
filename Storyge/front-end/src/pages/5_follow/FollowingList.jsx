import React, { useEffect, useState } from "react";
import * as S from "./Follow.js";
import { getCookie } from "./../../utils/Cookies";
import Api from "lib/customApi";

export default function FollowingList() {
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
  }, []);

  const deleteFollowing = async (id, e) => {
    try {
      await Api.delete(`/following/${id}`, {
        headers: {
          Authorization: getCookie("token"),
        },
      });
      console.log("팔로잉 삭제");
      console.log(id); // error
      e.preventDefault();
    } catch (err) {
      console.log(err);
    }
  };

  return (
    <S.Container>
      <S.LineText>ALL</S.LineText>
      <S.List>
        {followingList.map((list) => {
          return (
            <S.Profile key={list.userId}>
              <S.Img profile={list.profileImg}></S.Img>
              <S.Text>{list.nickname}</S.Text>
              <S.BtnBox>
                <S.FollowBtn
                  borderColor="var(--color-primary)"
                  color="var(--color-primary)"
                  onClick={(e) => {
                    deleteFollowing(list.userId, e);
                  }}
                >
                  팔로잉
                </S.FollowBtn>
              </S.BtnBox>
            </S.Profile>
          );
        })}
      </S.List>
    </S.Container>
  );
}
