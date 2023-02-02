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
          onClick={() => {
            setFollowAlarm(true);
            console.log("팔로우 버튼 클릭!");
          }}
        >
          <B.Text>팔로우</B.Text>
        </B.Follower>

        <B.Following
          onClick={() => {
            setFollowAlarm(false);
            console.log("팔로잉 버튼 클릭!");
          }}
        >
          <B.Text>팔로잉</B.Text>
        </B.Following>
      </S.Box>

      {followAlarm ? <FollowerList /> : <FollowingList />}
    </G.BodyContainer>
  );
}
