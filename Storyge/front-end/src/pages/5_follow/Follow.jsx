import React, { useState } from 'react'
import * as S from './Follow.js'
import * as B from './FollowBtn.js'
import * as G from '../../styles'

import SearchBar from './../../components/searchbar/SearchBar.jsx'
import FollowerList from './FollowerList.jsx'
import FollowingList from './FollowingList.jsx'

export default function Follow() {
  const [check, setCheck] = useState(false)
  const [followerAlarm, setFollowerAlarm] = useState(true)
  const [followingAlarm, setFollowingAlarm] = useState(true)

  return (
    <G.BodyContainer>
      <SearchBar />
      <S.Box>
        <B.Follower
          onClick={() => {
            setCheck(true)
          }}
          followAlarm={check}
        >
          <B.Text>팔로워</B.Text>
        </B.Follower>

        <B.Following
          onClick={() => {
            setCheck(false)
          }}
          followAlarm={check}
        >
          <B.Text>팔로잉</B.Text>
        </B.Following>
      </S.Box>

      {check ? (
        followerAlarm ? (
          <FollowerList />
        ) : (
          <FollowingList />
        )
      ) : followingAlarm ? (
        <FollowingList />
      ) : (
        <FollowerList />
      )}
    </G.BodyContainer>
  )
}
