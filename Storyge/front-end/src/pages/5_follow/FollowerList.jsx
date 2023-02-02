import React from "react";
import * as S from "./FollowerList.js";
import * as G from "../../styles";

import SearchBar from "./../../components/searchbar/SearchBar.jsx";
import FollowBtn from "./../../components/toggleBtn/FollowBtn.jsx";
import newProfileData from "./../2_main/NewDiaryData";

export default function FollowerList() {
  return (
    <G.BodyContainer>
      <S.Box>
        <SearchBar />
      </S.Box>

      <FollowBtn />

      <S.LineText>New</S.LineText>
      <S.List>
        {newProfileData.map((profile) => {
          return (
            <S.Profile>
              <S.Img profile={profile.imgUrl} key={profile.id}></S.Img>
              <S.Text>{profile.name}</S.Text>
            </S.Profile>
          );
        })}
      </S.List>

      <S.LineText>ALL</S.LineText>
      <S.List>
        {newProfileData.map((profile) => {
          return (
            <S.Profile>
              <S.Img profile={profile.imgUrl} key={profile.id}></S.Img>
              <S.Text>{profile.name}</S.Text>
            </S.Profile>
          );
        })}
      </S.List>
    </G.BodyContainer>
  );
}
