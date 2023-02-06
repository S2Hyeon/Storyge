import React, { useState } from "react";
import { AiOutlineSearch } from "react-icons/ai";
import * as S from "./../searchbar/SearchBar.js";

export default function SearchBar() {
  let [keyword, setKeyword] = useState("");

  function onKeyUpKeyword(e) {
    setTimeout(setKeyword(e.target.value), 500);
  }

  return (
    <>
      <S.SearchContainer>
        <S.Icon>
          <AiOutlineSearch
            style={{ width: "25px", height: "25px", color: "#C0C0C0" }}
          />
        </S.Icon>
        <S.Text placeholder={"Search"} onKeyUp={onKeyUpKeyword}></S.Text>
        {keyword !== "" ? (
          <S.AutoSearchContainer>
            <S.AutoSearchWrap>
              <S.AutoSearchData>뀨1</S.AutoSearchData>
              <S.AutoSearchData>뀨2</S.AutoSearchData>
              <S.AutoSearchData>뀨3</S.AutoSearchData>
              <S.AutoSearchData>뀨3</S.AutoSearchData>
              <S.AutoSearchData>뀨3</S.AutoSearchData>
              <S.AutoSearchData>뀨3</S.AutoSearchData>
              <S.AutoSearchData>뀨3</S.AutoSearchData>
              <S.AutoSearchData>뀨3</S.AutoSearchData>
              <S.AutoSearchData>뀨3</S.AutoSearchData>
              <S.AutoSearchData>뀨3</S.AutoSearchData>
              <S.AutoSearchData>뀨3</S.AutoSearchData>
              <S.AutoSearchData>뀨3</S.AutoSearchData>
              <S.AutoSearchData>뀨3</S.AutoSearchData>
              <S.AutoSearchData>뀨3</S.AutoSearchData>
            </S.AutoSearchWrap>
          </S.AutoSearchContainer>
        ) : null}
      </S.SearchContainer>
    </>
  );
}
