import React, { useState } from "react";
import { AiOutlineSearch } from "react-icons/ai";
import * as S from "./../searchbar/SearchBar.js";
import resultData from "./SearchBarData";

export default function SearchBar() {
  let [keyword, setKeyword] = useState("");
  let [focus, setFocus] = useState(false);

  function onKeyUpKeyword(e) {
    setKeyword(e.target.value);
  }

  function changeAutoSearchContainer() {
    setFocus(!focus);
  }

  return (
    <>
      <S.SearchContainer isFocused={focus}>
        <S.Icon>
          <AiOutlineSearch
            style={{ width: "25px", height: "25px", color: "#C0C0C0" }}
          />
        </S.Icon>
        <S.Text
          placeholder={"Search"}
          onKeyUp={onKeyUpKeyword}
          onFocus={changeAutoSearchContainer}
          onBlur={changeAutoSearchContainer}
        ></S.Text>
        {focus ? (
          <S.AutoSearchContainer>
            {keyword === "" ? (
              <S.noKeyword>검색어를 입력해주세요.</S.noKeyword>
            ) : (
              resultData.map((result) => {
                return (
                  <S.AutoSearchData key={result.id}>
                    <S.ProfileImg imgUrl={result.imgUrl} />
                    <div>{result.nickname}</div>
                  </S.AutoSearchData>
                );
              })
            )}
          </S.AutoSearchContainer>
        ) : null}
      </S.SearchContainer>
    </>
  );
}
