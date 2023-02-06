import React, { useState } from "react";
import * as S from "./Follow.js";
import * as B from "./FollowBtn.js";
import * as G from "../../styles";

import SearchBar from "./../../components/searchbar/SearchBar.jsx";
import FollowerList from "./FollowerList.jsx";
import FollowingList from "./FollowingList.jsx";

export default function Follow() {
  const [followAlarm, setFollowAlarm] = useState(true);

  return (
    <G.BodyContainer>
      <SearchBar />

      <S.Box>
        <B.Follower
          followAlarm={followAlarm}
          onClick={() => {
            setFollowAlarm(true);
          }}
        >
          <B.Text>팔로워</B.Text>
        </B.Follower>

        <B.Following
          followAlarm={followAlarm}
          onClick={() => {
            setFollowAlarm(false);
          }}
        >
          <B.Text>팔로잉</B.Text>
        </B.Following>
      </S.Box>

      {followAlarm ? <FollowerList /> : <FollowingList />}
    </G.BodyContainer>
  );
}
