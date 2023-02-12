import React, { useEffect, useState } from "react";
import { AiOutlineSearch } from "react-icons/ai";
import * as S from "./../searchbar/SearchBar.js";
import resultData from "./SearchBarData";
import { debounce } from 'lodash';
import { getUserSearch } from "api/user/getUserSearch";

export default function SearchBar() {
  let [keyword, setKeyword] = useState("");
  let [focus, setFocus] = useState(false);
  let [result, setResult] = useState([]);

  function onKeyUpKeyword(e) {
    setKeyword(e.target.value);
  }

  function changeAutoSearchContainer() {
    setFocus(!focus);
  }

  function getSearchResult() {
    console.log("검색 시작1 : " + keyword);
    // debounce(() => {
      async function userSearch() {
        console.log("검색 시작 : " + keyword);
        const response = await getUserSearch(keyword);
        setResult(response);
      }
      userSearch();
    // }, 300)
  }

  useEffect(() => {
    getSearchResult();
  }, [keyword]);


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
