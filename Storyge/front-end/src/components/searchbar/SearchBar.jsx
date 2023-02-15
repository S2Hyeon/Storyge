import React, { useEffect, useState } from 'react'
import { AiOutlineSearch } from 'react-icons/ai'
import * as S from './../searchbar/SearchBar.js'
import data2 from './SearchBarData'

import { debounce } from 'lodash'
import { getUserSearch } from 'api/user/getUserSearch'
import { useNavigate } from 'react-router-dom'

export default function SearchBar() {
  let [keyword, setKeyword] = useState('')
  let [focus, setFocus] = useState(false)
  let [resultData, setResultData] = useState([])

  function onKeyUpKeyword(e) {
    setKeyword(e.target.value)
  }

  function changeAutoSearchContainer() {
    setFocus(!focus)
  }

  useEffect(() => {
    async function getSearchResult() {
      const response = await getUserSearch(keyword)
      setResultData(response)
    }
    getSearchResult()
  }, [keyword])
  //keyword의 길이 변화로 api를 호출하니까 가짜 를 입력할때 가ㅉ가 되었을때 검색을 시도함 그래서 안될듯 ㅠㅠ

  const movePage = useNavigate()
  function goOtherPage(id) {
    movePage('/otherpage', { state: { otherId: id } })
  }

  const ShowResultData = () => {
    if (resultData && resultData.length !== 0) {
      return resultData.map((result) => {
        return (
          <S.AutoSearchData
            key={result.userId}
            onMouseDown={() => goOtherPage(result.userId)}
          >
            <S.ProfileImg imgUrl={result.profileImg} />
            <div>{result.nickname}</div>
          </S.AutoSearchData>
        )
      })
    } else {
      return <S.NoKeyword>검색 결과가 없습니다.</S.NoKeyword>
    }
  }

  return (
    <>
      <S.SearchContainer isFocused={focus}>
        <S.Icon>
          <AiOutlineSearch
            style={{ width: '25px', height: '25px', color: '#C0C0C0' }}
          />
        </S.Icon>
        <S.Text
          placeholder={'Search'}
          onKeyUp={onKeyUpKeyword}
          onFocus={changeAutoSearchContainer}
          onBlur={changeAutoSearchContainer}
        ></S.Text>
        {focus ? (
          <S.AutoSearchContainer>
            {keyword === '' ? (
              <S.NoKeyword>검색어를 입력해주세요.</S.NoKeyword>
            ) : (
              <ShowResultData />
            )}
          </S.AutoSearchContainer>
        ) : null}
      </S.SearchContainer>
    </>
  )
}
