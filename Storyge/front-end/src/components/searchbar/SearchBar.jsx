import React from 'react';
import { AiOutlineSearch } from "react-icons/ai";
import * as S from "./../searchbar/SearchBar.js";

export default function SearchBar() {
  return (
    <S.Box>
      <S.Icon>
        <AiOutlineSearch style={{width: '25px', height: '25px', color: '#C0C0C0'}}/>
      </S.Icon>
      <S.Text placeholder={"Search"}></S.Text>
    </S.Box>
  );
}

