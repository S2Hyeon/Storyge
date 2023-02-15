import React, { useState } from 'react'
import * as S from './Follow.js'
import * as B from './FollowBtn.js'
import * as G from '../../styles'

import SearchBar from './../../components/searchbar/SearchBar.jsx'
import FollowerList from './FollowerList.jsx'
import FollowingList from './FollowingList.jsx'

export default function Follow(props) {
  const [followAlarm, setFollowAlarm] = useState(props.status)

  return (
    <G.BodyContainer>
      <SearchBar />
      <S.Box>
        {/* props.status = true면, 팔로우 페이지 */}
        {/* props.status = false면, 팔로잉 페이지 */}
        {props.status}
        <B.Follower
          onClick={() => {
            setFollowAlarm(true)
          }}
          followAlarm={followAlarm}
        >
          <B.Text>팔로워</B.Text>
        </B.Follower>

        <B.Following
          onClick={() => {
            setFollowAlarm(false)
          }}
          followAlarm={followAlarm}
        >
          <B.Text>팔로잉</B.Text>
        </B.Following>
      </S.Box>

      {followAlarm ? <FollowerList /> : <FollowingList />}
    </G.BodyContainer>
  )
}
