import React from 'react';
import * as G from "../../styles/index.js";
import * as S from "./FollowerList.js";
import SearchBar from './../../components/searchbar/SearchBar.jsx';
import FollowBtn from './../../components/toggleBtn/FollowBtn.jsx';

export default function FollowerList() {
  return (
    <G.BodyContainer>
      <S.Box>
        <SearchBar />
      </S.Box>
      <FollowBtn />
      
    </G.BodyContainer>
  );
}

