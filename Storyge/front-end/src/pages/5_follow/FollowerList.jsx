import React from 'react';
import * as G from "../../styles/index.js";
import * as S from "./FollowerList.js";
import { AiOutlineSearch } from "react-icons/ai";

export default function FollowerList() {
  return (
    <G.BodyContainer>
      <h2>팔로워 리스트</h2>
      <S.Box>
        <AiOutlineSearch />
      </S.Box>
      
    </G.BodyContainer>
  );
}

