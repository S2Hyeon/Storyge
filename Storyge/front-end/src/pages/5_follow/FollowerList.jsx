import React, {useState} from "react";
import * as S from "./Follow.js";
import newProfileData from "./../2_main/NewDiaryData";

export default function FollowerList() {
  const [deleteFollow, setdeleteFollow] = useState(true);

  return (
    <S.Container>
      <S.LineText>New</S.LineText>
      <S.List>
        {newProfileData.map((profile) => {
          return (
            <S.Profile key={profile.id}>
              <S.Img profile={profile.imgUrl}></S.Img>
              <S.Text>{profile.name}</S.Text>

              {deleteFollow ?  (
                <S.BtnBox>
                  <S.FollowBtn
                    borderColor="var(--color-primary)"
                    color="var(--color-primary)"
                  >
                    확인
                  </S.FollowBtn>
                  <S.FollowBtn
                    borderColor="var(--color-warning)"
                    color="var(--color-warning)"
                    onClick={() => {setdeleteFollow(true); console.log(deleteFollow)}}
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
        {newProfileData.map((profile) => {
          return (
            <S.Profile key={profile.id}>
              <S.Img profile={profile.imgUrl}></S.Img>
              <S.Text>{profile.name}</S.Text>
              <S.BtnBox>
                <S.FollowBtn
                  borderColor="var(--color-warning)"
                  color="var(--color-warning)"
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
