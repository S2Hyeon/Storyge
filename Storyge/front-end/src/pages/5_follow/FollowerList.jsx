import React from 'react';
import * as G from "../../styles/index.js";
import * as S from "./FollowerList.js";
import SearchBar from './../../components/searchbar/SearchBar.jsx';

export default function FollowerList() {
  return (
    <G.BodyContainer>
      <S.Box>
        
        <SearchBar />
      </S.Box>
      
    </G.BodyContainer>
  );
}

