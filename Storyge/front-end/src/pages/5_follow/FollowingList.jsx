import React from "react";
import * as S from "./Follow.js";
import newProfileData from "./../2_main/NewDiaryData";

export default function FollowingList() {
  return (
    <S.Container>
      <S.LineText>ALL</S.LineText>
      <S.List>
        {newProfileData.map((profile) => {
          return (
            <S.Profile key={profile.id}>
              <S.Img profile={profile.imgUrl}></S.Img>
              <S.Text>{profile.name}</S.Text>
              <S.BtnBox>
                <S.FollowBtn
                  borderColor="var(--color-primary)"
                  color="var(--color-primary)"
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
